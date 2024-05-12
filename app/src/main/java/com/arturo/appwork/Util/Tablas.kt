object Tablas {
    const val DATABASE_NAME = "BDWORK.db"
    const val DATABASE_VERSION = 2

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

    object Estado{
        const val TABLE_NAME = "Estado"
        const val COLUMN_ID = "IdEstado"
        const val COLUMN_DESCRIPCION = "Descripcion"
    }

    object Solicitud{
        const val TABLE_NAME = "Solicitud"
        const val COLUMN_ID = "IdSolicitud"
        const val COLUMN_ID_CLIENTE= "IdCliente"
        const val COLUMN_ID_PROVEEDOR = "IdProveedor"
        const val COLUMN_FECHA_REG = "FechaRegistro"
        const val COLUMN_ID_ESTADO =  "EstadoSolicitud"
        const val COLUMN_VALORACION = "Valoracion"
        const val COLUMN_OBSERVACION = "Observaciones"
    }

    object SolicitudHistorial{
        const val TABLE_NAME = "SolicitudHistorial"
        const val COLUMN_ID = "IdSolicitud"
        const val COLUMN_ID_SOL = "IdSolicitudHist"
        const val COLUMN_FECHA_REG = "FechaRegistro"
        const val COLUMN_ID_ESTADO =  "EstadoSolicitud"
        const val COLUMN_OBSERVACION = "Observaciones"
    }

}
