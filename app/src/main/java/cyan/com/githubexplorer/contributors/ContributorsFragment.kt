package cyan.com.githubexplorer.contributors

import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ContributorsFragment : DaggerFragment(), ContributorsView {
    @Inject
    lateinit var presenter: ContributorsPresenter
}