package hu.inf.unideb.dailychallenges.screens.completechallenges

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import hu.inf.unideb.dailychallenges.R
import hu.inf.unideb.dailychallenges.database.DailyChallengesDatabase
import hu.inf.unideb.dailychallenges.databinding.FragmentCompletechallengesBinding

class CompleteChallengesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentCompletechallengesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_completechallenges, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = DailyChallengesDatabase.getInstance(application).dailyChallengesDAO

        val viewModelFactory = CompleteChallengesViewModelFactory(dataSource,application)
        val completeChallengesViewModel = ViewModelProvider(this,viewModelFactory)[CompleteChallengesViewModel::class.java]

        binding.completeChallengesViewModel = completeChallengesViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val challengesAdapter = CompleteChallengesAdapter(CompleteChallengesListener { _challengeId ->
            this.findNavController().navigate(
                CompleteChallengesFragmentDirections.actionCompleteChallengesFragmentToChallengeItemFragment(_challengeId)
            )
        })

        val manager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int) = when (position) {
                0 -> 3
                else -> 1
            }
        }
        binding.challengesRecycleView.layoutManager = manager
        binding.challengesRecycleView.adapter = challengesAdapter

        completeChallengesViewModel.challenges.observe(viewLifecycleOwner) {
            it?.let {
                challengesAdapter.addHeaderAndSubmitList(it)
            }
        }

        return binding.root
    }

}