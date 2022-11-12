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

}