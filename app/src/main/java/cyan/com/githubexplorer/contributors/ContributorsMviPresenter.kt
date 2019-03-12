package cyan.com.githubexplorer.contributors

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class ContributorsMviPresenter(
    private val interactor: ContributorsInteractor
) {
    private val disposable = CompositeDisposable()
    private val eventsSubject = PublishSubject.create<ContributorsIntent>()
    private val stateObservable: Observable<ContributorsViewState>
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
    private val transformer: ObservableTransformer<ContributorsIntent, ContributorsViewState> = ObservableTransformer {
        eventsSubject.publish { shared ->
            Observable.merge(
                listOf(
                    shared.ofType(ContributorsIntent.FetchContributors::class.java).compose(fetchTransformer).scan(ContributorsViewState(), ::reduce),
                    shared.ofType(ContributorsIntent.ClearContributors::class.java).compose(clearTransformer).scan(ContributorsViewState(), ::reduce)
                )
            )
        }
    }

    init {
        stateObservable = eventsSubject.compose(transformer)
    }

    fun bind(events: Observable<ContributorsIntent>) {
        events.subscribe(eventsSubject)
    }

    fun subscribe(render: (state: ContributorsViewState) -> Unit) {
        disposable.add(stateObservable.subscribe(render))
    }

    fun destroy() {
        disposable.clear()
    }

    private fun reduce(previous: ContributorsViewState, result: ContributorsResult): ContributorsViewState {
        val data = previous.copy()
        return when (result) {
            is ContributorsResult.FetchedResult -> {
                ContributorsViewState(contributors = result.contributors)
            }
            is ContributorsResult.ErrorResult -> {
                ContributorsViewState(contributors = data.contributors, errorMessage = result.errorMsg)
            }
            ContributorsResult.CleanResult -> {
                ContributorsViewState()
            }
        }
    }
}