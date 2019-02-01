package cyan.com.githubexplorer.contributors

import cyan.com.githubexplorer.model.Repository
import io.reactivex.disposables.CompositeDisposable

class ContributorsPresenterImpl(
    private var view: ContributorsView?,
    private val repository: Repository
) : ContributorsPresenter {
    private val disposable = CompositeDisposable()

    override fun destroy() {
        disposable.clear()
        view = null
    }

    override fun viewReady() {
    }
}