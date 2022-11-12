package hu.inf.unideb.dailychallenges.screens.newchallenge

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.inf.unideb.dailychallenges.database.DailyChallengesCategories
import hu.inf.unideb.dailychallenges.databinding.NewchallengeListItemBinding

class NewChallengeAdapter :
    ListAdapter<DailyChallengesCategories, NewChallengeAdapter.ViewHolder>(NewChallengeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i("DailyChallenges","NewChallengeAdapter - onCreateViewHolder")
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("DailyChallenges","NewChallengeAdapter - onBindViewHolder")
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(private val bindingList: NewchallengeListItemBinding) : RecyclerView.ViewHolder(bindingList.root)
    {
        fun bind(item: DailyChallengesCategories)
        {
            Log.i("DailyChallenges","NewChallengeAdapter - ViewHolder - bind()")
            bindingList.newchallengeItemText.text = item.categoryName
            bindingList.newchallengeItemImage.setImageResource(item.categoryImage)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                Log.i("DailyChallenges","NewChallengeAdapter - ViewHolder - from()")
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
        Log.i("DailyChallenges","NewChallengeAdapter - DiffCallback - areItemsTheSame()")
        return oldItem.categoryID == newItem.categoryID
    }

    override fun areContentsTheSame(
        oldItem: DailyChallengesCategories,
        newItem: DailyChallengesCategories
    ): Boolean {
        Log.i("DailyChallenges","NewChallengeAdapter - DiffCallback - areContentsTheSame()")
        return oldItem == newItem
    }
}