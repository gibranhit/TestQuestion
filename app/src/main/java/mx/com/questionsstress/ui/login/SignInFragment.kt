package mx.com.questionsstress.ui.login

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.sign_in_fragment.*
import kotlinx.coroutines.flow.collect
import mx.com.questionsstress.R
import mx.com.questionsstress.domain.models.request.UserRequest
import mx.com.questionsstress.domain.models.response.UserResponse
import mx.com.questionsstress.domain.remote.ProcessStep
import mx.com.questionsstress.ui.dashboard.listener.OnBackStack
import mx.com.questionsstress.utils.extensions.configProgressBar
import mx.com.questionsstress.utils.extensions.observe
import mx.com.questionsstress.utils.extensions.saveDataString
import mx.com.questionsstress.utils.extensions.snack
import org.koin.android.viewmodel.ext.android.viewModel


class SignInFragment : Fragment(R.layout.sign_in_fragment), OnBackStack {

    private val viewModel: SignInViewModel by viewModel()

    private val dialog: Dialog by lazy { configProgressBar(R.color.purple_200) }

    companion object {
        const val NAME_PREFERENCES_TEST = "preferences-user"
        const val KEY_EMAIL = "email"
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpData()
        setUpObserver()
    }

    private fun setUpObserver() {
        lifecycleScope.launchWhenCreated {
            viewModel.user.collect {
                it.getContentIfNotHandled()?.let { state ->
                    when(state) {
                        is SignInViewModel.LoginUiState.Loading -> loading(true)
                        is SignInViewModel.LoginUiState.Error -> {
                            loading(false)
                            snack(state.message)
                        }
                        is SignInViewModel.LoginUiState.Success -> {
                            saveDataString(NAME_PREFERENCES_TEST, KEY_EMAIL, state.response.email)
                            loading(false)
                            findNavController().navigate(R.id.action_signInFragment_to_welcomeFragment)
                        }
                        else -> Unit
                    }
                }
            }
        }
    }

    private fun setUpData() {
        tvTitle.text = getString(R.string.text_sign_in)
        buttonSignIn.setOnClickListener {
            if (etPassword.text.toString().isNotBlank() && etUsername.text.toString().isNotBlank()) {
                viewModel.signIn(UserRequest(email = etUsername.text.toString(), password = etPassword.text.toString()))
            } else {
                Toast.makeText(requireContext(), "Debes ingresar datos vacios", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        activity?.finish()
    }

    private fun loading(loaded: Boolean) {
        if (loaded) dialog.show() else dialog.dismiss()
    }

}

