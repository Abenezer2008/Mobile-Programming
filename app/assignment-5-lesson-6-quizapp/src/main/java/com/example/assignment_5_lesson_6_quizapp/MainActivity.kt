package edu.miu.quizapp.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment_5_lesson_6_quizapp.R
import com.example.assignment_5_lesson_6_quizapp.databinding.ActivityMainBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var examResult: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun question1(view: View) {
        // correct answer: false - B
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked
            when (view.getId()) {
                R.id.radio_q1_b ->
                    if (checked) {
                        examResult +=50
                    }
            }
        }
    }

    fun question2(view: View) {
        // correct answer: true - A
        if (view is CheckBox) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.checkbox1 ->
                    if (checked) {
                        examResult +=50
                    }
            }
        }
    }

    fun resetSolution(view: View) {
        resetAnswer()
    }

    fun resetAnswer(){
        binding.questionOneRadio.clearCheck()
    }

    private fun showResultDialog(title: String, message: String){
        val alertDialog: AlertDialog = this.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton("OK")
                { dialog, _ ->
                    resetAnswer()
                    examResult = 0
                    dialog.dismiss()
                }
            }
            builder.setMessage(message)
                .setTitle(title)
            builder.create()
        }
        alertDialog.show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun submitSolution(view: View) {
        val current = LocalDateTime.now()

        val date = current.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val time = current.format(DateTimeFormatter.ofPattern("HH:mm:ss"))

        val message = if(examResult > 0)
            "Congratulations! You submitted on current $date and $time, Your achieved $examResult%"
        else "No Pass!"
        val title = "Quiz Result"
        showResultDialog(title, message)
    }

}