package hu.inf.unideb.dailychallenges.screens.newchallenge

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewChallengeOptionsViewModel(application: Application) : ViewModel() {
    val app = application;

    private val _categoryName = MutableLiveData<String?>()

    val getCategoryName : MutableLiveData<String?> get() = _categoryName

    fun setCategoryName(categoryName: String?){
        _categoryName.value = categoryName
    }



}