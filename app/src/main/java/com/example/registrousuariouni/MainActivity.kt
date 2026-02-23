package com.example.registrousuariouni

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var listaEstudiantes: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etMatricula = findViewById<EditText>(R.id.etMatricula)
        val etAno = findViewById<EditText>(R.id.etAno)
        val spCarrera = findViewById<Spinner>(R.id.spCarrera)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)
        val cardResultado = findViewById<androidx.cardview.widget.CardView>(R.id.cardResultado)

        // Inicializar lista y adapter 🔥
        listaEstudiantes = ArrayList()

        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaEstudiantes
        )

        // Ocultar card al iniciar
        cardResultado.visibility = View.GONE

        // Datos del Spinner
        val carreras = arrayOf(
            "Ingeniería en Sistemas",
            "Derecho",
            "Administración",
            "Medicina"
        )

        val spinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            carreras
        )

        spCarrera.adapter = spinnerAdapter

        btnRegistrar.setOnClickListener {

            val nombre = etNombre.text.toString().trim()
            val matricula = etMatricula.text.toString().trim()
            val ano = etAno.text.toString().trim()
            val carrera = spCarrera.selectedItem.toString()

            if (nombre.isEmpty() || matricula.isEmpty() || ano.isEmpty()) {

                Toast.makeText(
                    this,
                    "Por favor complete todos los campos",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val mensaje = """
                    Nombre: $nombre
                    
                    Matrícula: $matricula
                    
                    Carrera: $carrera
                    
                    Año Académico: $ano
                """.trimIndent()

                tvResultado.text = mensaje
                cardResultado.visibility = View.VISIBLE

                listaEstudiantes.add("$nombre - $carrera - Año $ano")
                adapter.notifyDataSetChanged()

                etNombre.text.clear()
                etMatricula.text.clear()
                etAno.text.clear()
            }
        }
    }
}