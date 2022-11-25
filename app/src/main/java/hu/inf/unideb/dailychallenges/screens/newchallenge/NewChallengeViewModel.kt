package hu.inf.unideb.dailychallenges.screens.newchallenge

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.inf.unideb.dailychallenges.database.DailyChallengesDAO

class NewChallengeViewModel(
    dataSource: DailyChallengesDAO,
    application: Application
) : ViewModel() {
    val database = dataSource
    val categories = dataSource.getAllCategories()
    val app = application

    private val _navigateToNewChallengeOptions = MutableLiveData<String?>()

    val navigateToNewChallengeOptions get() = _navigateToNewChallengeOptions

    fun onCategoryClicked(categoryName: String?){
        _navigateToNewChallengeOptions.value = categoryName
    }

    fun onCategoriesToOptionsNavigated(){
        _navigateToNewChallengeOptions.value = null
    }
}