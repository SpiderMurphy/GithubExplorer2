package cyan.com.githubexplorer.contributors

import cyan.com.githubexplorer.model.data.Contributor

data class ContributorsViewState(
    val contributors: List<Contributor> = listOf(),
    val isClean: Boolean = true,
    val errorMessage: String? = null
)