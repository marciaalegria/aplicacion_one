package com.example.aplicacion_one

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
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var edtUsuario: EditText
    private lateinit var edtPassword: EditText
    private lateinit var chkRecordarme: CheckBox
    private lateinit var btnIngresar: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnMostrarPassword: ImageButton
    private lateinit var imgLogo: ImageView
    private lateinit var txtTitulo: TextView

    private var mostrandoPassword: Boolean = false
    private var intentosFallidos: Int = 0

    // Firebase Authentication
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Vincular vistas
        imgLogo = findViewById(R.id.imgLogo)
        txtTitulo = findViewById(R.id.txtTitulo)
        edtUsuario = findViewById(R.id.edtUsuario)
        edtPassword = findViewById(R.id.edtPassword)
        chkRecordarme = findViewById(R.id.chkRecordarme)
        btnIngresar = findViewById(R.id.btnIngresar)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnMostrarPassword = findViewById(R.id.btnMostrarPassword)

        // Inicializar Firebase Authentication
        auth = FirebaseAuth.getInstance()

        // Conectar botones directamente desde Kotlin
        btnIngresar.setOnClickListener {
            onIngresarClick(it)
        }

        btnLimpiar.setOnClickListener {
            onLimpiarClick(it)
        }

        btnMostrarPassword.setOnClickListener {
            onMostrarPasswordClick(it)
        }
    }

    /**
     * Inicio de sesión mediante Firebase Authentication.
     */
    fun onIngresarClick(view: View) {


        val usuario = edtUsuario.text.toString().trim()
        val password = edtPassword.text.toString().trim()

        var esValido = true

        // Validar usuario vacío
        if (usuario.isEmpty()) {

            edtUsuario.error = "El campo de usuario no puede estar vacío"
            esValido = false

        } else if (!Patterns.EMAIL_ADDRESS.matcher(usuario).matches()) {

            edtUsuario.error = "Ingresa un correo electrónico válido"
            esValido = false

        } else {

            edtUsuario.error = null
        }

        // Validar contraseña vacía
        if (password.isEmpty()) {

            edtPassword.error = "La contraseña no puede estar vacía"
            esValido = false

        } else if (password.length < 6) {

            edtPassword.error =
                "La contraseña debe tener al menos 6 caracteres"
            esValido = false

        } else {

            edtPassword.error = null
        }

        // Si existen errores locales, no consultar Firebase
        if (!esValido) {

            intentosFallidos++

            Toast.makeText(
                this,
                "Datos inválidos. Intentos fallidos: $intentosFallidos",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        // Autenticación mediante Firebase
        auth.signInWithEmailAndPassword(usuario, password)
            .addOnCompleteListener(this) { task ->

                if (task.isSuccessful) {

                    // Login correcto
                    intentosFallidos = 0

                    Toast.makeText(
                        this,
                        "Inicio de sesión correcto",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent(
                        this,
                        BienvenidaActivity::class.java
                    )

                    intent.putExtra(
                        "usuario",
                        usuario
                    )

                    intent.putExtra(
                        "recordarme",
                        chkRecordarme.isChecked
                    )

                    startActivity(intent)

                } else {

                    // Login rechazado por Firebase
                    intentosFallidos++

                    Toast.makeText(
                        this,
                        "Correo o contraseña incorrectos. Intentos fallidos: $intentosFallidos",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    /**
     * Limpia todos los campos del formulario.
     */
    fun onLimpiarClick(view: View) {

        edtUsuario.text.clear()
        edtPassword.text.clear()

        edtUsuario.error = null
        edtPassword.error = null

        chkRecordarme.isChecked = false

        Toast.makeText(
            this,
            "Formulario limpiado",
            Toast.LENGTH_SHORT
        ).show()
    }

    /**
     * Muestra u oculta la contraseña.
     */
    fun onMostrarPasswordClick(view: View) {

        mostrandoPassword = !mostrandoPassword

        if (mostrandoPassword) {

            edtPassword.inputType =
                InputType.TYPE_CLASS_TEXT or
                        InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD

            btnMostrarPassword.setImageResource(
                R.drawable.ic_visibility_off_24
            )

        } else {

            edtPassword.inputType =
                InputType.TYPE_CLASS_TEXT or
                        InputType.TYPE_TEXT_VARIATION_PASSWORD

            btnMostrarPassword.setImageResource(
                R.drawable.ic_visibility_24
            )
        }

        // Mantener cursor al final
        edtPassword.setSelection(
            edtPassword.text.length
        )
    }
}