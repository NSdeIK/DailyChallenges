package hu.inf.unideb.dailychallenges.screens.newchallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hu.inf.unideb.dailychallenges.R
import hu.inf.unideb.dailychallenges.databinding.FragmentNewchallengeBinding

class NewChallengeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentNewchallengeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_newchallenge, container, false)

        val newChallengeList: ArrayList<NewChallengeModel> = ArrayList<NewChallengeModel>()
        newChallengeList.add(NewChallengeModel("Education", R.drawable.ic_education))
        newChallengeList.add(NewChallengeModel("Recreational", R.drawable.ic_recreational))
        newChallengeList.add(NewChallengeModel("Music", R.drawable.ic_music))

        newChallengeList.add(NewChallengeModel("Diy", R.drawable.ic_diy))
        newChallengeList.add(NewChallengeModel("Social", R.drawable.ic_social))
        newChallengeList.add(NewChallengeModel("Cooking", R.drawable.ic_cooking))

        newChallengeList.add(NewChallengeModel("Relaxation", R.drawable.ic_relaxation))
        newChallengeList.add(NewChallengeModel("Charity", R.drawable.ic_charity))
        newChallengeList.add(NewChallengeModel("Busywork", R.drawable.ic_busywork))

        val challengeAdapter = NewChallengeAdapter(newChallengeList)
        binding.newChallengeRecycleView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = challengeAdapter
        }

        return binding.root
    }


}