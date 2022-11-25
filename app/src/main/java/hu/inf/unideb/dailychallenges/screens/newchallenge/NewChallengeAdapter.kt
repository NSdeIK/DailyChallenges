package hu.inf.unideb.dailychallenges.screens.newchallenge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.inf.unideb.dailychallenges.database.DailyChallengesCategories
import hu.inf.unideb.dailychallenges.databinding.NewchallengeListItemBinding

class NewChallengeAdapter(private val clickListener: OnAdapterListener) :
    ListAdapter<DailyChallengesCategories, NewChallengeAdapter.ViewHolder>(NewChallengeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener{
            clickListener.onClick(item)
        }
    }

    class ViewHolder private constructor(private val bindingList: NewchallengeListItemBinding) : RecyclerView.ViewHolder(bindingList.root)
    {
        fun bind(item: DailyChallengesCategories)
        {
            bindingList.category = item
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NewchallengeListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}

class NewChallengeDiffCallback : DiffUtil.ItemCallback<DailyChallengesCategories>(){
    override fun areItemsTheSame(
        oldItem: DailyChallengesCategories,
        newItem: DailyChallengesCategories
    ): Boolean {
        return oldItem.categoryID == newItem.categoryID
    }

    override fun areContentsTheSame(
        oldItem: DailyChallengesCategories,
        newItem: DailyChallengesCategories
    ): Boolean {
        return oldItem == newItem
    }
}

interface OnAdapterListener {
    fun onClick(categories: DailyChallengesCategories)
}