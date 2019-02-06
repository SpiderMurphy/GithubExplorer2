package cyan.com.githubexplorer.contributors.adapter

interface ContributorsListPresenter {
    fun getCount(): Int
    fun bindView(position: Int, view: ContributorsListView.ContributorsViewHolderView)
    fun displayAvatar(position: Int, view: ContributorsListView.ContributorsViewHolderView)
    fun getViewType(position: Int): Int
    fun groupByFirstLetter()
}