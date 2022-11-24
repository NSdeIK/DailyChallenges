package hu.inf.unideb.dailychallenges.screens.completechallenges

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.inf.unideb.dailychallenges.database.DailyChallengesDAO
import java.lang.IllegalArgumentException

class CompleteChallengesViewModelFactory (
    private val dataSource: DailyChallengesDAO,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CompleteChallengesViewModel::class.java)) {
            return CompleteChallengesViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}