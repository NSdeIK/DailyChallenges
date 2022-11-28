package hu.inf.unideb.dailychallenges.screens.challengeitem

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.inf.unideb.dailychallenges.database.DailyChallenges
import hu.inf.unideb.dailychallenges.database.DailyChallengesDAO
import kotlinx.coroutines.launch

class ChallengeItemViewModel (
    challengeIdKey: Long = 0L,
    dataSource: DailyChallengesDAO) : ViewModel() {

    val database = dataSource

    private val challenge: LiveData<DailyChallenges> = database.getChallengeId(challengeIdKey)

    fun getChallenge() = challenge

    private suspend fun remove(){
        database.removeChallengeItem(challenge.value!!.challengeID)
    }

    private suspend fun update(){
        database.update(challenge.value!!)
    }

    fun updateChallengeItem(boolean : Boolean){
        challenge.value!!.challengeDone = boolean
        viewModelScope.launch { update() }
    }

    fun removeItem(){
        viewModelScope.launch { remove() }
    }

}