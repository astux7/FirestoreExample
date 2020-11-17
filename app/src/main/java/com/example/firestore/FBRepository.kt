package com.example.firestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.QuerySnapshot

//https://en.proft.me/2019/10/20/firebase-cloud-firestore-and-livedata/
//https://stackoverflow.com/questions/54466799/retrieving-data-from-firestore-using-mvvm-architecture
//https://www.ericdecanini.com/2020/01/27/android-chat-messenger-with-firestore-mvvm-edition-with-dagger-livedata-and-coroutines/

class FBRepository(val database: FirebaseFirestore) {

    private  val PAGE_COLLECTION = "test"

     //   private val database = FirebaseFirestore.getInstance()
        private val postsValues = MutableLiveData<List<Page>>()
        private lateinit var postsRegistration: ListenerRegistration

//        fun addPost(content: String, onSuccessAction: () -> Unit, onFailureAction: () -> Unit) {
//            val documentReference = database.collection(POSTS_COLLECTION).document()
//            val post = HashMap<String, Any>()
//
//            post[AUTHOR_KEY] = authenticationManager.getCurrentUser()
//            post[CONTENT_KEY] = content
//            post[TIMESTAMP_KEY] = System.currentTimeMillis()
//            post[ID_KEY] = documentReference.id
//
//            documentReference
//                .set(post)
//                .addOnSuccessListener { onSuccessAction() }
//                .addOnFailureListener { onFailureAction() }
//        }

        fun onPostsValuesChange(): LiveData<List<Page>> {
            listenForPostsValueChanges()
            return postsValues
        }

        private fun listenForPostsValueChanges() {
            postsRegistration = database.collection(PAGE_COLLECTION)
                .addSnapshotListener(EventListener<QuerySnapshot> { value, error ->
                    if (error != null || value == null) {
                        return@EventListener
                    }

                    if (value.isEmpty) {
                        postsValues.postValue(emptyList())
                    } else {
                        val posts = ArrayList<Page>()
                        for (doc in value) {
                            val post = doc.toObject(Page::class.java)
                            posts.add(post)
                        }
                        postsValues.postValue(posts)
                    }
                })
        }

        fun stopListeningForPostChanges() = postsRegistration.remove()

//        fun updatePostContent(
//            key: String, content: String,
//            onSuccessAction: () -> Unit, onFailureAction: () -> Unit
//        ) {
//
//            val updatedPost = HashMap<String, Any>()
//         //   updatedPost[CONTENT_KEY] = content
//            database.collection(PAGE_COLLECTION)
//                .document(key)
//                .update(updatedPost)
//                .addOnSuccessListener { onSuccessAction() }
//                .addOnFailureListener { onFailureAction() }
//        }
//
//        fun deletePost(key: String, onSuccessAction: () -> Unit, onFailureAction: () -> Unit) {
//            database.collection(PAGE_COLLECTION)
//                .document(key)
//                .delete()
//                .addOnSuccessListener { onSuccessAction() }
//                .addOnFailureListener { onFailureAction() }
//        }

}