package hu.inf.unideb.dailychallenges.screens.challenges

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.inf.unideb.dailychallenges.database.DailyChallengesCategories
import hu.inf.unideb.dailychallenges.database.DailyChallengesDAO
import kotlinx.coroutines.launch

class ChallengesViewModel(
    dataSource: DailyChallengesDAO,
    application: Application
) : ViewModel() {
    val database = dataSource
    val challenges = dataSource.getAllChallenges()

    private val _navigateToChallengeItem = MutableLiveData<Long?>()
    val navigateToChallengeItem
        get() = _navigateToChallengeItem

    fun onChallengeClicked(id: Long) {
        _navigateToChallengeItem.value = id
    }

    fun onChallengeItemNavigated() {
        _navigateToChallengeItem.value = null
    }

}