package com.example.clubreservations.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.clubreservations.databinding.ActivityReservationsBinding

class ReservationsActivity : AppCompatActivity() {

    lateinit var binding: ActivityReservationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val actionBar = supportActionBar

        actionBar!!.title = "Reservations"

        actionBar.setDisplayHomeAsUpEnabled(true)

        binding = ActivityReservationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }




}