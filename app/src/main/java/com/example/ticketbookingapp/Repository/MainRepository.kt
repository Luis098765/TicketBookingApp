package com.example.ticketbookingapp.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ticketbookingapp.Domain.FlightModel
import com.example.ticketbookingapp.Domain.LocationModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class MainRepository {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun loadLocation(): LiveData<MutableList<LocationModel>> {
        val listData = MutableLiveData<MutableList<LocationModel>>()
        val ref = firebaseDatabase.getReference("Locations")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<LocationModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(LocationModel::class.java)
                    item?.let { list.add(item) }
                }
                listData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return listData
    }

    fun loadFiltered(from: String, to: String): LiveData<MutableList<FlightModel>> {
        val listData = MutableLiveData<MutableList<FlightModel>>()
        val ref = firebaseDatabase.getReference("Flights")
        val query: Query = ref.orderByChild("from").equalTo(from)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<FlightModel>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(FlightModel::class.java)
                    if (list != null) {
                        if (list.to == to) {
                            lists.add(list)
                        }
                    }
                }
                listData.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return listData
    }
}