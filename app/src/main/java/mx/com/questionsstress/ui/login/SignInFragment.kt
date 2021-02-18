package mx.com.questionsstress.ui.login

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.sign_in_fragment.*
import mx.com.questionsstress.R
import mx.com.questionsstress.ui.dashboard.listener.OnBackStack

class SignInFragment : Fragment(R.layout.sign_in_fragment), OnBackStack {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpData()
    }

    private fun setUpData() {
        tvTitle.text = getString(R.string.text_sign_in)
        buttonSignIn.setOnClickListener {
            if (etPassword.text.toString().isNotBlank() && etUsername.text.toString().isNotBlank()) {
                findNavController().navigate(R.id.action_signInFragment_to_welcomeFragment)
            } else {
                Toast.makeText(requireContext(), "Debes ingresar datos vacios", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        activity?.finish()
    }

}