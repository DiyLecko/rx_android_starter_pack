package co.lecko.rx_android_starter_pack.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import co.lecko.rx_android_starter_pack.common.BaseActivity
import co.lecko.rx_android_starter_pack.common.BaseFragment

class KeyboardUtil {
    companion object {
        fun hideKeyboard(view: View?) {
            if (view == null) {
                return
            }

            view.clearFocus()

            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        fun hideKeyboard(activity: BaseActivity?) {
            if (activity == null) {
                return
            }

            hideKeyboard(activity.currentFocus)
        }

        fun hideKeyboard(fragment: BaseFragment?) {
            if (fragment == null) {
                return
            }

            val activity = fragment.baseActivity

            hideKeyboard(activity)
        }

        fun showKeyboard(view: View?) {
            if (view == null) {
                return
            }

            view.requestFocus()
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
        }
    }
}