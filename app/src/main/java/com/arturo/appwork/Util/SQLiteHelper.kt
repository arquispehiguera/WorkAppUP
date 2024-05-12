import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context): SQLiteOpenHelper(context,
    Tablas.DATABASE_NAME, null, Tablas.DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val sqlTipoUsuario = "CREATE TABLE IF NOT EXISTS ${Tablas.TipoUsuario.TABLE_NAME} " +
                " (${Tablas.TipoUsuario.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " ${Tablas.TipoUsuario.COLUMN_DESCRIPCION} TEXT NOT NULL); "
        db.execSQL(sqlTipoUsuario)
        val tipoUsuarios = listOf("Cliente", "Proveedor")
        insertTipoUsuarios(db, Tablas.TipoUsuario.TABLE_NAME, Tablas.TipoUsuario.COLUMN_DESCRIPCION, tipoUsuarios)


        val sqlServicio = "CREATE TABLE IF NOT EXISTS ${Tablas.Servicio.TABLE_NAME} " +
                " (${Tablas.Servicio.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " ${Tablas.Servicio.COLUMN_DESCRIPCION} TEXT NOT NULL); "
        db.execSQL(sqlServicio)
        val tipoServicios = listOf("Gasfitero", "Electricista","Albañil","Carpintero")
        insertServicios(db, Tablas.Servicio.TABLE_NAME, Tablas.Servicio.COLUMN_DESCRIPCION, tipoServicios)

        val sqlUsuario = "CREATE TABLE IF NOT EXISTS ${Tablas.Usuario.TABLE_NAME} (" +
                "${Tablas.Usuario.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${Tablas.Usuario.COLUMN_NAME} TEXT NOT NULL, " +
                "${Tablas.Usuario.COLUMN_DNI} TEXT NOT NULL, " +
                "${Tablas.Usuario.COLUMN_TELEFONO} TEXT NOT NULL, " +
                "${Tablas.Usuario.COLUMN_EMAIL} TEXT NOT NULL, " +
                "${Tablas.Usuario.COLUMN_PASSWORD} TEXT NOT NULL, " +
                "${Tablas.Usuario.COLUMN_USUARIO_SERVICIO} TEXT NOT NULL, " +
                "${Tablas.Usuario.COLUMN_ID_TIPO} INTEGER, " +
                "FOREIGN KEY(${Tablas.Usuario.COLUMN_ID_TIPO}) REFERENCES ${Tablas.TipoUsuario.TABLE_NAME}(${Tablas.TipoUsuario.COLUMN_ID}));"
        db.execSQL(sqlUsuario)

        val sqlEstado = "CREATE TABLE IF NOT EXISTS ${Tablas.Estado.TABLE_NAME} " +
                " (${Tablas.Estado.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " ${Tablas.Estado.COLUMN_DESCRIPCION} TEXT NOT NULL); "
        db.execSQL(sqlEstado)
        val Estado = listOf("Proveedor Trabajando", "En Pausa", "Finalizado", "Cancelado", "Anulado","Pendinete Validación")
        insertEstado(db, Tablas.Estado.TABLE_NAME, Tablas.Estado.COLUMN_DESCRIPCION, Estado)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${Tablas.TipoUsuario.TABLE_NAME}")
        db.execSQL("DROP TABLE IF EXISTS ${Tablas.Servicio.TABLE_NAME}")
        db.execSQL("DROP TABLE IF EXISTS ${Tablas.Usuario.TABLE_NAME}")
        db.execSQL("DROP TABLE IF EXISTS ${Tablas.Estado.TABLE_NAME}")
        onCreate(db)
    }

    private fun insertEstado(db: SQLiteDatabase, tableName: String, columnName: String, descripciones: List<String>) {
        val contentValuesList = ArrayList<ContentValues>()
        for (descripcion in descripciones) {
            val values = ContentValues()
            values.put(columnName, descripcion)
            contentValuesList.add(values)
        }
        db.beginTransaction()
        try {
            for (values in contentValuesList) {
                db.insert(tableName, null, values)
            }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }


    private fun insertTipoUsuarios(db: SQLiteDatabase, tableName: String, columnName: String, descripciones: List<String>) {
        val contentValuesList = ArrayList<ContentValues>()
        for (descripcion in descripciones) {
            val values = ContentValues()
            values.put(columnName, descripcion)
            contentValuesList.add(values)
        }
        db.beginTransaction()
        try {
            for (values in contentValuesList) {
                db.insert(tableName, null, values)
            }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }
    private fun insertServicios(db: SQLiteDatabase, tableName: String, columnName: String, descripciones: List<String>) {
        val contentValuesList = ArrayList<ContentValues>()
        for (descripcion in descripciones) {
            val values = ContentValues()
            values.put(columnName, descripcion)
            contentValuesList.add(values)
        }
        db.beginTransaction()
        try {
            for (values in contentValuesList) {
                db.insert(tableName, null, values)
            }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }

}
