package cyan.com.githubexplorer.model

import cyan.com.githubexplorer.model.data.Contributor
import cyan.com.githubexplorer.model.data.Repo
import io.reactivex.Single

interface Repository {
    fun fetchUserRepo(user: String, repo: String): Single<List<Repo>>
    fun fetchRepoContributors(user: String, repo: String): Single<List<Contributor>>
}