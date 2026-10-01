package com.example.aplicacion_one

// Importaciones necesarias de Android y AndroidX
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * BienvenidaActivity: Segunda pantalla de la aplicación.
 * Implementa las funcionalidades del Bloque 1 y enlace con Bloque 3:
 * - Recepción de parámetros desde MainActivity usando intent.getStringExtra("usuario").
 * - Presentación personalizada del mensaje en txtBienvenida.
 * - Navegación hacia PreferenciasActivity propagando el nombre del usuario.
 */
class BienvenidaActivity : AppCompatActivity() {

    // Referencia al TextView que muestra el saludo al usuario
    private lateinit var txtBienvenida: TextView

    // Referencia al botón que abre la pantalla de preferencias
    private lateinit var btnPreferencias: Button

    // Variable para almacenar el nombre de usuario recibido
    private var nombreUsuario: String = ""

    /**
     * onCreate: Inicializa la actividad al crearse.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        // Ejecuta el constructor e inicialización base
        super.onCreate(savedInstanceState)

        // Asocia el archivo de diseño activity_bienvenida.xml
        setContentView(R.layout.activity_bienvenida)

        // Captura el TextView del mensaje de bienvenida por su ID
        txtBienvenida = findViewById(R.id.txtBienvenida)

        // Captura el botón de preferencias por su ID
        btnPreferencias = findViewById(R.id.btnPreferencias)

        // Extrae el dato enviado como extra bajo la clave "usuario", o asigna un valor por defecto si viniera nulo
        nombreUsuario = intent.getStringExtra("usuario") ?: "Estudiante"

        // Actualiza el texto en pantalla con una bienvenida personalizada
        txtBienvenida.text = "¡Bienvenido/a,\n$nombreUsuario!"
    }

    /**
     * onPreferenciasClick: Método vinculado al botón btnPreferencias para navegar al Bloque 3.
     */
    fun onPreferenciasClick(view: View) {
        // Crea una intención explícita para abrir la actividad PreferenciasActivity
        val intent = Intent(this, PreferenciasActivity::class.java)

        // Propaga el nombre del usuario hacia la pantalla de configuración
        intent.putExtra("usuario", nombreUsuario)

        // Inicia la tercera actividad
        startActivity(intent)
    }
}
