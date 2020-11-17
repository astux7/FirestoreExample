package com.example.firestore

import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

object CuteModule {
    private val database = FirebaseFirestore.getInstance()
    val module = module {
        single { FBRepository(database) }
        single { PageViewModel(get()) }
    }
}
