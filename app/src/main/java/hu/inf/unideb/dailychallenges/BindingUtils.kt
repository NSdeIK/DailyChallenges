package hu.inf.unideb.dailychallenges

import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.BindingAdapter
import hu.inf.unideb.dailychallenges.database.DailyChallenges

@BindingAdapter("activityText")
fun TextView.setActivityText(item : DailyChallenges?){
    item?.let{
        text = item.challengeActivityText
    }

}

@BindingAdapter("activityIcon")
fun ImageView.setActivityIcon(item : DailyChallenges?){
    item?.let{
        setImageResource(item.challengeIcon)
    }
}

@BindingAdapter("activityComplete")
fun SwitchCompat.setActivityComplete(item: DailyChallenges?){
    item?.let {
        when(item.challengeDone)
        {
            true -> {
                isChecked = true
                textOn = "Not ready"
            }
            false -> {
                isChecked = false
                textOff = "Done"
            }
        }
    }
}