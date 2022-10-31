package com.example.compositionofnumber

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.compositionofnumber.databinding.FragmentGameBinding
import com.example.compositionofnumber.databinding.FragmentGameFinishedBinding
import com.example.compositionofnumber.databinding.FragmentWelcomeBinding
import com.example.compositionofnumber.domain.entities.GameResult
import com.example.compositionofnumber.domain.entities.GameSettings
import com.example.compositionofnumber.domain.entities.Level
import java.lang.RuntimeException
import java.security.Key


class GameFinishedFragment : Fragment() {

    private lateinit var gameResult:GameResult

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
          parseArguments()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            //
        setupOnclickListeners()
        bindViews()


        
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupOnclickListeners() {
        requireActivity().onBackPressedDispatcher.addCallback( viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                retryGame()
            }
        })
        binding.buttonRetry.setOnClickListener{
            retryGame()
        }
    }
    private  fun bindViews() {
        with(binding) {
            emojiResult.setImageResource(getSmileResId())
            tvRequiredAnswers.text = String.format(
                getString(R.string.required_score),
                gameResult.gameSettings.minCountofRightAnswers
            )
            tvScoreAnswers.text = String.format(
                getString(R.string.score_answers),
                gameResult.countOfRightAnswers
            )
            tvRequiredPercentage.text = String.format(
                getString(R.string.required_percentage),
                gameResult.gameSettings.minPercentageofRightAnswers
            )
            tvScorePercentage.text = String.format(
                getString(R.string.score_percentage),
                getPercentOfRightAnswers()
            )

        }
    }

    private fun getSmileResId(): Int{
           return if(gameResult.winOrNot) {
               R.drawable.ic_smile
          } else {
               R.drawable.ic_sad
           }
    }

    private  fun getPercentOfRightAnswers() = with(gameResult) {

        if (countOfQuestions == 0 ) {
       0
        } else {
            ((countOfRightAnswers/countOfQuestions.toDouble())* 100).toInt()
        }

    }


    private  fun parseArguments() {
        requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
            gameResult = it
        }
    }
    //
    private  fun retryGame() {
        requireActivity().supportFragmentManager.popBackStack(ChooseLevelFragment.NAME, 0)
    }

    companion object {

       private const val KEY_GAME_RESULT = "game_result"

        fun newInstance(gameResult: GameResult) :GameFinishedFragment {
            return  GameFinishedFragment().apply {
                arguments = Bundle().apply {
                            putParcelable(KEY_GAME_RESULT,gameResult)
                }
            }
        }
    }
}