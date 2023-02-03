package com.example.clubreservations.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.clubreservations.R
import com.example.clubreservations.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.openReservationActivityBtn.setOnClickListener { openReservationsActivity() }

    }

    private fun openReservationsActivity() {
        val intent = Intent(this, ReservationsActivity::class.java)
        startActivity(intent)
    }
}