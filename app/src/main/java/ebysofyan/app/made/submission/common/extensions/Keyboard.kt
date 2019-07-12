package ebysofyan.app.made.submission.common.extensions

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager


/**
 * Created by @ebysofyan on 7/13/19
 */

fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = currentFocus
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}