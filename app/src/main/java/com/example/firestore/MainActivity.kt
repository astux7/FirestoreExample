package com.example.firestore

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import org.koin.android.ext.android.inject

//https://medium.com/firebase-developers/android-mvvm-firestore-37c3a8d65404

//https://stackoverflow.com/questions/59185231/firebase-authorisation-using-kotlin-mvvm

// THIS IS COOL https://stackoverflow.com/questions/59874102/how-to-return-livedata-from-repository

class MainActivity : AppCompatActivity() {
     val vm : PageViewModel by inject()

    override fun onStart() {
        super.onStart()
        listenForPostsUpdates()
    }

    override fun onStop() {
        super.onStop()
        vm.stopListeningForPostChanges()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

   //--------
   //  https://stackoverflow.com/questions/59874102/how-to-return-livedata-from-repository
   // https://medium.com/androiddevelopers/viewmodels-and-livedata-patterns-antipatterns-21efaef74a54

    private fun listenForPostsUpdates() {
        vm.onPostsValuesChange()
                .observe(this, Observer(::onPostsUpdate))
    }

    private fun onPostsUpdate(posts: List<Page>) {
        var label: TextView = findViewById<TextView>(R.id.text)
        posts.first().title?.let {
            label.text = it
        }
    }
}