package hu.inf.unideb.dailychallenges.screens.challenges

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.inf.unideb.dailychallenges.database.DailyChallengesDAO
import java.lang.IllegalArgumentException

class ChallengesViewModelFactory (
    private val dataSource: DailyChallengesDAO,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChallengesViewModel::class.java)) {
            return ChallengesViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}