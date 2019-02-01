package cyan.com.githubexplorer.contributors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cyan.com.githubexplorer.R
import dagger.android.support.DaggerFragment
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
}