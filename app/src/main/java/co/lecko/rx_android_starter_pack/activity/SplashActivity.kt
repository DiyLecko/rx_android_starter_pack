package co.lecko.rx_android_starter_pack.activity

import android.content.Intent
import android.os.Bundle
import co.lecko.rx_android_starter_pack.R
import co.lecko.rx_android_starter_pack.common.BaseActivity
import co.lecko.rx_android_starter_pack.common.delay

class SplashActivity : BaseActivity() {
    val INTRO_TIME_MILLIS = 1500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        delay(INTRO_TIME_MILLIS) {
            launchApp()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    fun launchApp() {
        startActivity<MainActivity>(
                flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_SINGLE_TOP,
                enterAnim = R.anim.fade_in,
                exitAnim = R.anim.fade_out,
                isFinishCurrent = true)
    }
}
