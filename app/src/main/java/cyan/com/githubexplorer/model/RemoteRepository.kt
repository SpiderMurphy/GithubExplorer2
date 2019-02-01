package cyan.com.githubexplorer.model

import cyan.com.githubexplorer.model.data.Repo
import io.reactivex.Single

class RemoteRepository(
    private val apiService: GithubApi,
    private val executorService: ExecutorService
) : Repository {
    override fun fetchUserRepo(user: String, repo: String): Single<List<Repo>> = apiService
        .getUserRepo(user, repo)
        .compose(executorService.singleThreadExecutor())
}