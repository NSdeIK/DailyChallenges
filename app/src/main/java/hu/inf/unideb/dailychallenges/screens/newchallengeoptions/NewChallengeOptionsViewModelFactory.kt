package hu.inf.unideb.dailychallenges.screens.newchallengeoptions

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.inf.unideb.dailychallenges.database.DailyChallengesDAO
import java.lang.IllegalArgumentException

class NewChallengeOptionsViewModelFactory(
    private val dataSource: DailyChallengesDAO,
    private val categoryName: String
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewChallengeOptionsViewModel::class.java)) {
            return NewChallengeOptionsViewModel(dataSource, categoryName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}