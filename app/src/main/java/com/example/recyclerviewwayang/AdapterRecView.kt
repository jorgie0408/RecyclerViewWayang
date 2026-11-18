package com.example.recyclerviewwayang

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.recyclerviewwayang.databinding.ItemRecyclerBinding

class AdapterRecView(
    private val listWayang: ArrayList<DcWayang>
) : RecyclerView.Adapter<AdapterRecView.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {

        fun onItemClicked(data: DcWayang)

        fun delData(position: Int)

    }

    fun setOnItemClickCallback(
        callback: OnItemClickCallback
    ) {
        this.onItemClickCallback = callback
    }

    inner class ListViewHolder(
        val binding: ItemRecyclerBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {

        val binding = ItemRecyclerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listWayang.size
    }

    override fun onBindViewHolder(
        holder: ListViewHolder,
        position: Int
    ) {

        val wayang = listWayang[position]

        with(holder.binding) {

            namaWayang.text = wayang.nama

            karakterWayang.text = wayang.karakter

            deskripsiWayang.text = wayang.deskripsi

            // ANIMASI LOADING GAMBAR
            val circularProgress = CircularProgressDrawable(
                gambarWayang.context
            ).apply {

                strokeWidth = 5f
                centerRadius = 30f
                setColorSchemeColors(
                    android.graphics.Color.BLUE
                )
                start()

            }

            // MENAMPILKAN GAMBAR DENGAN COIL
            gambarWayang.load(wayang.foto) {

                crossfade(true)

                placeholder(circularProgress)

                error(
                    android.R.drawable.ic_menu_report_image
                )

            }

            // KLIK GAMBAR UNTUK DETAIL
            gambarWayang.setOnClickListener {

                android.widget.Toast.makeText(
                    gambarWayang.context,
                    "Kamu memilih ${wayang.nama}",
                    android.widget.Toast.LENGTH_SHORT
                ).show()

                onItemClickCallback.onItemClicked(wayang)

            }

            // KLIK NAMA UNTUK DETAIL
            namaWayang.setOnClickListener {

                onItemClickCallback.onItemClicked(wayang)

            }

            // HAPUS DATA
            btnHapus.setOnClickListener {

                val currentPosition =
                    holder.bindingAdapterPosition

                if (currentPosition != RecyclerView.NO_POSITION) {

                    onItemClickCallback.delData(
                        currentPosition
                    )

                }

            }

        }

    }

}