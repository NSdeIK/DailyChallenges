package hu.inf.unideb.dailychallenges

import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.BindingAdapter
import hu.inf.unideb.dailychallenges.database.DailyChallenges
import hu.inf.unideb.dailychallenges.database.DailyChallengesCategories

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

@BindingAdapter("categoryText")
fun TextView.setCategoryText(item : DailyChallengesCategories?){
    item?.let{
        text = item.categoryName
    }
}

@BindingAdapter("categoryIcon")
fun ImageView.setCategoryIcon(item : DailyChallengesCategories?){
    item?.let{
        setImageResource(item.categoryImage)
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