package hu.inf.unideb.dailychallenges.screens.challengeitem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import hu.inf.unideb.dailychallenges.R
import hu.inf.unideb.dailychallenges.database.DailyChallengesDatabase
import hu.inf.unideb.dailychallenges.databinding.FragmentChallengeitemBinding

class ChallengeItemFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentChallengeitemBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_challengeitem, container, false)

        val application = requireNotNull(this.activity).application
        val args = ChallengeItemFragmentArgs.fromBundle(requireArguments())
        val dataSource = DailyChallengesDatabase.getInstance(application).dailyChallengesDAO
        val viewModelFactory = ChallengeItemViewModelFactory(args.challengeId, dataSource)

        val challengeItemViewModel =
            ViewModelProvider(this, viewModelFactory)[ChallengeItemViewModel::class.java]

        binding.challengeItemViewModel = challengeItemViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.completeSwitch.setOnCheckedChangeListener { _, isChecked ->
            challengeItemViewModel.updateChallengeItem(isChecked)
            if(isChecked)
            {
                Toast.makeText(context,"You've completed!", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context,"You didn't complete!", Toast.LENGTH_SHORT).show()
            }

        }

        binding.removeButton.setOnClickListener {
            view : View ->
                challengeItemViewModel.removeItem()
                Toast.makeText(context,"You removed it!", Toast.LENGTH_SHORT).show()
                view.findNavController().navigateUp()
        }

        return binding.root
    }


}