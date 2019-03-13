package cyan.com.githubexplorer.contributors.mvi

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observables.ConnectableObservable
import io.reactivex.subjects.PublishSubject

class ContributorsMviPresenter(
    private val interactor: ContributorsInteractor
) {
    private val uiDisposable = CompositeDisposable()
    private val coreDisposable = CompositeDisposable()
    private val eventsSubject = PublishSubject.create<ContributorsIntent>()
    private val stateObservable: ConnectableObservable<ContributorsViewState>
    private val fetchTransformer: ObservableTransformer<ContributorsIntent.FetchContributors, ContributorsResult> = ObservableTransformer { intent ->
        intent.flatMap {
            interactor.queryContributors(it.user, it.repo)
                .onErrorReturn { e -> ContributorsResult.ErrorResult(e.localizedMessage) }
        }
    }

    private val clearTransformer: ObservableTransformer<ContributorsIntent.ClearContributors, ContributorsResult> = ObservableTransformer { intent ->
        intent.map {
            ContributorsResult.CleanResult
        }
    }
    private val transformer: ObservableTransformer<ContributorsIntent, ContributorsResult> = ObservableTransformer {
        eventsSubject.publish { shared ->
            Observable.merge(
                listOf(
                    shared.ofType(ContributorsIntent.FetchContributors::class.java).compose(fetchTransformer),
                    shared.ofType(ContributorsIntent.ClearContributors::class.java).compose(clearTransformer)
                )
            )
        }
    }

    init {
        stateObservable = eventsSubject.compose(transformer).scan(
            ContributorsViewState(), ::reduce).publish()
    }

    fun bind(events: Observable<ContributorsIntent>) {
        events.subscribe(eventsSubject)
    }

    fun subscribe(render: (state: ContributorsViewState) -> Unit) {
        coreDisposable.add(stateObservable.connect())
        uiDisposable.add(stateObservable.subscribe(render))
    }

    fun unsubscribe() {
        uiDisposable.clear()
    }

    fun destroy() {
        if (!uiDisposable.isDisposed) {
            uiDisposable.dispose()
        }
        coreDisposable.dispose()
    }

    private fun reduce(previous: ContributorsViewState, result: ContributorsResult): ContributorsViewState {
        val data = previous.copy()
        return when (result) {
            is ContributorsResult.FetchedResult -> {
                ContributorsViewState(contributors = result.contributors)
            }
            is ContributorsResult.ErrorResult -> {
                ContributorsViewState(
                    contributors = data.contributors,
                    errorMessage = result.errorMsg
                )
            }
            ContributorsResult.CleanResult -> {
                ContributorsViewState()
            }
        }
    }
}