package mx.com.questionsstress.utils.extensions

import android.app.Dialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import mx.com.questionsstress.R

fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    context?.toast(message, duration)
}

fun Fragment.toast(@StringRes resString: Int, duration: Int = Toast.LENGTH_SHORT) {
    context?.toast(getString(resString), duration)
}

/**
 * Default short toast
 */
fun Context.toast(any: Any, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, any.toString(), duration).show()
}

/**
 * Default short toast
 */
fun Context.toast(@StringRes resString: Int, duration: Int = Toast.LENGTH_SHORT) {
    toast(getString(resString), duration)
}

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, Observer { it?.let { t -> action(t) } })
}

fun <T> LifecycleOwner.observe(liveData: MutableLiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, Observer { it?.let { t -> action(t) } })
}


fun Fragment.snack(message: String) {
    requireActivity().snack(message)
}

fun FragmentActivity.snack(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    val view: View = this.window.decorView.findViewById(android.R.id.content)
    Snackbar.make(view, message, duration).show()
}

fun Fragment.configProgressBar(color: Int) : Dialog {
    val dialog = Dialog(requireContext())
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setContentView(R.layout.layout_loader)
    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    val progressd = dialog.findViewById<ProgressBar>(R.id.progress)
    progressd.indeterminateTintList = ColorStateList.valueOf(
        this.resources.getColor(color)
    )
    dialog.setCancelable(false)
    return dialog
}

fun Fragment.getDataString(name: String, key: String): String =
        requireContext().getSharedPreferences(name, Context.MODE_PRIVATE).run {
            getString(key, "").orEmpty()
        }

fun Fragment.saveDataString(name: String, key: String, value: String) {
    requireContext().getSharedPreferences(name, Context.MODE_PRIVATE).apply {
        edit().putString(key, value).apply()
    }
}