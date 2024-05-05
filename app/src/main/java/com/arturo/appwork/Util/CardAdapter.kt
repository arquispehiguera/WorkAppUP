import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arturo.appwork.Entidades.CardData
import com.arturo.appwork.R

class CardAdapter(private val context: Context, private val data: List<CardData>) :
    RecyclerView.Adapter<CardAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
    }
    override fun getItemCount(): Int {
        return data.size
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
}
