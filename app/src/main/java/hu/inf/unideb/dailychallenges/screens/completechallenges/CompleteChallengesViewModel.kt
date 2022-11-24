package hu.inf.unideb.dailychallenges.screens.completechallenges

import android.app.Application
import androidx.lifecycle.ViewModel
import hu.inf.unideb.dailychallenges.database.DailyChallengesDAO

class CompleteChallengesViewModel(
    dataSource: DailyChallengesDAO,
    application: Application
) : ViewModel() {
    val database = dataSource
    val challenges = dataSource.getAllCompletedChallenges()
}