package mx.com.questionsstress.ui.dashboard.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_search.view.*
import mx.com.questionsstress.R
import mx.com.questionsstress.domain.models.response.ResultResponse
import mx.com.questionsstress.ui.model.ResultTest
import java.text.SimpleDateFormat
import java.util.*

class SearchTestAdapter (private var list: MutableList<ResultResponse>, private var listener: (ResultResponse) -> Unit)
    : RecyclerView.Adapter<SearchTestAdapter.SearchViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = SearchViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.item_search, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: SearchTestAdapter.SearchViewHolder, position: Int) {
        holder.setData(list[position])
    }

    inner class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun setData(result: ResultResponse) {
            with(itemView){
                with(ivLogoTest){
                    val image = getFace(result.score)
                    Glide.with(this).load(image).into(this)
                }
                tvTest.text = result.title
                tvDate.text = result.date
                setOnClickListener {
                    listener.invoke(result)
                }
            }
        }
    }

    private fun getFace(count: Int): Int = when {
        count <= 22 -> R.drawable.ic_5
        count <= 32 -> R.drawable.ic_4
        count <= 48 -> R.drawable.ic_3
        count <= 60 -> R.drawable.ic_2
        else -> R.drawable.ic_1
    }
}