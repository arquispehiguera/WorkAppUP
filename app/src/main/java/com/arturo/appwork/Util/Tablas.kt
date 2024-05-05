object Tablas {
    const val DATABASE_NAME = "BDWORK.db"
    const val DATABASE_VERSION = 1

    object TipoUsuario {
        const val TABLE_NAME = "TipoUsuario"
        const val COLUMN_ID = "IdTipoUsuario"
        const val COLUMN_DESCRIPCION = "Descripcion"
    }
    object Servicio {
        const val TABLE_NAME = "Servicio"
        const val COLUMN_ID = "IdServicio"
        const val COLUMN_DESCRIPCION = "Descripcion"
    }

    object Usuario {
        const val TABLE_NAME = "Usuario"
        const val COLUMN_ID = "IdUsuario"
        const val COLUMN_NAME = "Nombre"
        const val COLUMN_DNI = "Dni"
        const val COLUMN_TELEFONO = "Telefono"
        const val COLUMN_EMAIL = "Email"
        const val COLUMN_PASSWORD = "Password"
        const val COLUMN_USUARIO_SERVICIO = "ServiciosID"
        const val COLUMN_ID_TIPO = "IdTipoUsuario"
    }



}
