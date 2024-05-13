package com.arturo.appwork
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.arturo.appwork.Entidades.Servicio
import com.arturo.appwork.Entidades.Usuario
import com.arturo.appwork.Models.ServicioDao
import com.narvaez.sqlitewa.modelo.UsuarioDao
import kotlin.properties.Delegates

class ResitroServicioActicity : AppCompatActivity() {
    private lateinit var usuarioDao: UsuarioDao
    private lateinit var lstServUsuarios: List<Usuario>
    private lateinit var lblname: TextView
    private lateinit var btnVolver2: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val valorRecibido = intent.getIntExtra("IdServicio", -1) // Utiliza getIntExtra() para recuperar un entero
        setContentView(R.layout.registro_servicio_main)
        asignarvariables()
        asignarreferencias()
        initializeDao()
        lstServUsuarios = usuarioDao.obtenerListaUsuariosPorServicio(valorRecibido.toString())
        lblname.text = intent.getStringExtra("Username")

        //val valorRecibido = intent.getIntExtra("IdServicio", -1) // Utiliza getIntExtra() para recuperar un entero
        if (valorRecibido != -1) {
            mostrarMensaje("Procedemos a listar los proveedores con el servicio: $valorRecibido")
        }
    }
    private fun initializeDao() {
        usuarioDao = UsuarioDao(this)
    }
    fun asignarreferencias() {
        val tipoUsuario = intent.getIntExtra("IdTipoUser",0)
        btnVolver2 = findViewById(R.id.btnVolver2)
        btnVolver2.setOnClickListener {

        }
    }

    private fun mostrarMensaje(mensaje: String) {
        AlertDialog.Builder(this)
            .setTitle("Mensaje")
            .setMessage(mensaje)
            .setPositiveButton("OK", null)
            .show()
    }
    fun asignarvariables(){
        lblname = findViewById(R.id.lblname)
    }
}