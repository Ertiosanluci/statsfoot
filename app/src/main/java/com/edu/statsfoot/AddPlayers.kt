package com.edu.statsfoot

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.google.gson.Gson
import java.io.File

class AddPlayers : AppCompatActivity() {
    lateinit var image: ImageView
    lateinit var shoot: EditText
    lateinit var dribble: EditText
    lateinit var technical: EditText
    lateinit var defense: EditText
    lateinit var speed: EditText
    lateinit var endurance: EditText
    lateinit var control: EditText
    lateinit var name: TextView
    lateinit var buttonaddplayer: Button
    lateinit var buttonaddimage: Button
    lateinit var posiciones: List<String>
    lateinit var getContent: ActivityResultLauncher<PickVisualMediaRequest>
    lateinit var arrayplayer: ArrayList<Player>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.addplayer)
        posiciones = listOf(
            Posiciones.Delantero.toString(),
            Posiciones.Defensa.toString(),
            Posiciones.Portero.toString(),
            Posiciones.Lateral.toString(),
            Posiciones.Centrocampista.toString(),

            )
        shoot = findViewById(R.id.Tiro)
        dribble = findViewById(R.id.regate)
        technical = findViewById(R.id.tecnica)
        defense = findViewById(R.id.defensa)
        speed = findViewById(R.id.rapidez)
        endurance = findViewById(R.id.aguante)
        control = findViewById(R.id.control)

        image = findViewById(R.id.profilepicture)
        name = findViewById(R.id.Playername)
        buttonaddplayer = findViewById(R.id.buttonadd)
        image.setImageResource(R.drawable.border)
        buttonaddimage = findViewById(R.id.btnaddimage)
        getContent =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
                // Manejar la URI de la imagen seleccionada
                if (uri != null) {
                    Log.d("Photopicker", "Imagen seleccionada: $uri")
                    image.setImageURI(uri)
                } else {
                    Log.d("Photopicker", "No se ha seleccionado ninguna imagen")
                }
            }
        buttonaddimage.setOnClickListener {

            getContent.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        buttonaddplayer.setOnClickListener {
            if (name.text.toString().isNotEmpty() && shoot.text.toString()
                    .isNotEmpty() && dribble.text.toString().isNotEmpty() &&
                technical.text.toString().isNotEmpty() && defense.text.toString()
                    .isNotEmpty() && speed.text.toString().isNotEmpty() &&
                endurance.text.toString().isNotEmpty()
            ) {
                val name = name.text.toString()
                val shoot = shoot.text.toString()
                val dribble = dribble.text.toString()
                val technical = technical.text.toString()
                val defense = defense.text.toString()
                val speed = speed.text.toString()
                val endurance = endurance.text.toString()

                val position =
                    findViewById<AutoCompleteTextView>(R.id.auto_Complete).text.toString()
                var pos = Posiciones.Delantero
                //utilizar class name en vez de cambiar el enum.....
                when (position) {
                    Posiciones.Delantero.toString() -> {
                        pos = Posiciones.Delantero
                    }

                    Posiciones.Defensa.toString() -> {
                        pos = Posiciones.Defensa
                    }

                    Posiciones.Portero.toString() -> {
                        pos = Posiciones.Portero
                    }

                    Posiciones.Lateral.toString() -> {
                        pos = Posiciones.Lateral
                    }

                    Posiciones.Centrocampista.toString() -> {
                        pos = Posiciones.Centrocampista
                    }
                }
                var pl = Player(
                    name,
                    image,
                    pos,
                    shoot.toInt(),
                    dribble.toInt(),
                    technical.toInt(),
                    defense.toInt(),
                    speed.toInt(),
                    endurance.toInt(),
                    control.text.toString().toInt()



                )
                arrayplayer.add(pl)
                val gson = Gson()
                val jsonString = gson.toJson(arrayplayer) // Serializar a JSON
                guardarJson(this, jsonString, "players.json")
                Toast.makeText(this, "Jugador añadido", Toast.LENGTH_SHORT).show()

                finish()
            }

        }
        val autoComplete: AutoCompleteTextView = findViewById(R.id.auto_Complete)
        val adapter = ArrayAdapter(this, R.layout.listitems, posiciones)
        autoComplete.setAdapter(adapter)
        autoComplete.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, _, i, _ ->
                val itemSelected = adapterView.getItemAtPosition(i)
                Toast.makeText(this, "Seleccionaste $itemSelected", Toast.LENGTH_SHORT).show()
            }

    }

    //----------------------------
    fun guardarJson(context: Context, jsonString: String, nombreFichero: String) {
        try {
            val archivo = File(context.filesDir, nombreFichero)
            val fileOutputStream = archivo.outputStream()
            fileOutputStream.write(jsonString.toByteArray())
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}