package hu.inf.unideb.dailychallenges.screens.newchallenge

import android.app.Application
import androidx.lifecycle.ViewModel
import hu.inf.unideb.dailychallenges.database.DailyChallengesDAO

class NewChallengeViewModel(
    dataSource: DailyChallengesDAO,
    application: Application
) : ViewModel() {
    val database = dataSource

    val categories = dataSource.getAllCategories();

}