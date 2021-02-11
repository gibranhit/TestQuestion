package mx.com.questionsstress.ui.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_welcome.*
import kotlinx.android.synthetic.main.layout_header.*
import mx.com.questionsstress.R

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tvTitle.text = "Bienvenido"
        buttonWelcome.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_selectTestFragment)
        }
    }

}