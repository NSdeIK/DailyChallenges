package hu.inf.unideb.dailychallenges.screens.newchallenge

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.inf.unideb.dailychallenges.database.DailyChallengesDAO
import java.lang.IllegalArgumentException

class NewChallengeViewModelFactory(
    private val dataSource: DailyChallengesDAO,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewChallengeViewModel::class.java)) {
            Log.i("DailyChallenges","NewChallengeViewModelFactory - NewChallengeViewModelFactory()")
            return NewChallengeViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}