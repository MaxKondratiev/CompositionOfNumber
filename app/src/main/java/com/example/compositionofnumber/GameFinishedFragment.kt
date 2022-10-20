package com.example.compositionofnumber

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.compositionofnumber.databinding.FragmentGameBinding
import com.example.compositionofnumber.databinding.FragmentGameFinishedBinding
import com.example.compositionofnumber.databinding.FragmentWelcomeBinding
import java.lang.RuntimeException


class GameFinishedFragment : Fragment() {


    private var _binding: FragmentGameFinishedBinding? = null
    private  val binding: FragmentGameFinishedBinding
        get() = _binding ?:  throw  RuntimeException("Fragment not found")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}