package com.arturo.appwork
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.properties.Delegates

class ResitroServicioActicity : AppCompatActivity() {
    private lateinit var lblname: TextView
    private lateinit var btnVolver2: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.registro_servicio_main)
        asignarvariables()
        asignarreferencias()
        lblname.text = intent.getStringExtra("Username")

        val valorRecibido = intent.getIntExtra("IdServicio", -1) // Utiliza getIntExtra() para recuperar un entero
        if (valorRecibido != -1) {
            mostrarMensaje("Procedemos a listar los proveedores con el servicio: $valorRecibido")
        }
    }

    fun asignarreferencias() {
        val tipoUsuario = intent.getIntExtra("IdTipoUser",0)
        btnVolver2 = findViewById(R.id.btnVolver2)
        btnVolver2.setOnClickListener {
            /*if(tipoUsuario==1){
                mostrarMensaje("Tipo Cliente: ${tipoUsuario.toString()}")
                val intent = Intent(this, DashboardClientActivity::class.java)
            }else if(tipoUsuario==2){
                mostrarMensaje("Tipo Proveedor: ${tipoUsuario.toString()}")
                val intent = Intent(this, DashboardProveedorActivity::class.java)
            }
            else{
                mostrarMensaje("Error al cargar el Tipo de Usuario: ${tipoUsuario.toString()}")
            }
            startActivity(intent)

             */
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