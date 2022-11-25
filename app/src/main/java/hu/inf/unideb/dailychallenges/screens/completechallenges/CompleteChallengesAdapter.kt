package hu.inf.unideb.dailychallenges.screens.completechallenges

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.inf.unideb.dailychallenges.R
import hu.inf.unideb.dailychallenges.database.DailyChallenges
import hu.inf.unideb.dailychallenges.databinding.CompleteChallengesListItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.ClassCastException

private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_ITEM = 1

class CompleteChallengesAdapter(private val clickListener: CompleteChallengesListener) :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(CompleteChallengesDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.CompleteChallengesItem -> ITEM_VIEW_TYPE_ITEM
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
                val challengeItem = getItem(position) as DataItem.CompleteChallengesItem
                holder.bind(challengeItem.challenge, clickListener)
            }
        }
    }

    fun addHeaderAndSubmitList(list: List<DailyChallenges>){
        adapterScope.launch {
            val items = when(list){
                null -> listOf(DataItem.Header)
                else -> listOf(DataItem.Header) + list.map {DataItem.CompleteChallengesItem(it) }
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
                val view = layoutInflater.inflate(R.layout.header2, parent, false)
                return TextViewHolder(view)
            }
        }
    }

    class ViewHolder private constructor(val binding: CompleteChallengesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DailyChallenges, clickListener: CompleteChallengesListener) {
            binding.challenge = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CompleteChallengesListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class CompleteChallengesDiffCallback : DiffUtil.ItemCallback<DataItem>() {
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

class CompleteChallengesListener(val clickListener: (id: Long) -> Unit){
    fun onClick(challenge: DailyChallenges) = clickListener(challenge.challengeID)
}

sealed class DataItem {
    abstract val id: Long

    data class CompleteChallengesItem(val challenge: DailyChallenges) : DataItem(){
        override val id = challenge.challengeID
    }

    object Header : DataItem(){
        override val id = Long.MIN_VALUE
    }
}