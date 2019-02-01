package cyan.com.githubexplorer.model

import cyan.com.githubexplorer.model.data.Contributor
import cyan.com.githubexplorer.model.data.Repo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("repos/{user}/{repo}")
    fun getUserRepo(@Path("user") user: String, @Path("repo") repo: String): Single<List<Repo>>

    @GET("repos/{user}/{repo}/contributors")
    fun getRepoContributors(@Path("user") user: String, @Path("repo") repo: String): Single<List<Contributor>>
}