package com.example.compositionofnumber

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.compositionofnumber.databinding.FragmentChooseLevelBinding
import com.example.compositionofnumber.databinding.FragmentGameFinishedBinding
import com.example.compositionofnumber.domain.entities.Level
import java.lang.RuntimeException


class ChooseLevelFragment : Fragment() {

    private var _binding: FragmentChooseLevelBinding? = null
    private  val binding: FragmentChooseLevelBinding
        get() = _binding ?:  throw  RuntimeException("Fragment not found")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            buttonLevelTest.setOnClickListener{
                launchGameFragment(Level.TEST)
            }
            buttonLevelEasy.setOnClickListener{
                launchGameFragment(Level.EASY)
            }
            buttonLevelNormal.setOnClickListener{
                launchGameFragment(Level.NORMAL)
            }
            buttonLevelHard.setOnClickListener{
                launchGameFragment(Level.HARD)
            }
        }

    }

    private fun launchGameFragment(level:Level) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container,GameFragment.newInstance(level))
            .addToBackStack(null)
            .commit()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
         const val NAME = "ChooseLevelFragment"

        fun newInstance(): ChooseLevelFragment{
            return  ChooseLevelFragment()

        }
    }
}