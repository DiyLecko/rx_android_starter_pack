package co.lecko.rx_android_starter_pack.activity

import android.os.Bundle
import co.lecko.rx_android_starter_pack.R
import co.lecko.rx_android_starter_pack.common.App
import co.lecko.rx_android_starter_pack.common.BaseActivity
import co.lecko.rx_android_starter_pack.util.PermissionUtils
import co.lecko.rx_android_starter_pack.util.ToastUtils

class MainActivity : BaseActivity() {
    var permissions = arrayOf(PermissionUtils.DevicePermission.WRITE_EXTERNAL_STORAGE,
            PermissionUtils.DevicePermission.READ_EXTERNAL_STORAGE,
            PermissionUtils.DevicePermission.READ_CONTACTS,
            PermissionUtils.DevicePermission.READ_SMS)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (App.get().reloadActivity(this)) {
            return
        }

        PermissionUtils.get().requestPermission(this, permissions)
    }
}
