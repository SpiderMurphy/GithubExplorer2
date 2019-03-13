package cyan.com.githubexplorer.contributors

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jakewharton.rxbinding3.view.clicks
import cyan.com.githubexplorer.R
import cyan.com.githubexplorer.contributors.adapter.ContributorsListAdapter
import cyan.com.githubexplorer.contributors.adapter.ContributorsListPresenterImpl
import cyan.com.githubexplorer.contributors.mvi.ContributorsIntent
import cyan.com.githubexplorer.contributors.mvi.ContributorsMviPresenter
import cyan.com.githubexplorer.contributors.mvi.ContributorsViewState
import cyan.com.githubexplorer.model.data.Contributor
import dagger.android.support.DaggerFragment
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_contributors_list.view.*
import javax.inject.Inject

class ContributorsFragment : DaggerFragment() {
    @Inject
    lateinit var presenter: ContributorsMviPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contributors_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        presenter.bind(merge(view))
    }

    override fun onStart() {
        super.onStart()
        presenter.subscribe(::render)
    }

    override fun onStop() {
        super.onStop()
        presenter.unsubscribe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.destroy()
    }

    private fun displayContributors(contributors: List<Contributor>) {
        view?.contributorsRecyclerView?.adapter = ContributorsListAdapter(ContributorsListPresenterImpl(contributors))
    }

    private fun displayError(error: String) {
        if (!error.isEmpty()) Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    private fun merge(view: View): Observable<ContributorsIntent> = Observable.merge(
        listOf(
            view.refreshButton.clicks().map {
                ContributorsIntent.FetchContributors(getString(R.string.user), getString(R.string.repo))
            },
            view.clearButton.clicks().map {
                ContributorsIntent.ClearContributors
            }
        )
    )

    private fun render(state: ContributorsViewState) {
        with (state) {
            displayContributors(contributors)
            displayError(errorMessage.orEmpty())
        }
    }

    private fun initView() {
        view?.contributorsRecyclerView?.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }
}