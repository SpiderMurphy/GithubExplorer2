package cyan.com.githubexplorer.contributors

import android.annotation.SuppressLint
import android.util.Log
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
        view?.onViewReady()
    }

    override fun queryContributors(user: String, repo: String) {
        disposable.add(repository.fetchRepoContributors(user, repo).subscribe(
            {
                contributors ->
                view?.onDisplayContributors(contributors)
            },
            {
                Log.w("Error", it.toString())
            })
        )
    }
}