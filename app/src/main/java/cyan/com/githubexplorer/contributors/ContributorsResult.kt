package cyan.com.githubexplorer.contributors

import cyan.com.githubexplorer.model.data.Contributor

sealed class ContributorsResult {
    class FetchedResult(val contributors: List<Contributor>) : ContributorsResult()
    class ErrorResult(val errorMsg: String) : ContributorsResult()
    object CleanResult : ContributorsResult()
}