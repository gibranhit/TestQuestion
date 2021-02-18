package mx.com.questionsstress.ui.dashboard.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_search.view.*
import mx.com.questionsstress.R
import mx.com.questionsstress.ui.model.ResultTest
import java.text.SimpleDateFormat
import java.util.*

class SearchTestAdapter (private var list: MutableList<ResultTest>, private var listener: (ResultTest) -> Unit)
    : RecyclerView.Adapter<SearchTestAdapter.SearchViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = SearchViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.item_search, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: SearchTestAdapter.SearchViewHolder, position: Int) {
        holder.setData(list[position])
    }

    inner class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun setData(result: ResultTest){
            with(itemView){
                with(ivLogoTest){
                    Glide.with(this).load(result.image).into(this)
                }
                tvTest.text = result.title
                val actualDate = Date()
                tvDate.text = SimpleDateFormat("dd 'de' MMMM yyyy", Locale("es", "MX")).format(actualDate)
                setOnClickListener {
                    listener.invoke(result)
                }
            }
        }
    }
}