package com.example.admybrand_assignment.ui.Detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.admybrand_assignment.R
import com.example.admybrand_assignment.databinding.FragmentFollowBinding

class FollowingFragment:Fragment(R.layout.fragment_follow) {

    private var _binding:FragmentFollowBinding?=null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding= FragmentFollowBinding.bind(view)
    }
}