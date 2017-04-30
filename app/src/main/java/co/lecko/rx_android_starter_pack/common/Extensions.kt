package co.lecko.rx_android_starter_pack.common

import android.os.Looper
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class DisposeBag {
    private var subscriptions: ArrayList<Subscription> = ArrayList()
    private var disposeBags: ArrayList<DisposeBag> = ArrayList()

    fun dispose() {
        subscriptions.forEach { s -> s.unsubscribe() }
        subscriptions.clear()

        disposeBags.forEach(DisposeBag::dispose)
        disposeBags.clear()
    }

    fun add(bag: DisposeBag) {
        disposeBags.add(bag)
    }

    fun add(sub: Subscription) {
        subscriptions.add(sub)
    }
}

fun Subscription.addTo(disposeBag: DisposeBag) {
    disposeBag.add(this)
}

fun delay(delayInMs: Long = 0, block: Boolean = false, runner: (() -> Unit)) {
    val isMainThread = Looper.myLooper() == Looper.getMainLooper()
    if (isMainThread || !block) {
        rx.Observable.just(0)
                .delay(delayInMs, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .subscribe({
                    runner()
                })
    } else {
        val latch = CountDownLatch(1)
        rx.Observable.just(0)
                .delay(delayInMs, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .subscribe({
                    runner()
                    latch.countDown()
                })
        latch.await()
    }
}