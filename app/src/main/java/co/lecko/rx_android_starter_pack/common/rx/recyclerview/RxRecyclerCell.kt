package co.lecko.rx_android_starter_pack.common.rx.recyclerview

import android.support.annotation.LayoutRes
import co.lecko.rx_android_starter_pack.common.DisposeBag

open class RxRecyclerCell(open val style: RxRecyclerCellStyle, val data: RxRecyclerCellData, open val viewCategory: Int = 0) : java.io.Serializable {
    open var spanSize: Int = 0

    open fun bindItem(): (RxRecyclerViewBinder.CellItem, disposeBag: DisposeBag) -> Unit {
        return fun(_: RxRecyclerViewBinder.CellItem, _: DisposeBag) {
        }
    }

    override fun toString(): String = "(${this.javaClass.name} $style, $data, $viewCategory)"
}