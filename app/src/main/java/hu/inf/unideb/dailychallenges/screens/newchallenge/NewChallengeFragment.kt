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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import hu.inf.unideb.dailychallenges.R
import hu.inf.unideb.dailychallenges.database.DailyChallengesCategories
import hu.inf.unideb.dailychallenges.database.DailyChallengesDatabase
import hu.inf.unideb.dailychallenges.databinding.FragmentNewchallengeBinding

class NewChallengeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.i("DailyChallenges", "NewChallengeFragment - onCreateView()")
        val binding: FragmentNewchallengeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_newchallenge, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = DailyChallengesDatabase.getInstance(application).dailyChallengesDAO

        val viewModelFactory = NewChallengeViewModelFactory(dataSource, application)
        val newChallengeViewModel =
            ViewModelProvider(this, viewModelFactory)[NewChallengeViewModel::class.java]

        binding.newChallengeViewModel = newChallengeViewModel;

        val challengeAdapter = NewChallengeAdapter(object : OnAdapterListener {
            override fun onClick(categories: DailyChallengesCategories) {
                Log.i("DailyChallenges","NewChallengeFragment - challengeAdapter - onClick()")
                newChallengeViewModel.onCategoryClicked(categories.categoryName)
            }
        })

        val manager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        binding.newChallengeRecycleView.layoutManager = manager
        binding.newChallengeRecycleView.adapter = challengeAdapter

        newChallengeViewModel.categories.observe(viewLifecycleOwner, Observer {
            it?.let {
                challengeAdapter.submitList(it)
            }
        })
        binding.lifecycleOwner = viewLifecycleOwner

        newChallengeViewModel.navigateToNewChallengeOptions.observe(viewLifecycleOwner, Observer { newchallenge ->
            newchallenge?.let{
                this.findNavController().navigate(
                    NewChallengeFragmentDirections.actionNewChallengeFragmentIDToNewchallengeOptions(newchallenge))
                    newChallengeViewModel.onCategoriesToOptionsNavigated()
            }
        })

        return binding.root
    }

}