package cyan.com.githubexplorer.contributors.adapter

interface ContributorsListView {
    interface ContributorsViewHolderView {
        fun onBindView(username: String, contributions: String)
        fun onBindView(header: String)
        fun onDisplayAvatar(avatar: String)
    }
}