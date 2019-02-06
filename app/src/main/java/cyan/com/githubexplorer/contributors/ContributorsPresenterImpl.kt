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
        view?.onViewReady()
    }

    override fun queryContributors(user: String, repo: String) {
        disposable.add(
            repository.fetchRepoContributors(user, repo)
                .map {
                    it.sortedWith(Comparator { c1, c2 -> c1.login.orEmpty().compareTo(c2.login.orEmpty())
                    })
                }
                .subscribe(
                {
                    contributors ->
                    view?.onDisplayContributors(contributors)
                },
                {
                    view?.onDisplayError(it.localizedMessage)
                })
        )
    }
}