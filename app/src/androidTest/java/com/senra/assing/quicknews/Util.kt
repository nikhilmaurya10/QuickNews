package com.senra.assing.quicknews

import android.content.Context
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


object Util {
    fun asset(context: Context, path: String): String {
        try {
            val inputStream = context.assets.open(path)
            return inputStreamToString(inputStream, "UTF-8")
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    private fun inputStreamToString(inputStream: InputStream, charsetName: String): String {
        val builder = StringBuilder()
        val reader = InputStreamReader(inputStream, charsetName)
        reader.readLines().forEach {
            builder.append(it)
        }
        return builder.toString()
    }


}