package hu.inf.unideb.dailychallenges.screens.challenges

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.inf.unideb.dailychallenges.R
import hu.inf.unideb.dailychallenges.database.DailyChallenges
import hu.inf.unideb.dailychallenges.databinding.ChallengesListItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.ClassCastException

private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_ITEM = 1

class ChallengesAdapter(private val clickListener: ChallengesListener) :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(ChallengesDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.ChallengesItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
            ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> ViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType !")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is ViewHolder -> {
                val challengeItem = getItem(position) as DataItem.ChallengesItem
                holder.bind(challengeItem.challenge, clickListener)
            }
        }
    }

    fun addHeaderAndSubmitList(list: List<DailyChallenges>){
        adapterScope.launch {
            val items = when(list){
                null -> listOf(DataItem.Header)
                else -> listOf(DataItem.Header) + list.map {DataItem.ChallengesItem(it) }
            }
            withContext(Dispatchers.Main){
                submitList(items)
            }
        }
    }

    class TextViewHolder(view: View) : RecyclerView.ViewHolder(view){
        companion object {
            fun from(parent: ViewGroup): TextViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.header, parent, false)
                return TextViewHolder(view)
            }
        }
    }

    class ViewHolder private constructor(val binding: ChallengesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DailyChallenges, clickListener: ChallengesListener) {
            binding.challenge = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ChallengesListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class ChallengesDiffCallback : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(
        oldItem: DataItem,
        newItem: DataItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: DataItem,
        newItem: DataItem
    ): Boolean {
        return oldItem == newItem
    }
}

class ChallengesListener(val clickListener: (id: Long) -> Unit){
    fun onClick(challenge: DailyChallenges) = clickListener(challenge.challengeID)
}

sealed class DataItem {
    abstract val id: Long

    data class ChallengesItem(val challenge: DailyChallenges) : DataItem(){
        override val id = challenge.challengeID
    }

    object Header : DataItem(){
        override val id = Long.MIN_VALUE
    }
}