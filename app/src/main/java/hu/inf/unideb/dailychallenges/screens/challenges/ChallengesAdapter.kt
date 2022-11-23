package hu.inf.unideb.dailychallenges.screens.challenges

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.inf.unideb.dailychallenges.database.DailyChallenges
import hu.inf.unideb.dailychallenges.databinding.ChallengesListItemBinding

class ChallengesAdapter :
    ListAdapter<DailyChallenges, ChallengesAdapter.ViewHolder>(ChallengesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(private val binding: ChallengesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DailyChallenges) {
            binding.newchallengeItemText.text = item.challengeType
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ChallengesListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class ChallengesDiffCallback : DiffUtil.ItemCallback<DailyChallenges>() {
        override fun areItemsTheSame(
            oldItem: DailyChallenges,
            newItem: DailyChallenges
        ): Boolean {
            return oldItem.challengeID == newItem.challengeID
        }

        override fun areContentsTheSame(
            oldItem: DailyChallenges,
            newItem: DailyChallenges
        ): Boolean {
            return oldItem == newItem
        }
    }
}