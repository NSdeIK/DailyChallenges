package hu.inf.unideb.dailychallenges.screens.newchallengeoptions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import hu.inf.unideb.dailychallenges.R
import hu.inf.unideb.dailychallenges.database.DailyChallengesDatabase
import hu.inf.unideb.dailychallenges.databinding.FragmentNewchallengeOptionsBinding

class NewChallengeOptionsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentNewchallengeOptionsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_newchallenge_options, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = DailyChallengesDatabase.getInstance(application).dailyChallengesDAO
        val args = NewChallengeOptionsFragmentArgs.fromBundle(requireArguments()).categoryName
        val viewModelFactory = NewChallengeOptionsViewModelFactory(dataSource, args, binding)
        val viewModel =
            ViewModelProvider(this, viewModelFactory)[NewChallengeOptionsViewModel::class.java]

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getCategory.observe(viewLifecycleOwner) {
            binding.optionsCategoryName.text = it.toString()
        }

        viewModel.response.observe(viewLifecycleOwner) {
            binding.activityText.text = it.toString()
            binding.saveButton.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.green))
            binding.generateButton.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.blue))
        }

        binding.generateButton.setOnClickListener {
            viewModel.disableButton()
            binding.generateButton.text = "Renew"
            binding.saveButton.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.lightgreen))
            binding.generateButton.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.lightblue))
            viewModel.getActivityAPI()
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
            binding.saveButton.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.lightgreen))
            viewModel.saveDisableButton()
        }

        return binding.root
    }

}