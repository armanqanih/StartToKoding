package org.lotka.xenonx.domain.util.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import java.io.ByteArrayOutputStream
import java.io.File

object ImageUtil {

    fun rotatePhoto(rotate: Int, imageFile: File): ByteArray? {
        var photoBitmap = BitmapFactory.decodeFile(imageFile.path)
        val stream = ByteArrayOutputStream()
        photoBitmap = getBitmapRotatedByDegree(photoBitmap, rotate.toFloat())
        photoBitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream)
        return stream.toByteArray()
    }

    private fun getBitmapRotatedByDegree(bitmap: Bitmap, rotationDegree: Float): Bitmap {
        val matrix = Matrix()
        matrix.preRotate(rotationDegree)
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    fun checkImageRotation(file: File): Int {
        var rotate = 0
        val exif = ExifInterface(file.absolutePath)
        val orientation: Int = exif.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL
        )
        when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_270 -> rotate = 270
            ExifInterface.ORIENTATION_ROTATE_180 -> rotate = 180
            ExifInterface.ORIENTATION_ROTATE_90 -> rotate = 90
        }

        return rotate
    }
}