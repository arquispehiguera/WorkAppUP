package com.narvaez.sqlitewa.modelo
import SQLiteHelper
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.arturo.appwork.Entidades.Servicio
import com.arturo.appwork.Entidades.TipoUsuario
import com.arturo.appwork.Entidades.Usuario

class UsuarioDao(context: Context) {
    private var sqLiteHelper:SQLiteHelper = SQLiteHelper(context)
    fun cargarTipoUsuario():ArrayList<TipoUsuario>{
        val listaTipoUsuario:ArrayList<TipoUsuario> = ArrayList()
        val query = "SELECT * FROM TipoUsuario"
        val db = sqLiteHelper.writableDatabase
        val cursor:Cursor
        try{
            cursor = db.rawQuery(query,null)
            cursor.moveToFirst()
            do{
                val IdTipoUsuario:Int = cursor.getInt(cursor.getColumnIndexOrThrow("IdTipoUsuario"))
                val Descripcion:String = cursor.getString(cursor.getColumnIndexOrThrow("Descripcion"))
                val tipoUsuario = TipoUsuario()
                tipoUsuario.IdTipoUsuario = IdTipoUsuario
                tipoUsuario.Descripcion = Descripcion
                listaTipoUsuario.add(tipoUsuario)
            }while (cursor.moveToNext())
        }catch (e:Exception){
            Log.i("===", e.message.toString())
        }finally {
            db.close()
        }
        return listaTipoUsuario;
    }
    fun cargarServicios():ArrayList<Servicio>{
        val listaServicio:ArrayList<Servicio> = ArrayList()
        val query = "SELECT * FROM Servicio"
        val db = sqLiteHelper.writableDatabase
        val cursor:Cursor
        try{
            cursor = db.rawQuery(query,null)
            cursor.moveToFirst()
            do{
                val IdTipoUsuario:Int = cursor.getInt(cursor.getColumnIndexOrThrow("IdServicio"))
                val Descripcion:String = cursor.getString(cursor.getColumnIndexOrThrow("Descripcion"))
                val servicio = Servicio()
                servicio.IdServicio = IdTipoUsuario
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
    fun registrarUsuario(usuario: Usuario):String{
        var respuesta = "";
        val db = sqLiteHelper.writableDatabase
        try {
            val valores = ContentValues()
            valores.put("Nombre", usuario.Nombre)
            valores.put("Dni", usuario.Dni)
            valores.put("Telefono", usuario.Telefono)
            valores.put("Email", usuario.Email)
            valores.put("IdTipoUsuario", usuario.IdTipoUsuario)
            valores.put("Password", usuario.Password)
            valores.put("ServiciosID", usuario.ServiciosID)
            val rpta = db.insert("Usuario",null,valores)
            if(rpta == -1L){
                respuesta = "Error al insertar Usuario"
            }else{
                respuesta = "Se registró correctamente"
            }
        }catch (e:Exception){
            Log.d("===", e.message.toString())
            respuesta = "Ocurrio un error"
        }finally {
            db.close()
        }
        return respuesta
    }
    fun autenticarUsuario(email: String, password: String): Boolean {
        val db = sqLiteHelper.readableDatabase
        var autenticado = false
        try {
            val cursor = db.query(
                "Usuario",
                null,
                "Dni = ? AND Password = ?",
                arrayOf(email, password),
                null,
                null,
                null
            )
            if (cursor != null && cursor.moveToFirst()) {
                autenticado = true
            }
            cursor?.close()
        } catch (e: Exception) {
            Log.e("Error", "Error al autenticar usuario: ${e.message}")
        } finally {
            db.close()
        }
        return autenticado
    }
    fun obtenerUsuarioPorId(idUsuario: String): Usuario? {
        var usuario: Usuario? = null
        val query = "SELECT * FROM Usuario WHERE Dni = ?"
        val db = sqLiteHelper.readableDatabase
        val cursor: Cursor
        try {
            cursor = db.rawQuery(query, arrayOf(idUsuario.toString()))
            if (cursor.moveToFirst()) {
                usuario = Usuario()
                usuario.IdUsuario = cursor.getInt(cursor.getColumnIndexOrThrow("IdUsuario"))
                usuario.Nombre = cursor.getString(cursor.getColumnIndexOrThrow("Nombre"))
                usuario.Dni = cursor.getString(cursor.getColumnIndexOrThrow("Dni"))
                usuario.Telefono = cursor.getString(cursor.getColumnIndexOrThrow("Telefono"))
                usuario.Email = cursor.getString(cursor.getColumnIndexOrThrow("Email"))
                usuario.IdTipoUsuario = cursor.getInt(cursor.getColumnIndexOrThrow("IdTipoUsuario"))
                usuario.Password = cursor.getString(cursor.getColumnIndexOrThrow("Password"))
                usuario.ServiciosID = cursor.getString(cursor.getColumnIndexOrThrow("ServiciosID"))
            }
        } catch (e: Exception) {
            Log.e("Error", "Error al obtener usuario por ID: ${e.message}")
        } finally {
            db.close()
        }
        return usuario
    }

    fun obtenerListaUsuariosPorServicio(idServicio: String): ArrayList<Usuario>{
        val listaUsuarioServ:ArrayList<Usuario> = ArrayList()
        val query = "SELECT * FROM Usuario WHERE ServiciosID = ?"
        val db = sqLiteHelper.readableDatabase
        val cursor: Cursor
        try{
            cursor = db.rawQuery(query,arrayOf(idServicio.toString()))
            cursor.moveToFirst()
            do{
                val IdUsuario = cursor.getInt(cursor.getColumnIndexOrThrow("IdUsuario"))
                val Nombre = cursor.getString(cursor.getColumnIndexOrThrow("Nombre"))
                val Dni = cursor.getString(cursor.getColumnIndexOrThrow("Dni"))
                val Telefono = cursor.getString(cursor.getColumnIndexOrThrow("Telefono"))
                val Email = cursor.getString(cursor.getColumnIndexOrThrow("Email"))
                val IdTipoUsuario = cursor.getInt(cursor.getColumnIndexOrThrow("IdTipoUsuario"))
                val ServiciosID = cursor.getString(cursor.getColumnIndexOrThrow("ServiciosID"))
                val usuario = Usuario()
                usuario.IdUsuario = IdUsuario
                usuario.Nombre = Nombre
                usuario.Dni = Dni
                usuario.Telefono = Telefono
                usuario.Email = Email
                usuario.IdTipoUsuario = IdTipoUsuario
                usuario.ServiciosID = ServiciosID
                listaUsuarioServ.add(usuario)
            }while (cursor.moveToNext())
        }catch (e:Exception){
            Log.i("===", e.message.toString())
        }finally {
            db.close()
        }

        return listaUsuarioServ
    }
}