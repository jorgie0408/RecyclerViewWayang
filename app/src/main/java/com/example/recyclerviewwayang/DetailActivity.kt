package com.example.recyclerviewwayang

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.recyclerviewwayang.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // VIEW BINDING
        binding = ActivityDetailBinding.inflate(
            layoutInflater
        )

        setContentView(binding.root)

        // MENERIMA DATA DARI MAINACTIVITY
        val foto = intent.getStringExtra("FOTO") ?: ""

        val nama = intent.getStringExtra("NAMA") ?: ""

        val karakter = intent.getStringExtra("KARAKTER") ?: ""

        val deskripsi = intent.getStringExtra("DESKRIPSI") ?: ""

        // MENAMPILKAN DATA
        binding.ivDetailGambar.load(foto) {

            crossfade(true)

            error(
                android.R.drawable.ic_menu_report_image
            )

        }

        binding.tvDetailNama.text = nama

        binding.chipDetailKarakter.text = karakter

        binding.tvDetailDeskripsi.text = deskripsi

    }

}
