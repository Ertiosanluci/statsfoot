package com.edu.statsfoot

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import java.io.File
import java.io.FileInputStream
import kotlin.concurrent.write

class PlayerStatitcs : AppCompatActivity() {
    lateinit var arrayplayer: MutableList<Player>
    lateinit var player: Player
    lateinit var jugadores: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.playerstatics)
        jugadores = recuperarJsonEnArchivo(this, "players.json", arrayplayer)
        val gson = Gson()
    }
   fun recuperarJsonEnArchivo(context: Context, nombreArchivo: String,  Arrayplayer: MutableList<Player>)
    :String {
        var file : File
        var byteArray: ByteArray = byteArrayOf()
        var fileInputStream : FileInputStream
        try {

             file = File(context.filesDir, nombreArchivo)
             fileInputStream = FileInputStream(file)
             byteArray = fileInputStream.readBytes()
            fileInputStream.close()
            val gson = Gson()
            gson.fromJson(String(byteArray), Array<Player>::class.java).toMutableList() as MutableList<Player>
            // Archivo guardado con éxito
        } catch (e: Exception) {
            // Manejar la excepción
            e.printStackTrace()
        }


        return String(byteArray)

    }
}