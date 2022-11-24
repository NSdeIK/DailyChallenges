package hu.inf.unideb.dailychallenges.screens.challengeitem

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.inf.unideb.dailychallenges.database.DailyChallenges
import hu.inf.unideb.dailychallenges.database.DailyChallengesDAO

class ChallengeItemViewModel (
    private val challengeIdKey: Long = 0L,
    private val dataSource: DailyChallengesDAO) : ViewModel() {

    val database = dataSource

    private val challenge: LiveData<DailyChallenges> = database.getChallengeId(challengeIdKey)

    fun getChallenge() = challenge

    fun updateChallengeItem(boolean : Boolean){
        challenge.value.let {
            if (it != null) {
                it.challengeDone = boolean
                database.update(it)
            }
        }

    }
}