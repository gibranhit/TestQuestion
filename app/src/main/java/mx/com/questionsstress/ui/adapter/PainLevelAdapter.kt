package mx.com.questionsstress.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_pain_level.view.*
import mx.com.questionsstress.R
import mx.com.questionsstress.ui.model.PainLevel

class PainLevelAdapter(private var list: MutableList<PainLevel>, private var listener: (PainLevel, Int) -> Unit)
    : RecyclerView.Adapter<PainLevelAdapter.PainLevelViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PainLevelViewHolder
    = PainLevelViewHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_pain_level, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PainLevelViewHolder, position: Int) {
        holder.setUpDate(position)
    }

    inner class PainLevelViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun setUpDate(position: Int){
            val pain = list[position]
            with(itemView.ivPainLevel){
                Glide.with(this).load(pain.image).into(this)
                setOnClickListener {
                    listener.invoke(pain, position)
                }
            }
        }
    }
}