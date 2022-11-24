package hu.inf.unideb.dailychallenges.screens.newchallenge

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hu.inf.unideb.dailychallenges.R
import hu.inf.unideb.dailychallenges.database.DailyChallenges
import hu.inf.unideb.dailychallenges.database.DailyChallengesDAO
import hu.inf.unideb.dailychallenges.database.DailyChallengesDatabase
import hu.inf.unideb.dailychallenges.databinding.FragmentNewchallengeOptionsBinding

class NewChallengeOptionsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentNewchallengeOptionsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_newchallenge_options, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = DailyChallengesDatabase.getInstance(application).dailyChallengesDAO
        val viewModelFactory = NewChallengeOptionsViewModelFactory(dataSource, application)
        val viewModel =
            ViewModelProvider(this, viewModelFactory)[NewChallengeOptionsViewModel::class.java]


        binding.lifecycleOwner = viewLifecycleOwner

        //Set categoryName
        viewModel.setCategoryName(NewChallengeOptionsFragmentArgs.fromBundle(requireArguments()).categoryName)

        viewModel.getCategoryName.observe(viewLifecycleOwner, Observer{
            binding.optionsCategoryName.text = it.toString()
        })

        viewModel.response.observe(viewLifecycleOwner, Observer{
            binding.activityText.text = it.toString()
        })

        binding.generateButton.setOnClickListener {
            binding.generateButton.isEnabled = false
            binding.saveButton.isEnabled = false
            binding.generateButton.text = "Renew"
            binding.saveButton.setBackgroundColor(Color.parseColor("#C8E6C9"))
            binding.generateButton.setBackgroundColor(Color.parseColor("#B3E5FC"))
            binding.generateButton.postDelayed({
                binding.generateButton.isEnabled = true
                binding.saveButton.isEnabled = true
                binding.saveButton.setBackgroundColor(Color.parseColor("#86E489"))
                binding.generateButton.setBackgroundColor(Color.parseColor("#29B6F6"))
            },2000)
            val categoryName : String = viewModel.getCategoryName.value.toString()
            viewModel.getActivityAPI(categoryName);

        }

        binding.saveButton.setOnClickListener{
            val exists = viewModel.checkData()
            if(!exists)
            {
                viewModel.insert()
                Toast.makeText(context,"Saved!",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context,"This activity already exists! Try another challenge!",Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

}