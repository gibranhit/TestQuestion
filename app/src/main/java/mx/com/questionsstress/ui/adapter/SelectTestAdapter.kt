package mx.com.questionsstress.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_test.view.*
import mx.com.questionsstress.R
import mx.com.questionsstress.ui.adapter.SelectTestAdapter.SelectTestViewHolder
import mx.com.questionsstress.ui.model.Test

class SelectTestAdapter(private var list: MutableList<Test>,
                        private var listener: (Test) -> Unit) : RecyclerView.Adapter<SelectTestViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectTestViewHolder
    = SelectTestViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_test, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: SelectTestViewHolder, position: Int) {
        holder.setUpDate(list[position])
    }

    inner class SelectTestViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun setUpDate(test: Test){
            itemView.apply{
                setOnClickListener {
                    listener.invoke(test)
                }
                tvTest.text = test.name

                ivLogoTest.apply {
                    Glide.with(this).load(test.background).into(this)
                }
            }

        }
    }
}