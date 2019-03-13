package cyan.com.githubexplorer.contributors.mvi

sealed class ContributorsIntent {
    data class FetchContributors(val user: String, val repo: String): ContributorsIntent()
    object ClearContributors: ContributorsIntent()
}