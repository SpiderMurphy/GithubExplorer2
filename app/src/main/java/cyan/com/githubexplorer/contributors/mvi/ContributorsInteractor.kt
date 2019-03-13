package cyan.com.githubexplorer.contributors.mvi

import io.reactivex.Observable

interface ContributorsInteractor {
    fun queryContributors(username: String, repo: String): Observable<ContributorsResult>
}