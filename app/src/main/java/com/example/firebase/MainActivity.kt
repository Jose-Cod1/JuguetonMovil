package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.ArrayAdapter
import android.widget.ListView

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var lview: ListView
    private var myarray: ArrayList<String> = ArrayList()
    lateinit var mref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lview= findViewById(R.id.lview)
        var myAdapter: ArrayAdapter<String> = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myarray)
        lview.setAdapter(myAdapter)

        mref = FirebaseDatabase.getInstance().getReference().child("jugueton-d5601")

        val childEventListener = object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                var value=dataSnapshot.child("email").getValue()
                var value2=dataSnapshot.child("mensaje").getValue()
                var value3=dataSnapshot.child("nombre").getValue()
                myarray.add("$value $value2 $value3")
                myAdapter.notifyDataSetChanged()

            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        }


        mref.addChildEventListener(childEventListener)
    }
}
