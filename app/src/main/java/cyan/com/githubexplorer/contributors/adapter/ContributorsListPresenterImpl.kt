package cyan.com.githubexplorer.contributors.adapter

import cyan.com.githubexplorer.model.data.Contributor

class ContributorsListPresenterImpl(
    private val contributors: List<Contributor>
) : ContributorsListPresenter {
    override fun bindView(position: Int, view: ContributorsListView) {
        val contributor = contributors[position]
        view.onBindView(contributor.login.orEmpty(), "${contributor.contributions}")
        displayAvatar(position, view)
    }

    override fun getCount(): Int = contributors.size

    override fun displayAvatar(position: Int, view: ContributorsListView) {
        view.onDisplayAvatar(contributors[position].avatar_url.orEmpty())
    }
}