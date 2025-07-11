package com.example.ticketbookingapp.ViewModel

import androidx.lifecycle.LiveData
import com.example.ticketbookingapp.Domain.FlightModel
import com.example.ticketbookingapp.Domain.LocationModel
import com.example.ticketbookingapp.Repository.MainRepository

class MainViewModel {
    private val repository = MainRepository()

    fun loadLocations(): LiveData<MutableList<LocationModel>> {
        return repository.loadLocation()
    }

    fun loadFiltered(from: String, to: String): LiveData<MutableList<FlightModel>> {
        return repository.loadFiltered(from, to)
    }
}