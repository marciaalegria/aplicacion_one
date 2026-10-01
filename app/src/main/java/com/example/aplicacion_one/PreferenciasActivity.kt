package com.example.aplicacion_one

// Importaciones necesarias de Android y AndroidX
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.materialswitch.MaterialSwitch

/**
 * PreferenciasActivity: Tercera pantalla de la aplicación encargada de la configuración.
 * Implementa las funcionalidades del Bloque 3:
 * - Layout con título, LinearLayout vertical y componentes de Material Design.
 * - Conexión de MaterialSwitch (swNotificaciones) para activar/desactivar notificaciones.
 * - Conexión de Spinner (spIdioma) con la lista de opciones definida en @array/idiomas.
 * - Recepción de datos del usuario propagados desde BienvenidaActivity.
 */
class PreferenciasActivity : AppCompatActivity() {

    // Referencia al título de la pantalla
    private lateinit var txtTituloPreferencias: TextView

    // Referencia al interruptor de Material Design para notificaciones
    private lateinit var swNotificaciones: MaterialSwitch

    // Referencia al control selector desplegable (Spinner) de idiomas
    private lateinit var spIdioma: Spinner

    // Referencia al botón para guardar y regresar
    private lateinit var btnVolver: Button

    // Variable para almacenar el nombre del usuario
    private var nombreUsuario: String = ""

    /**
     * onCreate: Configura la pantalla de preferencias y conecta los adaptadores de datos.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        // Inicialización base de la actividad
        super.onCreate(savedInstanceState)

        // Asocia el layout activity_preferencias.xml
        setContentView(R.layout.activity_preferencias)

        // Captura las referencias a las vistas mediante sus IDs
        txtTituloPreferencias = findViewById(R.id.txtTituloPreferencias)
        swNotificaciones = findViewById(R.id.swNotificaciones)
        spIdioma = findViewById(R.id.spIdioma)
        btnVolver = findViewById(R.id.btnVolver)

        // Recibe el nombre del usuario propagado desde la actividad anterior
        nombreUsuario = intent.getStringExtra("usuario") ?: "Usuario"

        // Actualiza el título contextual con el nombre del usuario
        txtTituloPreferencias.text = "Preferencias de $nombreUsuario"

        // Crea un ArrayAdapter utilizando el array de recursos R.array.idiomas y el diseño estándar de Spinner
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.idiomas,
            android.R.layout.simple_spinner_item
        )

        // Asigna el diseño visual que tendrá la lista desplegable al abrirse
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Enlaza el adaptador con el Spinner spIdioma
        spIdioma.adapter = adapter

        // Configura un escuchador para detectar cuando el usuario cambia el estado del switch
        swNotificaciones.setOnCheckedChangeListener { _, isChecked ->
            // Determina el mensaje según el estado activo o inactivo
            val estado = if (isChecked) "activadas" else "desactivadas"
            // Muestra una notificación Toast informativa
            Toast.makeText(this, "Notificaciones $estado", Toast.LENGTH_SHORT).show()
        }

        // Configura un escuchador para reaccionar a la selección de elementos del Spinner
        spIdioma.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            // Se ejecuta al seleccionar un elemento
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Obtiene el idioma seleccionado en base a su posición
                val idiomaSeleccionado = parent?.getItemAtPosition(position).toString()
                // Muestra el idioma elegido
                Toast.makeText(this@PreferenciasActivity, "Idioma: $idiomaSeleccionado", Toast.LENGTH_SHORT).show()
            }

            // Se ejecuta si no hay ningún elemento seleccionado
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se requiere acción si no hay selección
            }
        }
    }

    /**
     * onVolverClick: Método vinculado al botón para guardar y finalizar la actividad actual.
     */
    fun onVolverClick(view: View) {
        // Muestra un mensaje confirmando que las preferencias fueron aplicadas
        Toast.makeText(this, "Preferencias guardadas exitosamente", Toast.LENGTH_SHORT).show()

        // Cierra la actividad actual y regresa a la pantalla anterior en la pila de navegación
        finish()
    }
}
