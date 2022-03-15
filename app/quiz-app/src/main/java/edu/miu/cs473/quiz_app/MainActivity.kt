package edu.miu.cs473.quiz_app

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import edu.miu.cs473.quiz_app.databinding.ActivityMainBinding
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
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.question1_choice1 ->
                    if (checked) {
                        examResult +=50
                    }
            }
        }
    }

    fun question2(view: View) {
        if (view is CheckBox) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.checkbox2 ->
                    if (checked) {
                        examResult +=50
                    }
            }
        }
    }

    fun resetSolution(v: View) {
        resetAnswer()
    }

    fun resetAnswer(){
        binding.questionOneRadio.clearCheck()
        if (binding.checkbox1.isChecked){
            binding.checkbox1.toggle()
        }
        if(binding.checkbox2.isChecked){
            binding.checkbox2.toggle()
        }
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
            "Congratulations! You submitted on current $date and $time, You achieved $examResult%"
        else "No Pass!"
        val title = "Quiz Result"
        showResultDialog(title, message)
    }
}