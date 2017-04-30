package co.lecko.rx_android_starter_pack.common

import android.content.Intent
import android.support.multidex.MultiDexApplication
import co.lecko.rx_android_starter_pack.activity.SplashActivity
import java.lang.ref.WeakReference

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