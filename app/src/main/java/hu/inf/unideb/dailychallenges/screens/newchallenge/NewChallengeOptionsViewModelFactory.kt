package hu.inf.unideb.dailychallenges.screens.newchallenge

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class NewChallengeOptionsViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewChallengeOptionsViewModel::class.java)) {
            Log.i("DailyChallenges", "NewChallengeOptionsViewModelFactory - NewChallengeViewModelFactory()")
            return NewChallengeOptionsViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}