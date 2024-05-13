package com.arturo.appwork.Models

import SQLiteHelper
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.arturo.appwork.Entidades.Solicitud

class SolicitudDao(context:Context) {
    private var sqLiteHelper:SQLiteHelper = SQLiteHelper(context)

    fun cargarSolicitudes():ArrayList<Solicitud>{
        val listaSolicitud:ArrayList<Solicitud> = ArrayList()
        val query = "SELECT * FROM Solicitud"
        val db = sqLiteHelper.readableDatabase
        val cursor: Cursor

        try{
            cursor = db.rawQuery(query,null)
            cursor.moveToFirst()
            do{
                val id:Int = cursor.getInt(cursor.getColumnIndexOrThrow("IdSolicitud"))
                val idCliente:Int = cursor.getInt(cursor.getColumnIndexOrThrow("IdCliente"))
                val idProveedor:Int = cursor.getInt(cursor.getColumnIndexOrThrow("IdProveedor"))
                val fechaRegistro:String = cursor.getString(cursor.getColumnIndexOrThrow("FechaRegistro"))
                val estadoSolicitud:Int = cursor.getInt(cursor.getColumnIndexOrThrow("EstadoSolicitud"))
                val valoracion:Int = cursor.getInt(cursor.getColumnIndexOrThrow("Valoracion"))
                val observaciones:String = cursor.getString(cursor.getColumnIndexOrThrow("Observaciones"))

                val solicitud = Solicitud()
                solicitud.IdSolicitud = id
                solicitud.IdCliente = idCliente
                solicitud.IdProveedor = idProveedor
                solicitud.FechaRegistro = fechaRegistro
                solicitud.IdEstadoSol = estadoSolicitud
                solicitud.Valoracion = valoracion
                solicitud.Observaciones = observaciones

                listaSolicitud.add(solicitud)
            }while (cursor.moveToNext())
        }catch (e:Exception){
            Log.d("===", e.message.toString())
        }finally {
            db.close()
        }

        return listaSolicitud
    }

}
