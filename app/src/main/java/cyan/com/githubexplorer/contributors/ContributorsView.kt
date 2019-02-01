package cyan.com.githubexplorer.contributors

import cyan.com.githubexplorer.model.data.Contributor

interface ContributorsView {
    fun onViewReady()
    fun onDisplayContributors(contributors: List<Contributor>)
    fun onDisplayError(error: String)
}