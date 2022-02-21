package com.example.kotlin101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var dinnerText = findViewById<TextView>(R.id.dinnerText)


        var dinnerList = ArrayList<String>();
        dinnerList.add("Hamburger");
        dinnerList.add("Pizza");
        dinnerList.add("Mexican");
        dinnerList.add("American");
        dinnerList.add("Chinese");

        dinnerText.text = randomDinner(dinnerList);


        var decideButton = findViewById<Button>(R.id.decideButton);

        decideButton.setOnClickListener {
            dinnerText.text = randomDinner(dinnerList);
        }

        var addButton = findViewById<Button>(R.id.addButton);
        var editText = findViewById<EditText>(R.id.addDinner);
        addButton.setOnClickListener {
            dinnerText.text = editText.text.toString();
        }
    }

    fun randomDinner(list: ArrayList<String>): String {
        val randomIndex = Random.nextInt(list.size);

        return list[randomIndex];
    }
}