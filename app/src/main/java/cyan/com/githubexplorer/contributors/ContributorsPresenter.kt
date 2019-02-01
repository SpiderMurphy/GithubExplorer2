package cyan.com.githubexplorer.contributors

import cyan.com.githubexplorer.mvp.BasePresenter

interface ContributorsPresenter : BasePresenter {
    fun queryContributors(user: String, repo: String)
}