package com.arturo.appwork
import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arturo.appwork.Util.LocalStorage

class ResitroServicioActicity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_servicio_main)
        val valorRecibido = intent.getIntExtra("IdServicio", -1) // Utiliza getIntExtra() para recuperar un entero
        if (valorRecibido != -1) {
            mostrarMensaje("Procedemos a listar los proveedores con el servicio: $valorRecibido")
        }
    }
    private fun mostrarMensaje(mensaje: String) {
        AlertDialog.Builder(this)
            .setTitle("Mensaje")
            .setMessage(mensaje)
            .setPositiveButton("OK", null)
            .show()
    }
}