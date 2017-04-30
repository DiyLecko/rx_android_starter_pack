package co.lecko.rx_android_starter_pack.common

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import co.lecko.rx_android_starter_pack.R
import co.lecko.rx_android_starter_pack.util.KeyboardUtil
import java.util.*

open class BaseFragment : Fragment() {
    val disposeBag = DisposeBag()
    val baseActivity: BaseActivity get() = activity!! as BaseActivity

    override fun onDestroyView() {
        super.onDestroyView()

        disposeBag.dispose()
    }

    open fun onPopFragment() {
        KeyboardUtil.hideKeyboard(this)
    }
}