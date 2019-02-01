package cyan.com.githubexplorer.model

import cyan.com.githubexplorer.model.data.Repo
import io.reactivex.Single
import retrofit2.http.GET

interface GithubApi {
    @GET("repos/{user}/{repo}")
    fun getUserRepo(user: String, repo: String): Single<List<Repo>>
}