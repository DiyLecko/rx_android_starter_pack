package co.lecko.rx_android_starter_pack.activity

import android.os.Bundle
import co.lecko.rx_android_starter_pack.R
import co.lecko.rx_android_starter_pack.common.App
import co.lecko.rx_android_starter_pack.common.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (App.get().reloadActivity(this)) {
            return
        }
    }
}
