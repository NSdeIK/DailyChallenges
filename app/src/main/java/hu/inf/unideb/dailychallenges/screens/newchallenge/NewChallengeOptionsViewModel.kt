package hu.inf.unideb.dailychallenges.screens.newchallenge

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.inf.unideb.dailychallenges.database.DailyChallenges
import hu.inf.unideb.dailychallenges.database.DailyChallengesDAO
import hu.inf.unideb.dailychallenges.restapi.BoredService
import kotlinx.coroutines.launch

class NewChallengeOptionsViewModel(
    dataSource: DailyChallengesDAO,
    application: Application
) : ViewModel() {

    private val dao = dataSource

    private val _categoryName = MutableLiveData<String?>()
    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    val getCategoryName : MutableLiveData<String?> get() = _categoryName

    fun setCategoryName(categoryName: String?){
        _categoryName.value = categoryName
    }

    fun getActivityAPI(type : String){
        viewModelScope.launch {
            try{
                val response = BoredService.boredInterface.getActivity(type.lowercase())
                if(response.body()?.error.equals(null))
                {
                    _response.value = response.body()?.activity.toString()
                }
                else{
                    throw Exception()
                }
            }catch(e: Exception)
            {
                _response.value = "No activity found with the specified parameters"
            }
        }
    }
    fun checkData(): Boolean {
        return dao.checkExists(response.value.toString())
    }

    fun insert(){
        val getImageSrc : Int = dao.getImageSrc(getCategoryName.value.toString())
        val dailyChallenges = DailyChallenges(
            challengeActivityText = response.value,
            challengeType = getCategoryName.value,
            challengeDone = false,
            challengeIcon = getImageSrc
        )
        dao.insert(dailyChallenges)
    }
}