package org.lotka.xenonx.presentation.extension

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import org.lotka.xenonx.domain.util.util.FileUtil
import org.lotka.xenonx.domain.util.util.isDownloadsDocument
import org.lotka.xenonx.domain.util.util.isExternalStorageDocument
import org.lotka.xenonx.domain.util.util.isMediaDocument

fun Context.getFilePath(uri: Uri): String? {
    try {
        if (DocumentsContract.isDocumentUri(this, uri)
        ) {
            if (isExternalStorageDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":").toTypedArray()
                val type = split[0]
                if ("primary".equals(type, ignoreCase = true)) {
                    return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                }
            } else if (isDownloadsDocument(uri)) {
                val id = DocumentsContract.getDocumentId(uri)
                val contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"),
                    java.lang.Long.valueOf(id)
                )
                return FileUtil.getDataColumn(this, contentUri, null, null)
            } else if (isMediaDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":").toTypedArray()
                val type = split[0]
                var contentUri: Uri? = null
                when (type) {
                    "image" -> contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    "video" -> contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                    "audio" -> contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }
                val selection = "_id=?"
                val selectionArgs: Array<String?> = arrayOf(split[1])
                return FileUtil.getDataColumn(this, contentUri, selection, selectionArgs)
            }
        } else if ("content".equals(uri.scheme, ignoreCase = true)) {
            return FileUtil.getDataColumn(this, uri, null, null)
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            uri.path?.let {
                return it
            }
        }
    } catch (e: Exception) {
        Log.e("getFilePath", "Failed to get file path", e)
    }
    return null
}
