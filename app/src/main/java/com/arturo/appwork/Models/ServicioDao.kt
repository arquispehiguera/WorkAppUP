package com.arturo.appwork.Models
import SQLiteHelper
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.arturo.appwork.Entidades.Servicio

class ServicioDao(context: Context) {
    private var sqLiteHelper:SQLiteHelper = SQLiteHelper(context)
    fun cargarServicio():ArrayList<Servicio>{
        val listaServicio:ArrayList<Servicio> = ArrayList()
        val query = "SELECT * FROM Servicio"
        val db = sqLiteHelper.writableDatabase
        val cursor: Cursor
        try{
            cursor = db.rawQuery(query,null)
            cursor.moveToFirst()
            do{
                val IdServicio:Int = cursor.getInt(cursor.getColumnIndexOrThrow("IdServicio"))
                val Descripcion:String = cursor.getString(cursor.getColumnIndexOrThrow("Descripcion"))
                val servicio = Servicio()
                servicio.IdServicio = IdServicio
                servicio.Descripcion = Descripcion
                listaServicio.add(servicio)
            }while (cursor.moveToNext())
        }catch (e:Exception){
            Log.i("===", e.message.toString())
        }finally {
            db.close()
        }
        return listaServicio;
    }
}