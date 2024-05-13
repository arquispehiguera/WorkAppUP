package com.arturo.appwork

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arturo.appwork.Entidades.Solicitud
import com.arturo.appwork.Entidades.Usuario

class AdaptadorProveedor: RecyclerView.Adapter<AdaptadorProveedor.MiViewHolder>() {
    private lateinit var context: Context
    private var listaSolicitudes: ArrayList<Solicitud> =   ArrayList()

    fun mostrarSolicitudes(items: ArrayList<Solicitud>){
        this.listaSolicitudes = items
    }
    fun contexto(context: Context){
        this.context = context
    }
    class MiViewHolder(val view: View):RecyclerView.ViewHolder(view) {
        private var servicio = view.findViewById<TextView>(R.id.filaServicio)
        private var nombres = view.findViewById<TextView>(R.id.filaNombres)
        private var fechaservicio = view.findViewById<TextView>(R.id.filaFechaReg)

        var filaAgregar = view.findViewById<ImageButton>(R.id.filaAgregar)
        var filaEliminar = view.findViewById<ImageButton>(R.id.filaEliminar)

        fun bindView(solicitud: Solicitud) {

            nombres.text = solicitud.nombreUsuario.toString()
            servicio.text = solicitud.nombreServicio.toString()
            fechaservicio.text = solicitud.FechaRegistro.toString()
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    )= MiViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_user_service,parent,false)
    )

    override fun getItemCount(): Int {
        return listaSolicitudes.size
    }

    override fun onBindViewHolder(holder: AdaptadorProveedor.MiViewHolder, position: Int) {
        val personaItem = listaSolicitudes[position]
        holder.bindView(personaItem)

        holder.filaAgregar.setOnClickListener{
            //val intent = Intent(context,MainActivity::class.java)
            //context.startActivity(intent)
        }
        holder.filaEliminar.setOnClickListener {

        }
    }

}
