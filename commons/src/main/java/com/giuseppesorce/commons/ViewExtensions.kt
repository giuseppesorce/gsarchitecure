package com.giuseppesorce.commons

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment


/**
 * @author Giuseppe Sorce
 */
/**
 * Extension method to provide simpler access to {@link View#getResources()#getString(int)}.
 */



/**
 * Extension method to show a keyboard for View.
 */
fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, 0)
}
/**
 * Try to hide the keyboard and returns whether it worked
 * https://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
 */
fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) { }
    return false
}

/**
 * Extension method to remove the required boilerplate for running code after a view has been
 * inflated and measured.
 *
 * @author Antonio Leiva
 * @see <a href="https://antonioleiva.com/kotlin-ongloballayoutlistener/>Kotlin recipes: OnGlobalLayoutListener</a>
 */
inline fun <T : View> T.afterMeasured(crossinline f: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}


fun View.show(show:Boolean){
    this.visibility= when(show){
        true -> View.VISIBLE
        else -> View.GONE
    }
}

fun View.hide(show:Boolean){
    this.visibility= when(show){
        true -> View.VISIBLE
        else -> View.INVISIBLE
    }
}


fun ImageView.canTouch(touch:Boolean){
    isEnabled= touch
    this.alpha= when(touch){
        true ->1.0F
        else -> 0.6F
    }
}


/**
 * Extension method to get a view as bitmap.
 */
fun View.getBitmap(): Bitmap {
    val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bmp)
    draw(canvas)
    canvas.save()
    return bmp
}


fun Context.toast(message :String, duration:Int= Toast.LENGTH_LONG){
    Toast.makeText(this, message, duration).show()
}

/**
 * Extension method to provide hide keyboard for [Activity].
 */
fun Activity.hideSoftKeyboard() {
    if (currentFocus != null) {
        val inputMethodManager = getSystemService(Context
                .INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
}


fun Fragment.color(color:Int ) :Int{
    return this.activity?.let { act->
        ContextCompat.getColor(act.applicationContext , color)
    } ?:0
}


fun SwitchCompat.setColors(thumbUnswitch: Int, thumbSwitched:Int, trackUnSwitch:Int, trackSwitched:Int){

    val states = arrayOf(intArrayOf(-android.R.attr.state_checked), intArrayOf(android.R.attr.state_checked)
    )
    val thumbColors = intArrayOf(thumbUnswitch, thumbSwitched,
    )
    val trackColors = intArrayOf(trackUnSwitch, trackSwitched,)
    DrawableCompat.setTintList(
        DrawableCompat.wrap( this.thumbDrawable),
        ColorStateList(states, thumbColors)
    )
    DrawableCompat.setTintList(
        DrawableCompat.wrap(this.trackDrawable),
        ColorStateList(states, trackColors)
    )
}
fun Context.color(color:Int ) :Int{
    return this.let { act->
        ContextCompat.getColor(act.applicationContext , color)
    } ?:0
}

fun Fragment.drawable(drawable:Int ) :Drawable?{
    return this.activity?.let { act->
        ContextCompat.getDrawable(act.applicationContext , drawable)
    }
}

fun ImageView.drawable(drawable:Int ) :Drawable?{
    return this.context?.let { act->
        ContextCompat.getDrawable(act.applicationContext , drawable)
    }
}

