package com.example.ticketbookingapp.Activities.SeatSelect

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ticketbookingapp.Activities.Splash.StatusBarColor
import com.example.ticketbookingapp.Activities.TicketDetail.TicketDetailActivity
import com.example.ticketbookingapp.Domain.FlightModel
import com.example.ticketbookingapp.R

class SeatSelectActivity : AppCompatActivity() {
    private lateinit var flight: FlightModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        flight = intent.getSerializableExtra("flight") as FlightModel

        setContent {
            StatusBarColor()

            SeatListScreen(
                flight = flight,
                onBackClick = {
                    finish()
                },
                onConfirm = {
                    val intent = Intent(this, TicketDetailActivity::class.java).apply {
                        putExtra("flight", flight)
                    }
                    startActivity(intent, null)
                }
            )
        }
    }
}