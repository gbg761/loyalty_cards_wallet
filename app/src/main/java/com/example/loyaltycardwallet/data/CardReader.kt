package com.example.loyaltycardwallet.data

import android.content.Context
import android.util.Log
import com.example.loyaltycardwallet.R
import com.example.loyaltycardwallet.model.InputCard
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList

class CardReader(private val context: Context) {

    private val TAG = "Reading error"

    fun read(): List<InputCard> {

        val inputStream: InputStream = context.resources.openRawResource(R.raw.listcards)
        val reader = BufferedReader(InputStreamReader(inputStream))

        val resultList: MutableList<InputCard> = ArrayList()

        var csvLine: String? = ""
        try {
            csvLine = reader.readLine()
            while (csvLine != null) {
                val row: List<String> = csvLine.split(";")
                resultList.add(InputCard(id = UUID.randomUUID().toString(), shopName = row[0], cardName = row[1], color = row[2]))
                csvLine = reader.readLine()
            }
        } catch (e: IOException) {
            Log.d(TAG, "Error in reading CSV file line#: $csvLine")
        } finally {
            try {
                inputStream.close()
                reader.close()
            } catch (e: IOException) {
                Log.d(TAG, "Error while closing input stream: $e")
            }
        }

        if (resultList.isEmpty())
            throw IOException("Не удалось прочитать файл со списком карт")
        else
            return resultList
    }
}