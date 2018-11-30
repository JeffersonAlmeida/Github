package com.doublef.github.features.list


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.doublef.github.R
import com.doublef.github.model.RepositoryItem
import kotlinx.android.synthetic.main.activity_main_item.view.*

class Adapter: RecyclerView.Adapter<Adapter.ViewHolder>() {

    var onClickItemClickListener: ItemClickListener? = null
    var data: List<RepositoryItem>? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val screen = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.activity_main_item, viewGroup, false)
        return ViewHolder(screen)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = data!![position]
        viewHolder.text.text = item.name
        viewHolder.text.setOnClickListener {
            onClickItemClickListener?.onClickItem(item)
        }
    }

    override fun getItemCount(): Int {
        return if (data != null) data!!.size else 0
    }

    class ViewHolder(screen: View) : RecyclerView.ViewHolder(screen) {
        var text: TextView = screen.text
    }
}