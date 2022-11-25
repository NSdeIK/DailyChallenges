package hu.inf.unideb.dailychallenges.screens.challenges

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import hu.inf.unideb.dailychallenges.database.DailyChallenges
import hu.inf.unideb.dailychallenges.database.DailyChallengesDAO

class ChallengesViewModel(
    dataSource: DailyChallengesDAO,
    application: Application
) : ViewModel() {
    val database = dataSource
    private val challenges : LiveData<List<DailyChallenges>>

    fun getChallenges() = challenges

    init {
        challenges = Transformations.map(database.getAllChallenges()){ it }
    }

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