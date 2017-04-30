package co.lecko.rx_android_starter_pack.common

import rx.Subscription
import java.util.*

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