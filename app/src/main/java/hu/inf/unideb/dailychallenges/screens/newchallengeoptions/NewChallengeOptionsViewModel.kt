package hu.inf.unideb.dailychallenges.screens.newchallengeoptions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.inf.unideb.dailychallenges.database.DailyChallenges
import hu.inf.unideb.dailychallenges.database.DailyChallengesDAO
import hu.inf.unideb.dailychallenges.databinding.FragmentNewchallengeOptionsBinding
import hu.inf.unideb.dailychallenges.restapi.BoredService
import kotlinx.coroutines.launch
import java.util.*

class NewChallengeOptionsViewModel(
    dataSource: DailyChallengesDAO,
    categoryName: String,
    binding: FragmentNewchallengeOptionsBinding
) : ViewModel() {

    private val dao = dataSource
    private val binding = binding

    private val _categoryName = MutableLiveData<String?>()
    private val _response = MutableLiveData<String?>()

    val response: LiveData<String?>
        get() = _response

    val getCategory: LiveData<String?>
        get() = _categoryName

    init {
        _categoryName.value = categoryName
        getActivityAPI()
    }

    override fun onCleared() {
        _response.value = null
        _categoryName.value = null
        super.onCleared()
    }

    fun getActivityAPI(){
        viewModelScope.launch {
            try{
                val response = BoredService.boredInterface.getActivity(_categoryName.value!!.lowercase(Locale.getDefault()))
                if(response.body()?.error.equals(null))
                {
                    _response.value = response.body()?.activity.toString()
                    enableButton()
                }
                else{
                    throw Exception()
                }
            }catch(e: Exception)
            {
                _response.value = "No activity found with the specified parameters"
                saveDisableButton()
            }
        }
    }
    fun checkData(): Boolean {
        return dao.checkExists(response.value.toString())
    }

    private fun enableButton(){
        binding.generateButton.isEnabled = true
        binding.saveButton.isEnabled = true
    }

    fun disableButton(){
        binding.generateButton.isEnabled = false
        binding.saveButton.isEnabled = false
    }

    fun saveDisableButton(){
        binding.generateButton.isEnabled = true
        binding.saveButton.isEnabled = false
    }


    fun insert(){
        viewModelScope.launch {
            val getImageSrc : Int = dao.getImageSrc(_categoryName.value!!)
            val dailyChallenges = DailyChallenges(
                challengeActivityText = response.value,
                challengeType = _categoryName.value!!,
                challengeDone = false,
                challengeIcon = getImageSrc)

            dao.insert(dailyChallenges)
        }
    }
}