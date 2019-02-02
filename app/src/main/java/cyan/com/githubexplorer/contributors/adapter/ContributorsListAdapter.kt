package cyan.com.githubexplorer.contributors.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import cyan.com.githubexplorer.R
import kotlinx.android.synthetic.main.view_adapter_contributor.view.*

class ContributorsListAdapter(
    private val presenter: ContributorsListPresenter
) : RecyclerView.Adapter<ContributorsListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.view_adapter_contributor,
            parent,
            false))
    }

    override fun getItemCount(): Int = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(position, holder)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), ContributorsListView {
        override fun onBindView(username: String, contributions: String) {
            itemView.apply {
                contributorTextView.text = username
                contributionTextView.text = contributions
            }
        }

        override fun onDisplayAvatar(avatar: String) {
            Glide.with(itemView.context).load(avatar).into(itemView.avatarImageView)
        }
    }
}