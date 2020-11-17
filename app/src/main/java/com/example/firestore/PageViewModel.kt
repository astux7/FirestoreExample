package com.example.firestore
import androidx.lifecycle.*

 //https://en.proft.me/2019/10/20/firebase-cloud-firestore-and-livedata/

//https://stackoverflow.com/questions/54466799/retrieving-data-from-firestore-using-mvvm-architecture

//https://www.ericdecanini.com/2020/01/27/android-chat-messenger-with-firestore-mvvm-edition-with-dagger-livedata-and-coroutines/

class PageViewModel(private val repo: FBRepository): ViewModel() {

    fun onPostsValuesChange(): LiveData<List<Page>> {
        return  repo.onPostsValuesChange()
    }

    fun stopListeningForPostChanges() = repo.stopListeningForPostChanges()

}