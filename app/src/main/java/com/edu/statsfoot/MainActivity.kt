package com.edu.statsfoot

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.cast.framework.media.ImagePicker

class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter: InazumaAdapter
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private lateinit var listaJugadores: MutableList<Player>
    private lateinit var button: Button
    private lateinit var buttonadd: Button
    private lateinit var img: ImageView
    // tienes que declararlo como lateinit para que funcione el getContent y luego lo inicializas en onCreate
    lateinit var getContent: ActivityResultLauncher<PickVisualMediaRequest>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        listaJugadores = mutableListOf(
            Player(
                "Axel", Posiciones.Delantero, 100, 60, 70, 80, 90, 100, 80
            ),

            Player(
                "Shawn", Posiciones.Delantero, 95, 65, 75, 85, 95, 100, 100
            ),
            Player(
                "Tori", Posiciones.Delantero, 95, 65, 75, 85, 95, 100, 100
            ),
            Player(
                "Jack", Posiciones.Delantero, 95, 65, 75, 85, 95, 100, 100
            ),
            Player(
                "Mark", Posiciones.Portero, 95, 65, 75, 85, 95, 100, 100
            ),
            Player(
                "Nathan", Posiciones.Delantero, 95, 65, 75, 85, 95, 100, 100
            ),
            Player(
                "Scotty", Posiciones.Delantero, 95, 65, 75, 85, 95, 100, 100
            ),
            Player(
                "Hurley", Posiciones.Delantero, 95, 65, 75, 85, 95, 100, 100
            ),
            Player(
                "Gazelle", Posiciones.Delantero, 95, 65, 75, 85, 95, 100, 100
            ),
            Player(
                "Byron Love", Posiciones.Delantero, 95, 65, 75, 85, 95, 100, 100
            ),

            )
        buttonadd = findViewById(R.id.boton_iradd)
        buttonadd.setOnClickListener(){
            val intent = Intent(this, AddPlayers::class.java)
            startActivity(intent)
        }




        val list = findViewById<RecyclerView>(R.id.juglist)
        list.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(this)
        list.layoutManager = mLayoutManager



        mAdapter = InazumaAdapter(listaJugadores, this)
        list.adapter = mAdapter







        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // getcontet es ahora un activity result launcher para obtener la imagen seleccionada de la galeria

    }

}

class InazumaAdapter(private val jugadores: MutableList<Player>, private val context: Context) :
    RecyclerView.Adapter<InazumaAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val position = view.findViewById<TextView>(R.id.position)
        val name = view.findViewById<TextView>(R.id.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Infla el layout del ítem y lo convierte en un ViewHolder
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.simplifiedplayerlist, parent, false)
        return ViewHolder(view) // Devuelve un nuevo ViewHolder
    }

    override fun getItemCount(): Int {
        return jugadores.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player = jugadores[position]
        holder.position.text = player.position.toString()
        holder.name.text = player.name
        holder.name.setOnLongClickListener {
            jugadores.removeAt(position)
            notifyItemRemoved(position)
            true
        }
    }

}



