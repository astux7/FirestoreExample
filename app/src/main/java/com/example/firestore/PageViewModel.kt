package com.example.firestore
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.Observer


class PageViewModel: ViewModel() {

    private val cfm by lazy { FBRepository() }




    fun postsUpdate() {
    }



}