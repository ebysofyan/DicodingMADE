package ebysofyan.app.made.favorite.common.extension

import android.annotation.SuppressLint
import android.content.Context
import java.text.ParseException
import java.text.SimpleDateFormat

/**
 * Created by @ebysofyan on 7/2/19
 */

@SuppressLint("SimpleDateFormat")
fun String.toDateFormat(pattern: String = "MMMM dd, yyyy"): String {
    return try {
        val date = SimpleDateFormat("yyyy-MM-dd").parse(this)
        SimpleDateFormat(pattern).format(date)
    } catch (e: ParseException) {
        "---- --, ---"
    }
}

fun Context.forceLocale2to1() = if (resources?.configuration?.locale?.language.toString() == "in") "id"
else resources?.configuration?.locale?.language.toString()