package com.akagra.quizapp

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class GetQuestions {
    private val db = FirebaseFirestore.getInstance()

//    private fun randomArray(maxNum:Int): Array<Int> {
//        val a = HashSet<Int>()
//        while ( a.size<10){
//            a.add((0..maxNum).random())
//        }
//        println("HereIsAAA : $a")
//        return a.toTypedArray()
//    }
//
//    private fun getMaxSize(key: String): Int {
//        GlobalScope.launch(Dispatchers.IO) {
//            db.collection("stats").document(key).get().result.get("questions")
//        }
//    }


}