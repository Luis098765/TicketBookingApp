package com.example.ticketbookingapp.Activities.Dashboard

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ticketbookingapp.Activities.Splash.StatusBarColor
import com.example.ticketbookingapp.Domain.LocationModel
import com.example.ticketbookingapp.R
import com.example.ticketbookingapp.ViewModel.MainViewModel

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val locations = remember { mutableStateListOf<LocationModel>() }
    val viewModel = MainViewModel()
    var showLocationLoading by remember { mutableStateOf(true) }
    var from: String = ""
    var to: String = ""
    var classes: String = ""
    var adultPassenger: String = ""
    var childPassenger: String = ""


    StatusBarColor()

    LaunchedEffect(Unit) {
        viewModel.loadLocations().observeForever { result->
            locations.clear()
            locations.addAll(result)
            showLocationLoading = false
        }
    }

    Scaffold (
        bottomBar = { MyBottomBar() }
    ) { paddingValues ->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.darkPurple2))
                .padding(paddingValues = paddingValues)
        ) {
            item { TopBar() }
            item {
                Column (
                    modifier = Modifier
                        .padding(32.dp)
                        .background(
                            colorResource(R.color.darkPurple),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 24.dp)
                ) {
                    val locationNames: List<String> = locations.map { it.Name }

                    YellowTitle("From")
                    DropDownList(
                        items = locationNames,
                        loadingIcon = painterResource(R.drawable.from_ic),
                        hint = "Select origin",
                        showLocationLoading = showLocationLoading
                    ) { selectedItem->
                        from = selectedItem
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    YellowTitle("To")
                    DropDownList(
                        items = locationNames,
                        loadingIcon = painterResource(R.drawable.from_ic),
                        hint = "Select destination",
                        showLocationLoading = showLocationLoading
                    ) { selectedItem->
                        to = selectedItem
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    YellowTitle("Passengers")
                    Row (
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        PassengerCounter(
                            title = "Adult",
                            modifier = Modifier.weight(1f),
                            onItemSelected = {adultPassenger = it}
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        PassengerCounter(
                            title = "Child",
                            modifier = Modifier.weight(1f),
                            onItemSelected = {childPassenger = it}
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row {
                        YellowTitle("Departure date", Modifier.weight(1f))
                        Spacer(modifier = Modifier.width(16.dp))
                        YellowTitle("Return date", Modifier.weight(1f))
                    }

                    DatePickerScreen(modifier = Modifier.weight(1f))

                    Spacer(modifier = Modifier.height(16.dp))

                    val classItems = listOf("Business class", "First class", "Economy class")
                    YellowTitle("Class")
                    DropDownList(
                        items = classItems,
                        loadingIcon = painterResource(R.drawable.seat_black_ic),
                        hint = "Select class",
                        showLocationLoading = showLocationLoading
                    ) { selectedItem->
                        to = selectedItem
                    }
                }
            }
        }
    }
}

@Composable
fun YellowTitle(text: String, modifier:Modifier=Modifier) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        color = colorResource(R.color.orange),
        modifier = modifier
    )
}