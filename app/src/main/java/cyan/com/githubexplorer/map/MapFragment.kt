package cyan.com.githubexplorer.map

import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MapFragment : DaggerFragment() {
    @Inject
    lateinit var presenter: MapPresenter
}