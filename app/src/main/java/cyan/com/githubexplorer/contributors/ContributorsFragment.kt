package cyan.com.githubexplorer.contributors

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cyan.com.githubexplorer.R
import cyan.com.githubexplorer.contributors.adapter.ContributorsListAdapter
import cyan.com.githubexplorer.contributors.adapter.ContributorsListPresenterImpl
import cyan.com.githubexplorer.model.data.Contributor
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_contributors_list.view.*
import javax.inject.Inject

class ContributorsFragment : DaggerFragment(), ContributorsView {
    @Inject
    lateinit var presenter: ContributorsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contributors_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.viewReady()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.destroy()
    }

    override fun onDisplayContributors(contributors: List<Contributor>) {
        view?.contributorsRecyclerView?.adapter = ContributorsListAdapter(ContributorsListPresenterImpl(contributors))
    }

    override fun onDisplayError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun onViewReady() {
        initView()
        presenter.queryContributors(
            context?.getString(R.string.user).orEmpty(),
            context?.getString(R.string.repo).orEmpty())
    }

    private fun initView() {
        view?.contributorsRecyclerView?.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }
}