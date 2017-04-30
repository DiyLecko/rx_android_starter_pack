package co.lecko.rx_android_starter_pack.common

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import co.lecko.rx_android_starter_pack.R
import java.util.*

open class BaseActivity : AppCompatActivity() {
    val disposeBag = DisposeBag()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.get().reloadActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()

        disposeBag.dispose()
    }

    override fun onBackPressed() {
        if (!popFragment()) {
            super.onBackPressed()
        }
    }

    inline fun <reified T : BaseActivity> startActivity(
            flags: Int = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_SINGLE_TOP,
            enterAnim: Int = -1,
            exitAnim: Int = -1,
            isFinishCurrent: Boolean = false) {
        startActivity(Intent(this, T::class.java).setFlags(flags))
        if (enterAnim >= 0 || exitAnim >= 0) {
            val _enterAnim = if (enterAnim >= 0) enterAnim else 0
            val _exitAnim = if (exitAnim >= 0) exitAnim else 0
            overridePendingTransition(_enterAnim, _exitAnim)
        }
        if (isFinishCurrent) {
            finish()
        }
    }

    fun fragmentTransaction(transaction: (fm: FragmentManager, ft: FragmentTransaction) -> Boolean): Boolean {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        val result = transaction(fm, ft)
        ft.commit()

        return result
    }

    fun replaceFragment(fragment: BaseFragment,
                        animations: ArrayList<Int>? = null,
                        containerId: Int = R.id.container) {
        fragmentTransaction { fm, ft ->
            if (animations != null) {
                ft.setCustomAnimations(animations[0], animations[1], animations[2], animations[3])
            }
            ft.replace(containerId, fragment)
            ft.addToBackStack(null)
            true
        }
    }

    fun pushFragment(fragment: BaseFragment,
                     animations: ArrayList<Int>? = null,
                     containerId: Int = R.id.container) {
        fragmentTransaction { fm, ft ->
            if (animations != null) {
                ft.setCustomAnimations(animations[0], animations[1], animations[2], animations[3])
            }
            ft.add(containerId, fragment)
            ft.addToBackStack(null)
            true
        }
    }

    fun popFragment(): Boolean {
        return fragmentTransaction { fm, ft ->
            if (fm.backStackEntryCount > 0) {
                val frag = fm.fragments[fm.backStackEntryCount - 1]
                if (frag is BaseFragment) {
                    frag.onPopFragment()
                }
                fm.popBackStack()
                true
            } else {
                false
            }
        }
    }
}