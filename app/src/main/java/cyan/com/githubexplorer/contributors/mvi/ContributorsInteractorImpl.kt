package cyan.com.githubexplorer.contributors.mvi

import cyan.com.githubexplorer.model.ExecutorService
import cyan.com.githubexplorer.model.Repository
import io.reactivex.Observable

class ContributorsInteractorImpl(
    private val repository: Repository,
    private val executorService: ExecutorService
) : ContributorsInteractor {
    override fun queryContributors(username: String, repo: String): Observable<ContributorsResult> {
        return repository.fetchRepoContributors(username, repo)
            .toObservable()
            .flatMap {
                val sorted = it.sortedBy { cont ->  cont.login }
                Observable.just(ContributorsResult.FetchedResult(sorted))
            }
            .compose(executorService.observableExecutor())
    }
}