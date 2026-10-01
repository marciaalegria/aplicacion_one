package com.example.aplicacion_one

// Importaciones necesarias de Android y AndroidX
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * MainActivity: Pantalla principal de la aplicación encargada del Inicio de Sesión.
 * Implementa las funcionalidades descritas en el Bloque 1 y Bloque 2:
 * - Construcción de vistas mediante XML y enlace con findViewById.
 * - Validaciones de formato de correo y longitud de contraseña con .error.
 * - Alternancia de visibilidad de contraseña.
 * - Limpieza de formulario y reseteo de estados.
 * - Contador de intentos fallidos.
 * - Navegación explícita con Intent y paso de parámetros hacia BienvenidaActivity.
 */
class MainActivity : AppCompatActivity() {

    // Referencia al campo de texto del usuario o correo electrónico
    private lateinit var edtUsuario: EditText

    // Referencia al campo de texto de la contraseña
    private lateinit var edtPassword: EditText

    // Referencia a la casilla de verificación "Recordarme"
    private lateinit var chkRecordarme: CheckBox

    // Referencia al botón principal para iniciar sesión
    private lateinit var btnIngresar: Button

    // Referencia al botón secundario para limpiar el formulario
    private lateinit var btnLimpiar: Button

    // Referencia al botón de imagen que conmuta la visibilidad de la contraseña
    private lateinit var btnMostrarPassword: ImageButton

    // Referencia al logo corporativo de INACAP
    private lateinit var imgLogo: ImageView

    // Referencia al título principal "Iniciar sesión"
    private lateinit var txtTitulo: TextView

    // Variable booleana de estado para registrar si la contraseña está visible u oculta
    private var mostrandoPassword: Boolean = false

    // Variable numérica a nivel de clase para registrar el número de intentos fallidos
    private var intentosFallidos: Int = 0

    /**
     * onCreate: Método del ciclo de vida ejecutado al crearse la actividad.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        // Ejecuta la inicialización de la clase base AppCompatActivity
        super.onCreate(savedInstanceState)

        // Asocia el archivo de diseño XML activity_main.xml a esta actividad
        setContentView(R.layout.activity_main)

        // Inicializa y captura cada vista definida en el XML mediante su ID único
        imgLogo = findViewById(R.id.imgLogo)
        txtTitulo = findViewById(R.id.txtTitulo)
        edtUsuario = findViewById(R.id.edtUsuario)
        edtPassword = findViewById(R.id.edtPassword)
        chkRecordarme = findViewById(R.id.chkRecordarme)
        btnIngresar = findViewById(R.id.btnIngresar)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnMostrarPassword = findViewById(R.id.btnMostrarPassword)
    }

    /**
     * onIngresarClick: Método vinculado al atributo android:onClick del botón btnIngresar.
     * Valida los datos ingresados y navega a BienvenidaActivity si todo es correcto.
     */
    fun onIngresarClick(view: View) {
        // Obtiene el texto ingresado en el campo usuario y elimina espacios en blanco extremos
        val usuario = edtUsuario.text.toString().trim()

        // Obtiene el texto ingresado en el campo contraseña
        val password = edtPassword.text.toString().trim()

        // Bandera local para determinar si el formulario es válido
        var esValido = true

        // Validación 1: Verificar que el usuario no esté vacío
        if (usuario.isEmpty()) {
            // Asigna un mensaje de error visual directamente sobre el campo de texto
            edtUsuario.error = "El campo de usuario no puede estar vacío"
            // Marca el formulario como inválido
            esValido = false
        }
        // Validación 2: Validar formato de correo mediante Patterns.EMAIL_ADDRESS
        else if (!Patterns.EMAIL_ADDRESS.matcher(usuario).matches()) {
            // Asigna mensaje contextual de formato de correo incorrecto
            edtUsuario.error = "Ingresa un correo electrónico válido (ejemplo: usuario@inacap.cl)"
            // Marca el formulario como inválido
            esValido = false
        } else {
            // Si el campo es correcto, elimina cualquier error visual previo
            edtUsuario.error = null
        }

        // Validación 3: Verificar que la contraseña no esté vacía
        if (password.isEmpty()) {
            // Asigna mensaje de error visual sobre el campo de contraseña
            edtPassword.error = "La contraseña no puede estar vacía"
            // Marca el formulario como inválido
            esValido = false
        }
        // Validación 4: Verificar longitud mínima de al menos 6 caracteres
        else if (password.length < 6) {
            // Asigna mensaje de longitud insuficiente
            edtPassword.error = "La contraseña debe tener al menos 6 caracteres"
            // Marca el formulario como inválido
            esValido = false
        } else {
            // Si la contraseña es válida, elimina errores visuales previos
            edtPassword.error = null
        }

        // Si alguna validación falló
        if (!esValido) {
            // Incrementa el contador acumulativo de intentos fallidos
            intentosFallidos++
            // Muestra una notificación Toast didáctica con el conteo de fallos
            Toast.makeText(this, "Datos inválidos. Intentos fallidos: $intentosFallidos", Toast.LENGTH_SHORT).show()
            // Finaliza la ejecución sin navegar
            return
        }

        // Si los datos son válidos, reinicia el contador de intentos fallidos
        intentosFallidos = 0

        // Crea un Intent explícito para transicionar desde MainActivity hacia BienvenidaActivity
        val intent = Intent(this, BienvenidaActivity::class.java)

        // Agrega el parámetro del usuario como dato extra dentro del Intent
        intent.putExtra("usuario", usuario)

        // Opcional: pasa también el estado del checkbox si el usuario eligió ser recordado
        intent.putExtra("recordarme", chkRecordarme.isChecked)

        // Inicia la segunda actividad en pantalla
        startActivity(intent)
    }

    /**
     * onLimpiarClick: Método vinculado al botón btnLimpiar para resetear el formulario.
     */
    fun onLimpiarClick(view: View) {
        // Borra el contenido del campo usuario
        edtUsuario.text.clear()

        // Borra el contenido del campo contraseña
        edtPassword.text.clear()

        // Quita los mensajes de error visuales de ambos campos
        edtUsuario.error = null
        edtPassword.error = null

        // Desmarca la casilla de verificación
        chkRecordarme.isChecked = false

        // Notifica al usuario que los campos fueron restablecidos
        Toast.makeText(this, "Formulario limpiado", Toast.LENGTH_SHORT).show()
    }

    /**
     * onMostrarPasswordClick: Método vinculado al ImageButton para conmutar la visibilidad de la contraseña.
     */
    fun onMostrarPasswordClick(view: View) {
        // Invierte el estado booleano actual
        mostrandoPassword = !mostrandoPassword

        // Condicional según el nuevo estado de visualización
        if (mostrandoPassword) {
            // Establece el tipo de entrada como texto visible
            edtPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            // Cambia el icono para reflejar que la contraseña ahora está visible
            btnMostrarPassword.setImageResource(R.drawable.ic_visibility_off_24)
        } else {
            // Establece el tipo de entrada como contraseña oculta
            edtPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            // Restablece el icono original de visibilidad
            btnMostrarPassword.setImageResource(R.drawable.ic_visibility_24)
        }

        // Preserva la posición del cursor colocándolo al final del texto actual
        edtPassword.setSelection(edtPassword.text.length)
    }
}