package hu.inf.unideb.dailychallenges.screens.challenges

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import hu.inf.unideb.dailychallenges.R
import hu.inf.unideb.dailychallenges.database.DailyChallengesDatabase
import hu.inf.unideb.dailychallenges.databinding.FragmentChallengesBinding

class ChallengesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentChallengesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_challenges, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = DailyChallengesDatabase.getInstance(application).dailyChallengesDAO

        val viewModelFactory = ChallengesViewModelFactory(dataSource,application)
        val challengesViewModel = ViewModelProvider(this,viewModelFactory)[ChallengesViewModel::class.java]

        binding.challengesViewModel = challengesViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val challengesAdapter = ChallengesAdapter(ChallengesListener { _challengeId ->
            challengesViewModel.onChallengeClicked(_challengeId)
        })

        challengesViewModel.navigateToChallengeItem.observe(viewLifecycleOwner) { challengeId ->
            challengeId?.let {
                this.findNavController().navigate(
                    ChallengesFragmentDirections.actionChallengesFragmentSTARTToChallengeItemFragment(
                        challengeId
                    )
                )
                challengesViewModel.onChallengeItemNavigated()
            }
        }

        val manager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int) = when (position) {
                0 -> 3
                else -> 1
            }
        }
        binding.challengesRecycleView.layoutManager = manager
        binding.challengesRecycleView.adapter = challengesAdapter

        challengesViewModel.getChallenges().observe(viewLifecycleOwner) {
            challengesAdapter.addHeaderAndSubmitList(it)
            it?.let {
                challengesAdapter.addHeaderAndSubmitList(it)
            }
        }

        binding.completedChallengesButton.setOnClickListener{
            view: View -> view.findNavController().navigate(ChallengesFragmentDirections.actionChallengesFragmentSTARTToCompleteChallengesFragment())
        }

        binding.newChallengeButton.setOnClickListener {
            view: View -> view.findNavController().navigate(ChallengesFragmentDirections.actionChallengesFragmentSTARTToNewChallengeFragment2())
        }


        return binding.root
    }

}