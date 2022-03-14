package com.example.part1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.widget.TableRow
import android.widget.TextView
import com.example.part1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addRow("Android 3.0", "Honeycomb")
        addRow("Android 4.0", "Ice Cream Sandwich")

        binding.btnAdd.setOnClickListener {
            addRow(
                binding.etAndroidVersion.text.toString().trim(),
                binding.etAndroidCodeNm.text.toString().trim()
            )
        }
    }

    @SuppressLint("ResourceType")
    private fun addRow(versionName: String, codeName: String) {
        val tableRow = TableRow(this)

        val androidVersion = TextView(this)
        androidVersion.text = versionName

        val androidName = TextView(this)
        androidName.text = codeName

        tableRow.addView(androidVersion, 0)
        tableRow.addView(androidName, 1)

        val tvVersionParam = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT).apply {
            setMargins(0, 5, 8, 0)
        }
        tvVersionParam.weight = 1f
        androidVersion.setBackgroundResource(R.color.table_bg)
        androidVersion.layoutParams = tvVersionParam

        val tvCodeNameParam = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT).apply {
            setMargins(0, 2, 0, 0)
        }
        tvCodeNameParam.weight = 1f
        androidName.setBackgroundResource(R.color.table_bg)
        androidName.layoutParams = tvCodeNameParam

        binding.androidTable.addView(tableRow)
    }
}