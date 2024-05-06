package com.arturo.appwork
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.arturo.appwork.Entidades.Usuario
import com.google.android.material.textfield.TextInputEditText
import com.narvaez.sqlitewa.modelo.UsuarioDao

class LoginActivity : AppCompatActivity() {

    private lateinit var BtnRegistrarRedirect: Button
    private lateinit var txtPassword: TextInputEditText
    private lateinit var txtUsuario: TextInputEditText
    private lateinit var btnIngresar: Button
    private lateinit var usuarioDao: UsuarioDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_main)
        inicializarDao()
        asignarReferencias()
        BtnRegistrarRedirect.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
        btnIngresar.setOnClickListener{
            Autenticarse()
        }
    }
    private fun inicializarDao() {
        usuarioDao = UsuarioDao(this)
    }
  private fun Autenticarse(){
      val usuario = txtUsuario.text.toString()
      val contraseña = txtPassword.text.toString()
      var valida = true
      if (usuario.isEmpty()) {
          valida = false
          txtUsuario.setError("nombre es obligatorio")
      }
      if (contraseña.isEmpty()) {
          valida = false
          txtPassword.setError("dni son obligatorios")
      }
      if (valida) {
          val autenticado = usuarioDao.autenticarUsuario(usuario, contraseña)

          if (autenticado) {
              val user = usuarioDao.obtenerUsuarioPorId(usuario)

              if (user != null) {
                  val intent = if (user.IdTipoUsuario == 1) {
                      Intent(this, DashboardClientActivity::class.java)
                  } else {
                      Intent(this, DashboardProveedorActivity::class.java)
                  }
                  intent.putExtra("Username", user.Nombre)
                  intent.putExtra("IdUser", user.IdUsuario)
                  intent.putExtra("IdTipoUser", user.IdTipoUsuario)
                  startActivity(intent)
              } else {
                  mostrarMensajeError("Error al listar usuario")
              }
          } else {
              mostrarMensajeError("Credenciales Incorrectas")
          }
      }

  }
    private fun mostrarMensajeError(mensaje: String) {
        AlertDialog.Builder(this)
            .setTitle("Mensaje")
            .setMessage(mensaje)
            .setPositiveButton("OK", null)
            .show()
    }
    private fun asignarReferencias() {
        BtnRegistrarRedirect = findViewById(R.id.BtnRegistrarRedirect)
        txtPassword = findViewById(R.id.txtPassword)
        txtUsuario = findViewById(R.id.txtUsuario)
        btnIngresar = findViewById(R.id.btnIngresar)
    }
}
