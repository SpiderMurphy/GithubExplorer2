package cyan.com.githubexplorer.contributors

import cyan.com.githubexplorer.model.Repository

class ContributorsPresenterImpl(
    private var view: ContributorsView?,
    private val repository: Repository
) : ContributorsPresenter {
    override fun destroy() {
        view = null
    }

    override fun viewReady() {
    }
}