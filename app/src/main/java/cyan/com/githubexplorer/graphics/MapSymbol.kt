package cyan.com.githubexplorer.graphics

import android.graphics.Bitmap
import android.graphics.drawable.Drawable

interface MapSymbol {
    fun from(drawable: Drawable): Bitmap
}