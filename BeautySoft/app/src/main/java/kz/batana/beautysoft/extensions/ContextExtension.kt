package kz.batana.beautysoft.extensions


import android.app.Activity
import android.content.Context
import android.widget.Toast

fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.showDialog(activity: Activity, title: String, title2: String, msg: String, iDialiogClick: IDialiogClick, btnOkTitle: String = "ОПЛАТИТЬ") {
//    val factory = LayoutInflater.from(this)
//    val deleteDialogView = factory.inflate(R.layout.dialog_payment, null)
//    val deleteDialog = this.let { AlertDialog.Builder(it).create() }
//    deleteDialog!!.setView(deleteDialogView)
//    deleteDialog.window.setBackgroundDrawableResource(android.R.color.transparent)
//    deleteDialogView.findViewById<TextView>(R.id.makePayment).setOnClickListener {
//        iDialiogClick.onClick()
//
//    }
//    deleteDialogView.findViewById<TextView>(R.id.back).setOnClickListener {
//        deleteDialog.cancel()
//    }
//    deleteDialogView.findViewById<TextView>(R.id.textView2)?.text = title
//    deleteDialogView.findViewById<TextView>(R.id.paymentValueTextView)?.text = title2.toUpperCase()
//    deleteDialogView.findViewById<TextView>(R.id.makePayment)?.text = btnOkTitle
//    deleteDialogView.findViewById<TextView>(R.id.timesCountTextView)?.text = msg
//    deleteDialog.show()

}

interface IDialiogClick{
    fun onClick()
}