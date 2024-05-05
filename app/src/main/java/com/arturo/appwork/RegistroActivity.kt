package com.arturo.appwork
import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.arturo.appwork.Entidades.Servicio
import com.arturo.appwork.Entidades.TipoUsuario
import com.arturo.appwork.Entidades.Usuario
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.narvaez.sqlitewa.modelo.UsuarioDao
import com.google.android.material.dialog.MaterialAlertDialogBuilder
class RegistroActivity : AppCompatActivity() {
    private lateinit var rbUsuario: RadioButton
    private lateinit var rbColaborador: RadioButton
    private lateinit var tipoUsuarios: List<TipoUsuario>
    private lateinit var tipoServicios: List<Servicio>
    private lateinit var radioGroup: RadioGroup
    private lateinit var txtlayoutservices: TextInputLayout
    private lateinit var textLayaoutCheckBox: TextInputLayout
    private lateinit var usuarioDao: UsuarioDao
    private lateinit var ValueRadiobutton:String
    private lateinit var txtNombre:TextInputEditText
    private lateinit var txtDni:TextInputEditText
    private lateinit var txtTelefono:TextInputEditText
    private lateinit var txtEmail:TextInputEditText
    private lateinit var btnRegistrar:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_main)
        asignarReferencias()
        inicializarDao()
        cargarTipoUsuarios()
        configurarRadioGroup()
    }
    private fun asignarReferencias() {
        rbUsuario = findViewById(R.id.rbUsuario)
        rbColaborador = findViewById(R.id.rbColaborador)
        radioGroup = findViewById(R.id.radioGroup)
        txtlayoutservices = findViewById(R.id.txtlayoutservices)
        textLayaoutCheckBox = findViewById(R.id.textLayaoutCheckBox)

        txtNombre = findViewById(R.id.txtUsuario)
        txtDni = findViewById(R.id.txtDni)
        txtTelefono = findViewById(R.id.txtTelefono)
        txtEmail = findViewById(R.id.txtPassword)
        btnRegistrar = findViewById(R.id.btnIngresar)
        btnRegistrar.setOnClickListener {
            capturarDatos()
        }
    }
    fun capturarDatos() {
        val nombre = txtNombre.text.toString()
        val dni = txtDni.text.toString()
        val telefono = txtTelefono.text.toString()
        val email = txtEmail.text.toString()
        var valida = true

        if (nombre.isEmpty()) {
            valida = false
            txtNombre.setError("nombre es obligatorio")
        }
        if (dni.isEmpty()) {
            valida = false
            txtDni.setError("dni son obligatorios")
        }
        if (telefono.isEmpty()) {
            valida = false
            txtTelefono.setError("telefono es obligatorio")
        }
        if (email.isEmpty()) {
            valida = false
            txtEmail.setError("email es obligatorio")
        }
        if (radioGroup.checkedRadioButtonId == -1) {
            valida = false
            Toast.makeText(this, "Debes seleccionar un tipo de usuario", Toast.LENGTH_SHORT).show()
        }
        if (valida) {
            val usuario = Usuario()
            usuario.Nombre = nombre
            usuario.Email = email
            usuario.Dni = dni
            usuario.Telefono = telefono
            usuario.IdTipoUsuario = ValueRadiobutton.toInt()
            val lstServicesID = capturarServiciosSeleccionados()
            if (lstServicesID.isNotEmpty()) {
                usuario.ServiciosID= lstServicesID.toString()
            } else {
                usuario.ServiciosID= ""
            }
            if (ValueRadiobutton=="2" &&  lstServicesID.isEmpty()){
                Toast.makeText(this, "Debes seleccionar al menos 1 servicio", Toast.LENGTH_SHORT).show()
            }else{
                mostrarAlertDialog(usuario)
            }
        }
    }
    private fun limpiarCampos() {
        txtNombre.text = null
        txtDni.text = null
        txtTelefono.text = null
        txtEmail.text = null
        radioGroup.clearCheck()
        txtNombre.error = null
        txtDni.error = null
        txtTelefono.error = null
        txtEmail.error = null
    }
    private fun inicializarDao() {
        usuarioDao = UsuarioDao(this)
    }
    private fun cargarTipoUsuarios() {
        try {
            tipoUsuarios = usuarioDao.cargarTipoUsuario()
            if (tipoUsuarios.isNotEmpty()) {
                rbUsuario.text = tipoUsuarios[0].Descripcion
                rbColaborador.text = tipoUsuarios[1].Descripcion
            }
        } catch (e: Exception) {
            mostrarMensajeError(e.message ?: "Error desconocido")
        }
    }
    private fun cargarTipoServicios() {
        try {
            tipoServicios = usuarioDao.cargarServicios()
            if (tipoServicios.isNotEmpty()) {
                tipoServicios.forEach { item ->
                    val checkBox = CheckBox(this)
                    checkBox.id=item.IdServicio
                    checkBox.text = item.Descripcion
                    checkBox.isChecked = false
                    textLayaoutCheckBox.addView(checkBox)
                }
            }
        } catch (e: Exception) {
            mostrarMensajeError(e.message ?: "Error desconocido")
        }
    }
    private fun capturarServiciosSeleccionados(): List<Int> {
        val serviciosSeleccionados = mutableListOf<Int>()
        val checkBoxCount = textLayaoutCheckBox.childCount
        for (i in 0 until checkBoxCount) {
            val view = textLayaoutCheckBox.getChildAt(i)
            if (view is CheckBox) {
                if (view.isChecked) {
                    serviciosSeleccionados.add(view.id)
                }
            }
        }
        return serviciosSeleccionados
    }
    private fun configurarRadioGroup() {
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val tipoUsuarioSeleccionado = if (checkedId == R.id.rbUsuario) tipoUsuarios[0] else tipoUsuarios[1]
            ValueRadiobutton= tipoUsuarioSeleccionado.IdTipoUsuario.toString()
            if(ValueRadiobutton=="2"){

                cargarTipoServicios()
            }else{

                limpiarServicios()
            }
        }
    }
    private fun limpiarServicios() {
        textLayaoutCheckBox.removeAllViews()
    }
    private fun mostrarMensajeError(mensaje: String) {
        AlertDialog.Builder(this)
            .setTitle("Mensaje")
            .setMessage(mensaje)
            .setPositiveButton("OK", null)
            .show()
    }
    private fun mostrarAlertDialog(usuario: Usuario) {
        val alertDialogBuilder = MaterialAlertDialogBuilder(this)
        alertDialogBuilder.setTitle("Para finalizar, ingresa una contraseña")
        val input = EditText(this)
        input.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        alertDialogBuilder.setView(input)
        alertDialogBuilder.setPositiveButton("Aceptar") { dialog, _ ->
            val valorIngresado = input.text.toString()
            usuario.Password = valorIngresado
            val mensaje = usuarioDao.registrarUsuario(usuario)
            mostrarMensajeError(mensaje)
            limpiarCampos()
            limpiarServicios()
        }
        alertDialogBuilder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.setOnShowListener {
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextColor(ContextCompat.getColor(this, R.color.danger))
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(ContextCompat.getColor(this, R.color.primary))
        }
        alertDialog.show()
    }
}
