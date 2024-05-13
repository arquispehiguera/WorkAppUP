package com.arturo.appwork
import android.app.AlertDialog
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arturo.appwork.Models.SolicitudDao
import com.arturo.appwork.Util.LocalStorage

class DashboardProveedorActivity : AppCompatActivity() {
    private lateinit var lblNombreProveedor:TextView
    private lateinit var rvListaSolicitud: RecyclerView
    private lateinit var solicitudDao: SolicitudDao
    private var adaptadorProv:AdaptadorProveedor = AdaptadorProveedor()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboardproveedor_main)
        asignarvariables()
        mostrarSolicitudes()
        lblNombreProveedor.text = intent.getStringExtra("Username")
    }
fun mostrarSolicitudes(){
    val listSolicitudes =solicitudDao.cargarSolicitudes()
    adaptadorProv.mostrarSolicitudes(listSolicitudes)
    adaptadorProv.contexto(this)

}
    private fun mostrarMensaje(mensaje: String) {
        AlertDialog.Builder(this)
            .setTitle("Mensaje")
            .setMessage(mensaje)
            .setPositiveButton("OK", null)
            .show()
    }
    fun asignarvariables(){
        lblNombreProveedor = findViewById(R.id.lblNombreProveedor)
        solicitudDao = SolicitudDao(this)
        rvListaSolicitud = findViewById(R.id.rvListaSolicitud)
        rvListaSolicitud.layoutManager = LinearLayoutManager(this)
        rvListaSolicitud.adapter = adaptadorProv
    }
}