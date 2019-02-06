package cyan.com.githubexplorer.contributors.adapter

import cyan.com.githubexplorer.model.data.Contributor

class ContributorsListPresenterImpl(
    private val contributors: List<Contributor>
) : ContributorsListPresenter {
    private val viewTypes: MutableList<Triple<Int, Boolean, String>> = ArrayList()

    init {
        groupByFirstLetter()
    }

    override fun bindView(position: Int, view: ContributorsListView.ContributorsViewHolderView) {
        if (isHeader(position)) {
            view.onBindView(viewTypes[getPosition(position)].third.toUpperCase())
        } else {
            val index = getPosition(position)
            val contributor = contributors[index]
            view.onBindView(contributor.login.orEmpty(), contributor.contributions.toString())
            displayAvatar(position, view)
        }
    }

    override fun getCount(): Int = viewTypes.size

    override fun displayAvatar(position: Int, view: ContributorsListView.ContributorsViewHolderView) {
        view.onDisplayAvatar(contributors[getPosition(position)].avatar_url.orEmpty())
    }

    override fun getViewType(position: Int): Int {
        return if (isHeader(position)) {
            ContributorsListAdapter.VIEW_TYPE_HEADER
        } else {
            ContributorsListAdapter.VIEW_TYPE_CONTRIBUTOR
        }
    }

    override fun groupByFirstLetter() {
        var lastLetter = ""
        viewTypes.clear()

        for(i: Int in 0 until contributors.size) {
            val it = contributors[i]
            val header = getHeaderFromString(it.login.orEmpty())

            if (getHeaderFromString(it.login.orEmpty()) != lastLetter) {
                lastLetter = header
                viewTypes.add(Triple(i, true, header))
            }
            viewTypes.add(Triple(i, false, header))
        }
    }

    private fun getHeaderFromString(value: String) = value.first().toLowerCase().toString()

    private fun isHeader(position: Int) = viewTypes[position].second

    private fun getPosition(position: Int): Int {
        return if (isHeader(position)) {
            position
        } else {
            viewTypes[position].first
        }
    }
}