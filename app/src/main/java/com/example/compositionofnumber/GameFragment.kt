package com.example.compositionofnumber

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.compositionofnumber.databinding.FragmentGameBinding
import com.example.compositionofnumber.databinding.FragmentWelcomeBinding
import java.lang.RuntimeException

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private  val binding: FragmentGameBinding
    get() = _binding ?:  throw  RuntimeException("Fragment not found")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
