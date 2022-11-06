package com.akagra.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.akagra.quizapp.databinding.ActivityTestBinding
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding
    private val db = FirebaseFirestore.getInstance()
    private var score:Double = 0.0
    private var diff:String = "easy"

    fun UIupdator(){
        binding.tvQuestion.text = ""
        binding.tvQuestionImg.visibility = View.GONE
        binding.tvOptionOne.text = ""
        binding.tvOptionTwo.text = ""
        binding.tvOptionThree.text = ""
        binding.tvOptionFour.text = ""
        var QuestionsList = ArrayList<QueryDocumentSnapshot>()
        val query = db.collection(getDifficulty(score)).whereEqualTo("isOption1Image",false).limit(1)
        query.get().addOnSuccessListener {
                documents ->
            QuestionsList = documents.toCollection(ArrayList<QueryDocumentSnapshot>())
            if (QuestionsList[0].data.getValue("isQuestionImage") as Boolean){
                Glide.with(this).load(QuestionsList[0].data.getValue("question").toString())
                    .into(binding.tvQuestionImg)
                binding.tvQuestionImg.visibility = View.VISIBLE
            }else{
                binding.tvQuestion.text = QuestionsList[0].data.getValue("question").toString()
            }
            binding.tvOptionOne.text =  QuestionsList[0].data.getValue("option1").toString()
            binding.tvOptionTwo.text =  QuestionsList[0].data.getValue("option2").toString()
            binding.tvOptionThree.text =  QuestionsList[0].data.getValue("option3").toString()
            binding.tvOptionFour.text = QuestionsList[0].data.getValue("option4").toString()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        UIupdator()

        binding.btnSubmit.setOnClickListener {
            if (diff=="easy"){
                score += 0.1
            }
            if( diff == "medium"){
                score += 0.2
            }
            if (diff=="hard"){
                score += 0.3
            }

            UIupdator()
        }

//        val db = FirebaseFirestore.getInstance()
//        val collection = db.collection("hard")
//        for (i in 0..40){
//            collection.document(i.toString()).set(hashMapOf(
//                "question" to "https://cdn.discordapp.com/attachments/878916762240970815/1038457389726322698/Screenshot_2022-11-05_at_7.48.44_PM.png",
//                "isQuestionImage" to true,
//                "option1" to "1",
//                "isOption1Image" to false,
//                "option2" to "2",
//                "isOption2Image" to false,
//                "option3" to "4",
//                "isOption3Image" to false,
//                "option4" to "3",
//                "isOption4Image" to false,
//                "answer" to 2,
//                "difficulty" to 2,
//                "topics" to "Non Verbal Reasoning, Mental Ability"
//                ))
//        }
    }

    fun getDifficulty(score:Double): String {
        println("SCOREIS : $score")
        when {
            score<0.3 -> {
                diff = "easy"
                return "easy"
            }
            score<0.7 -> {
                diff = "medium"
                return "medium"
            }
            else -> {
                diff = "hard"
                return "hard"
            }
        }
    }
}