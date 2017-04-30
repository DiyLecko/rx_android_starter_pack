package co.lecko.rx_android_starter_pack.activity

import android.content.Intent
import android.os.Bundle
import co.lecko.rx_android_starter_pack.R
import co.lecko.rx_android_starter_pack.common.BaseActivity
import co.lecko.rx_android_starter_pack.common.addTo
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class SplashActivity : BaseActivity() {
    val INTRO_TIME_MILLIS = 1500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        rx.Observable
                .just("")
                .delay(INTRO_TIME_MILLIS, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    launchApp()
                }
                .addTo(disposeBag)
    }

    fun launchApp() {
        startActivity<MainActivity>(
                flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_SINGLE_TOP,
                enterAnim = R.anim.fade_in,
                exitAnim = R.anim.fade_out,
                isFinishCurrent = true)
    }
}
