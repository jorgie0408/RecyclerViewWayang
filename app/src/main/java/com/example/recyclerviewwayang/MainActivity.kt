package com.example.recyclerviewwayang

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.recyclerviewwayang.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // VIEW BINDING
    private lateinit var binding: ActivityMainBinding

    // DATA WAYANG
    private lateinit var arWayang: Array<String>
    private lateinit var arDeskripsi: Array<String>
    private lateinit var arKarakter: Array<String>
    private lateinit var arFoto: Array<String>

    // ARRAY LIST
    private val listWayang = ArrayList<DcWayang>()

    // RECYCLERVIEW
    private lateinit var rvWayang: androidx.recyclerview.widget.RecyclerView

    // ADAPTER
    private lateinit var adapterWayang: AdapterRecView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // INISIALISASI RECYCLERVIEW
        rvWayang = binding.rvWayang

        // PANGGIL FUNGSI
        siapkanData()

        tambahData()

        tampilkanData()

        // TOGGLE LINEAR DAN GRID
        binding.btnToggleLayout.setOnCheckedChangeListener {
                _, _ ->

            tampilkanData()

        }

    }

    // =====================================
    // 1. FUNGSI SIAPKAN DATA
    // =====================================

    private fun siapkanData() {

        arWayang = resources.getStringArray(
            R.array.namaWayang
        )

        arDeskripsi = resources.getStringArray(
            R.array.deskripsiWayang
        )

        arKarakter = resources.getStringArray(
            R.array.karakterUtamaWayang
        )

        arFoto = resources.getStringArray(
            R.array.gambarWayang
        )

    }

    // =====================================
    // 2. FUNGSI TAMBAH DATA
    // =====================================

    private fun tambahData() {

        listWayang.clear()

        for (position in arWayang.indices) {

            val wayang = DcWayang(

                foto = arFoto[position],

                nama = arWayang[position],

                karakter = arKarakter[position],

                deskripsi = arDeskripsi[position]

            )

            listWayang.add(wayang)

        }

    }

    // =====================================
    // 3. FUNGSI TAMPILKAN DATA
    // =====================================

    private fun tampilkanData() {

        // MEMBUAT ADAPTER
        adapterWayang = AdapterRecView(listWayang)

        // CALLBACK ITEM CLICK DAN HAPUS
        adapterWayang.setOnItemClickCallback(
            object : AdapterRecView.OnItemClickCallback {

                override fun onItemClicked(data: DcWayang) {

                    val intent = Intent(
                        this@MainActivity,
                        DetailActivity::class.java
                    )

                    intent.putExtra("FOTO", data.foto)

                    intent.putExtra("NAMA", data.nama)

                    intent.putExtra("KARAKTER", data.karakter)

                    intent.putExtra("DESKRIPSI", data.deskripsi)

                    startActivity(intent)

                }

                override fun delData(position: Int) {

                    if (position in listWayang.indices) {

                        listWayang.removeAt(position)

                        adapterWayang.notifyItemRemoved(position)

                        adapterWayang.notifyItemRangeChanged(
                            position,
                            listWayang.size - position
                        )

                    }

                }

            }
        )

        // MENGATUR LAYOUT MANAGER
        if (binding.btnToggleLayout.isChecked) {

            rvWayang.layoutManager =
                GridLayoutManager(this, 2)

        } else {

            rvWayang.layoutManager =
                LinearLayoutManager(this)

        }

        // MENGHUBUNGKAN ADAPTER
        rvWayang.adapter = adapterWayang

        // ANIMASI RECYCLERVIEW
        rvWayang.itemAnimator = DefaultItemAnimator()

    }

}
