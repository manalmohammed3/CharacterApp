package com.example.characterapp.ui.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.characterapp.R

class CharactersAdapter (private var items: List<Character>)
    : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {
    lateinit var onCharacterClickListener: CharacterClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            RecyclerItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size


    inner class ViewHolder(val binding:RecyclerItemCharacterBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Character) {

            binding.tvName.text = item.name
            binding.tvSpeciesAndStatus.text = "${item.species} - ${item.status}"

            Glide.with(binding.layoutRoot).load(item.image)
                .placeholder(R.drawable.ic_avatar_placeholder)
                .dontAnimate().into(binding.ivAvatar)

            binding.layoutRoot.setOnClickListener {
                onCharacterClickListener.onCharacterClick(item.id, binding.layoutRoot)
            }

        }
    }


    fun setItems(items: List<Character>) {

        this.items = items
        notifyDataSetChanged()

    }

    interface CharacterClickListener {
        fun onCharacterClick(id: Int, itemRoot: ConstraintLayout)
    }
}




