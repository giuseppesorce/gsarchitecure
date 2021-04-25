package com.giuseppesorce.gsarchitecture.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.giuseppesorce.gsarchitecture.R
import com.giuseppesorce.gsarchitecture.models.Beer

import kotlin.properties.Delegates

/**
 * @author Giuseppe Sorce
 */
class BeersListAdapter(items: List<Beer> = emptyList()) :
    RecyclerView.Adapter<BeersListAdapter.ViewHolderItem>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItem =
        ViewHolderItem(
            LayoutInflater.from(parent?.context).inflate(
                R.layout.item_beer,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = itemsList.size
    var onActionClickListener: ((action: Beer, position: Int) -> Unit)? = null

    override fun onBindViewHolder(holder: ViewHolderItem, position: Int) {
        var view = holder.bindItems(itemsList[position])
        view.setOnClickListener {
            onActionClickListener?.invoke(itemsList[position], position)
        }
    }

    var itemsList by Delegates.observable(items) { _, _, _ -> notifyDataSetChanged() }

    class ViewHolderItem(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvName: TextView by lazy { itemView.findViewById<TextView>(R.id.tvName) }
        val ivBeer: ImageView by lazy { itemView.findViewById<ImageView>(R.id.ivBeer) }

        fun bindItems(item: Beer): View {
            tvName.text = item.name
            ivBeer.load(item.image) {
                size(300, 300)
                scale(Scale.FIT)
                transformations(RoundedCornersTransformation(0f, 0f, 0f, 0f))
            }
            return itemView
        }
    }
}