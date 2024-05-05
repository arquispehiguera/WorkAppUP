package com.arturo.appwork
import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arturo.appwork.Util.LocalStorage

class DashboardProveedorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboardproveedor_main)

    }
    private fun mostrarMensaje(mensaje: String) {
        AlertDialog.Builder(this)
            .setTitle("Mensaje")
            .setMessage(mensaje)
            .setPositiveButton("OK", null)
            .show()
    }
}