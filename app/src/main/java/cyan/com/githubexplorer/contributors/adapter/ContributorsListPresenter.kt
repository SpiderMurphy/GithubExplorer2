package cyan.com.githubexplorer.contributors.adapter

interface ContributorsListPresenter {
    fun getCount(): Int
    fun bindView(position: Int, view: ContributorsListView)
    fun displayAvatar(position: Int, view: ContributorsListView)
}