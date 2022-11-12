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
import hu.inf.unideb.dailychallenges.database.DailyChallengesDatabase
import hu.inf.unideb.dailychallenges.databinding.FragmentNewchallengeBinding

class NewChallengeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.i("DailyChallenges","NewChallengeFragment - onCreateView()")
        val binding: FragmentNewchallengeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_newchallenge, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = DailyChallengesDatabase.getInstance(application).dailyChallengesDAO

        val viewModelFactory = NewChallengeViewModelFactory(dataSource,application)
        val challengesViewModel = ViewModelProvider(this,viewModelFactory)[NewChallengeViewModel::class.java]

        binding.newChallengeViewModel = challengesViewModel;

        binding.lifecycleOwner = viewLifecycleOwner

        val challengeAdapter = NewChallengeAdapter()

        binding.newChallengeRecycleView.adapter = challengeAdapter
        challengesViewModel.categories.observe(viewLifecycleOwner, Observer {
            it?.let {
                challengeAdapter.submitList(it)
            }
        })
        return binding.root
    }


}