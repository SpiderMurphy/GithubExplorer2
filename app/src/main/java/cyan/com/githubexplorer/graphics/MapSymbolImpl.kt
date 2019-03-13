package cyan.com.githubexplorer.graphics

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable

class MapSymbolImpl : MapSymbol {
    override fun from(drawable: Drawable): Bitmap {
        drawable.bounds.set(
            0,
            0,
            drawable.intrinsicWidth,
            drawable.intrinsicHeight)
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.draw(canvas)
        return bitmap
    }
}