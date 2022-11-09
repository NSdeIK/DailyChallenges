package hu.inf.unideb.dailychallenges.screens.challenges

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import hu.inf.unideb.dailychallenges.R
import hu.inf.unideb.dailychallenges.databinding.FragmentChallengesBinding

class ChallengesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentChallengesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_challenges, container, false)

        return binding.root
    }

}