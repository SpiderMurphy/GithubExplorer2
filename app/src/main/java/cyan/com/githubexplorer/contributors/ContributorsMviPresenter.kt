package cyan.com.githubexplorer.contributors

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Observer
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class ContributorsMviPresenter(
    private val interactor: ContributorsInteractor
) {
    private val eventsSubject = PublishSubject.create<ContributorsIntent>()
    private val stateObservable: Observable<ContributorsViewState>
    private val fetchTransformer: ObservableTransformer<ContributorsIntent.FetchContributors, ContributorsResult> = ObservableTransformer { event ->
        event.flatMap {
            interactor.queryContributors(it.user, it.repo)
                .onErrorReturn { ContributorsResult.ErrorResult(it.localizedMessage) }
        }
    }
    private val transformer: ObservableTransformer<ContributorsIntent, ContributorsViewState> = ObservableTransformer {
        eventsSubject.publish { shared ->
            Observable.merge(
                listOf(
                    shared.ofType(ContributorsIntent.FetchContributors::class.java).compose(fetchTransformer).scan(ContributorsViewState(), ::reduce)
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
        stateObservable.subscribe(render)
    }

    private fun reduce(previous: ContributorsViewState, result: ContributorsResult): ContributorsViewState {
        val data = previous.copy()
        return when (result) {
            is ContributorsResult.FetchedResult -> {
                ContributorsViewState(contributors = data.contributors + result.contributors)
            }
            is ContributorsResult.ErrorResult -> {
                ContributorsViewState(contributors = data.contributors, errorMessage = result.errorMsg)
            }
            ContributorsResult.CleanResult -> TODO()
        }
    }
}