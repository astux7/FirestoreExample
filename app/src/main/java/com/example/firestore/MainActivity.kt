package com.example.firestore

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer


//https://medium.com/firebase-developers/android-mvvm-firestore-37c3a8d65404


class MainActivity : AppCompatActivity() {
    private val cfm by lazy { FBRepository() }

    override fun onStart() {
        super.onStart()
        listenForPostsUpdates()
    }

    private fun listenForPostsUpdates() {
        cfm.onPostsValuesChange()
            .observe(this, Observer(::onPostsUpdate))
    }

    private fun onPostsUpdate(posts: List<Page>) {
        var label: TextView = findViewById<TextView>(R.id.text)
        posts.first().title?.let {
            label.text = it
        }
    }

    override fun onStop() {
        super.onStop()
        cfm.stopListeningForPostChanges()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}