package co.lecko.rx_android_starter_pack.common

import android.content.Intent
import android.content.pm.PackageManager
import android.support.multidex.MultiDexApplication
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import co.lecko.rx_android_starter_pack.activity.SplashActivity
import co.lecko.rx_android_starter_pack.common.rx.model.RxModel
import co.lecko.rx_android_starter_pack.util.PermissionUtils
import java.lang.ref.WeakReference
import java.util.ArrayList

class App : MultiDexApplication() {
    val activities = HashMap<String, WeakReference<BaseActivity>>()

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    private var isReloadNeeded = true
    fun reloadActivity(activity: BaseActivity): Boolean {
        activities.put(activity.javaClass.name, WeakReference(activity))

        if (activity.javaClass == SplashActivity::class.java) {
            isReloadNeeded = false
        }

        if (isReloadNeeded) {
            activities.values.forEach { it.get()?.finish() }
            activity.startActivity<SplashActivity>(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }

        return isReloadNeeded
    }

    companion object {
        private var instance: App? = null

        fun get() = instance!!
    }
}