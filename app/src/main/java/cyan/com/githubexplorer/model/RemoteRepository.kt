package cyan.com.githubexplorer.model

class RemoteRepository(
    private val apiService: GithubApi,
    private val executorService: ExecutorService
) : Repository {

}