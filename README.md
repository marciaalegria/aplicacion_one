# Mi Primera Aplicación

Un proyecto inicial de Android creado para aprender los conceptos fundamentales del desarrollo móvil nativo en TI3V42 - Aplicaciones Móviles para IoT (INACAP Puente Alto).

## 📱 Contenido y Funcionalidades Desarrolladas

### 🔹 Bloque 1: Construcción Incremental del Login (Incrementos 1 al 10)
* **ImageView (`imgLogo`):** Logo corporativo INACAP (`@drawable/logo_inacap`), 96x96dp, centrado superiormente.
* **TextView (`txtTitulo`):** Título "Iniciar sesión", 24sp, negrita y posicionado bajo el logo.
* **EditText (`edtUsuario`):** Campo de entrada de texto con `hint="Usuario"` y `0dp` (match constraints).
* **EditText (`edtPassword`):** Campo de contraseña con `hint="Contraseña"` y `android:inputType="textPassword"`.
* **CheckBox (`chkRecordarme`):** Casilla de verificación para recordar credenciales.
* **Button (`btnIngresar`):** Botón principal de acceso con ancho expandido.
* **Lógica de Botón en Kotlin:** Conexión vía `android:onClick="onIngresarClick"`, captura de vistas con `findViewById` y validaciones básicas.
* **Segunda Pantalla (`BienvenidaActivity`):** Creación de Activity y layout `activity_bienvenida.xml` con `txtBienvenida`.
* **Navegación con Intent:** Transición de pantalla explícita con `startActivity(intent)`.
* **Paso de Parámetros:** Envío de datos entre Activities con `intent.putExtra("usuario", usuario)` y recepción con `intent.getStringExtra("usuario")`.

### 🔹 Bloque 2: Mejoras de Interfaz, Experiencia de Usuario y Validaciones
* **Botón de Limpieza (`btnLimpiar`):**
  * Botón secundario estilizado con fondo rojo (`android:backgroundTint="#FF0000"`).
  * Método `onLimpiarClick` para resetear campos de usuario, contraseña, errores visuales y desmarcar el checkbox.
* **Alternar Visibilidad de Contraseña (`btnMostrarPassword`):**
  * ImageButton con icono `@drawable/ic_visibility_24` y fondo transparente.
  * Variable de estado `mostrandoPassword: Boolean` y método `onMostrarPasswordClick` que conmuta dinámicamente el `inputType` entre texto visible y oculto preservando la posición del cursor.
* **Contador de Intentos Fallidos (`intentosFallidos`):**
  * Variable a nivel de clase que registra cada intento fallido de autenticación.
* **Validaciones Robustas con `.error`:**
  * Validación de formato de correo electrónico mediante `Patterns.EMAIL_ADDRESS`.
  * Validación de longitud mínima para la contraseña (al menos 6 caracteres).
  * Mensajes contextuales directos en los campos mediante `edtUsuario.error` y `edtPassword.error`.

### 🔹 Bloque 3: Tercera Pantalla y Configuración (`PreferenciasActivity`)
* **Navegación a Preferencias:**
  * Botón `btnPreferencias` en `activity_bienvenida.xml` vinculado al método `onPreferenciasClick`.
  * Propagación del nombre del usuario como extra en el Intent.
* **Layout de Preferencias (`activity_preferencias.xml`):**
  * TextView de título centrado.
  * LinearLayout vertical para agrupar las opciones de configuración.
  * MaterialSwitch (`swNotificaciones`) con el texto "Recibir notificaciones".
  * Spinner (`spIdioma`) conectado a un string-array de recursos (`@array/idiomas`) con opciones: Español, Inglés, Italiano y Portugues.

## 🛠️ Tecnologías y Herramientas
* **Lenguaje:** Kotlin
* **Diseño UI:** Android XML (ConstraintLayout, LinearLayout, Material Components)
* **SDK Mínimo:** Android API 24+
* **Control de Versiones:** Git & GitHub CLI (`gh`)
* **Documentación:** Código 100% comentado línea por línea de forma didáctica.
