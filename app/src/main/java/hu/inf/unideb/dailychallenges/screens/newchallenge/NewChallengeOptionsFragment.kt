package hu.inf.unideb.dailychallenges.screens.newchallenge

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hu.inf.unideb.dailychallenges.R
import hu.inf.unideb.dailychallenges.databinding.FragmentNewchallengeOptionsBinding

class NewChallengeOptionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.i("DailyChallenges", "NewChallengeOptionsFragment - onCreateView()")
        val binding: FragmentNewchallengeOptionsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_newchallenge_options, container, false)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = NewChallengeOptionsViewModelFactory(application)
        val optionsViewModel = ViewModelProvider(this,viewModelFactory)[NewChallengeOptionsViewModel::class.java]

        binding.lifecycleOwner = viewLifecycleOwner
        //Set categoryName
        optionsViewModel.setCategoryName(NewChallengeOptionsFragmentArgs.fromBundle(requireArguments()).categoryName)

        optionsViewModel.getCategoryName.observe(viewLifecycleOwner, Observer{
            binding.optionsCategoryName.text = it.toString()
        })

        Log.i("DailyChallenges", optionsViewModel.getCategoryName.toString())

        return binding.root
    }
}