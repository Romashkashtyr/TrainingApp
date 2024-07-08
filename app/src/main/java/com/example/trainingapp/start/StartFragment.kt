package com.example.trainingapp.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.trainingapp.R
import com.example.trainingapp.databinding.FragmentStartBinding
import com.google.firebase.auth.FirebaseAuth


class StartFragment : Fragment() {

    private lateinit var  binding: FragmentStartBinding

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }


}