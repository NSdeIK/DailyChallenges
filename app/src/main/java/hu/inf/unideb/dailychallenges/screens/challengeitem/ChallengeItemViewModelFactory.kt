package hu.inf.unideb.dailychallenges.screens.challengeitem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.inf.unideb.dailychallenges.database.DailyChallengesDAO
import java.lang.IllegalArgumentException

class ChallengeItemViewModelFactory (
    private val challengeIdKey: Long,
    private val dataSource: DailyChallengesDAO
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChallengeItemViewModel::class.java)) {
            return ChallengeItemViewModel(challengeIdKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}