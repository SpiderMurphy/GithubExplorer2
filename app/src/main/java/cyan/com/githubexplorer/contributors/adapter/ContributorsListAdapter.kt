package cyan.com.githubexplorer.contributors.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import cyan.com.githubexplorer.R
import kotlinx.android.synthetic.main.view_adapter_contributor.view.*
import kotlinx.android.synthetic.main.view_adapter_contributor_header.view.*

class ContributorsListAdapter(
    private val presenter: ContributorsListPresenter
) : RecyclerView.Adapter<ContributorsListAdapter.ViewHolder>() {
    companion object {
        const val VIEW_TYPE_CONTRIBUTOR = 1
        const val VIEW_TYPE_HEADER = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when(viewType) {
            VIEW_TYPE_HEADER ->
                ViewHolderHeader(LayoutInflater.from(parent.context).inflate(
                    R.layout.view_adapter_contributor_header,
                    parent,
                    false))
            else ->
                ViewHolder(LayoutInflater.from(parent.context).inflate(
                    R.layout.view_adapter_contributor,
                    parent,
                    false))
        }
    }

    override fun getItemCount(): Int = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(position, holder)
    }

    override fun getItemViewType(position: Int): Int {
        return presenter.getViewType(position)
    }

    open inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), ContributorsListView.ContributorsViewHolderView {
        override fun onBindView(username: String, contributions: String) {
            itemView.apply {
                contributorTextView.text = username
                contributionTextView.text = contributions
            }
        }

        override fun onDisplayAvatar(avatar: String) {
            Glide.with(itemView.context).load(avatar).into(itemView.avatarImageView)
        }

        override fun onBindView(header: String) {
        }
    }

    inner class ViewHolderHeader(view: View) : ViewHolder(view) {
        override fun onBindView(header: String) {
            itemView.letterTextView.text = header
        }
    }
}