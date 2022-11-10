package hu.inf.unideb.dailychallenges.screens.newchallenge

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.inf.unideb.dailychallenges.R


class NewChallengeAdapter(
    newChallengeModelArrayList: ArrayList<NewChallengeModel>
) :
    RecyclerView.Adapter<NewChallengeAdapter.ViewHolder>() {

    private val newChallengeModelArrayList: ArrayList<NewChallengeModel>
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewChallengeAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.newchallenge_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewChallengeAdapter.ViewHolder, position: Int) {
        val model: NewChallengeModel = newChallengeModelArrayList[position]
        holder.newChallengeType.text = model.getChallenge_type()
        holder.newChallengeImage.setImageResource(model.getChallenge_image())
    }

    override fun getItemCount(): Int {
        return newChallengeModelArrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newChallengeType : TextView
        val newChallengeImage : ImageView
        init {
            newChallengeType = itemView.findViewById(R.id.newchallenge_item_text)
            newChallengeImage = itemView.findViewById(R.id.newchallenge_item_image)
        }
    }

    init {
        this.newChallengeModelArrayList = newChallengeModelArrayList
    }
}