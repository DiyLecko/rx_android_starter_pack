package co.lecko.rx_android_starter_pack.util

import android.support.annotation.StringRes
import android.view.Gravity
import android.widget.Toast
import co.lecko.rx_android_starter_pack.common.App
import co.lecko.rx_android_starter_pack.common.delay

object ToastUtils {
    fun show(resId: Int, gravity: Int) {
        delay {
            val toast = Toast.makeText(App.get(), App.get().getString(resId), Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }

    fun show(@StringRes resId: Int) {
        show(App.get().getString(resId))
    }

    fun show(msg: String?) {
        if (msg == null) {
            return
        }
        delay {
            val toast = Toast.makeText(App.get(), msg, Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }
}