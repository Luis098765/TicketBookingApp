package com.example.ticketbookingapp.Activities.TicketDetail

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ticketbookingapp.Activities.Splash.StatusBarColor
import com.example.ticketbookingapp.Domain.FlightModel
import com.example.ticketbookingapp.R

class TicketDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val flight = intent.getSerializableExtra("flight") as FlightModel

        setContent {
            StatusBarColor()

            TicketDetailScreen(
                flight = flight,
                onBackClick = {finish()},
                onDownloadTicketClick = {

                }
            )
        }
    }
}