package com.arturo.appwork

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.arturo.appwork.Entidades.CardData
import com.arturo.appwork.Entidades.Servicio
import com.arturo.appwork.Models.ServicioDao

class DashboardClientActivity : AppCompatActivity() {
    private lateinit var servicioDao: ServicioDao
    private lateinit var lstServicios: List<Servicio>
    private lateinit var lblname: TextView
    private lateinit var btnVolver1: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboardcliente_main)
        asignarvariables()
        asignarreferencias()
        initializeDao()
        lstServicios = servicioDao.cargarServicio()
        val professionImageMap = mapOf(
            "Gasfitero" to R.drawable.gasfitero,
            "Electricista" to R.drawable.electricista,
            "Albañil" to R.drawable.albanil,
            "Carpintero" to R.drawable.carpintero
        )
        populateContractedServices(professionImageMap)
        populateGridLayout()
        lblname.text = intent.getStringExtra("Username")
    }
    fun asignarreferencias(){
        btnVolver1 = findViewById(R.id.btnVolver1)
        btnVolver1.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this, LoginActivity::class.java))
        }


    }
    fun asignarvariables(){
        lblname = findViewById(R.id.lblname)
    }
    private fun initializeDao() {
        servicioDao = ServicioDao(this)
    }
    private fun populateContractedServices(professionImageMap: Map<String, Int>) {
        val containerLayout: LinearLayout = findViewById(R.id.horizontalLinearLayout)
        val cardDataList = listOf(
            CardData("Andrez Junior.", "Gasfitero", "03-11-24","Terminado"),
            CardData("Andrez Junior.", "Electricista", "03-11-24","Pendiente"),
            CardData("Andrez Junior.", "Albañil", "03-11-24","Terminado"),
            CardData("Andrez Junior.", "Carpintero", "03-11-24","Terminado")
        )

        for (cardData in cardDataList) {
            val cardView = LayoutInflater.from(this).inflate(R.layout.card_item, containerLayout, false)
            val nameTextView: TextView = cardView.findViewById(R.id.txtnamecard)
            val professionTextView: TextView = cardView.findViewById(R.id.txtespecialidadcard)
            val dateTextView: TextView = cardView.findViewById(R.id.txtfechacard)
            val statusTextView: TextView = cardView.findViewById(R.id.txtstatus)
            val professionImage: ImageView = cardView.findViewById(R.id.professionImageView)

            nameTextView.text = cardData.name
            professionTextView.text = cardData.profession
            dateTextView.text = cardData.date
            statusTextView.text= cardData.status
            when (cardData.status) {
                "Terminado" -> statusTextView.setBackgroundResource(R.drawable.badge_success)
                "Pendiente" -> statusTextView.setBackgroundResource(R.drawable.badge_pendiente)
                "Cancelado" -> statusTextView.setBackgroundResource(R.drawable.badge_danger)
            }
            val professionImagePath = professionImageMap[cardData.profession]
            if (professionImagePath != null) {
                professionImage.setImageResource(professionImagePath)
            }
            containerLayout.addView(cardView)

        }
    }
    private fun populateGridLayout() {
        val tipoUsuario = intent.getIntExtra("IdTipoUser",0)
        val gridLayout = findViewById<GridLayout>(R.id.gridLayout)
        val buttonWidth = resources.getDimensionPixelSize(R.dimen.button_width)
        val buttonHeight = resources.getDimensionPixelSize(R.dimen.button_height)
        val horizontalSpacing = resources.getDimensionPixelSize(R.dimen.horizontal_spacing)
        val verticalSpacing = resources.getDimensionPixelSize(R.dimen.vertical_spacing)
        val columnCount = gridLayout.columnCount
        lstServicios.forEachIndexed { index, cardData ->
            val button = Button(this)
            button.text = cardData.Descripcion
            button.id = cardData.IdServicio
            button.gravity = Gravity.CENTER
            button.setBackgroundColor(ContextCompat.getColor(this, R.color.primary)) // Establece el color de fondo del botón
            button.setTextColor(ContextCompat.getColor(this, R.color.white)) // Establece el color de fondo del botón
            button.elevation = resources.getDimensionPixelSize(R.dimen.button_elevation).toFloat() // Elevación para la sombra
            val row = index / columnCount
            val col = index % columnCount
            val layoutParams = GridLayout.LayoutParams().apply {
                width = buttonWidth
                height = buttonHeight
                leftMargin = horizontalSpacing
                rightMargin = horizontalSpacing
                topMargin = verticalSpacing
                columnSpec = GridLayout.spec(col)
                rowSpec = GridLayout.spec(row)
            }
            button.layoutParams = layoutParams
            gridLayout.addView(button)
            button.setOnClickListener {
                val intent = Intent(this, ResitroServicioActicity::class.java)

                intent.putExtra("IdServicio", it.id)
                intent.putExtra("Username", lblname.text)
                intent.putExtra("IdTipoUser", tipoUsuario)
                startActivity(intent)
            }
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
