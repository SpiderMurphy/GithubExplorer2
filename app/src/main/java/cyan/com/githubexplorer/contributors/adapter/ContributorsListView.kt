package cyan.com.githubexplorer.contributors.adapter

interface ContributorsListView {
    fun onBindView(username: String, contributions: String)
    fun onDisplayAvatar(avatar: String)
}