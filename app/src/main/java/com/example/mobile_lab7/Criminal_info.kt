package com.example.mobile_lab7

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class Criminal_info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_criminal_info)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val datePick = findViewById<Button>(R.id.ChooseDate)
        val timePick = findViewById<Button>(R.id.ChooseTime)

        datePick.setOnClickListener {
            val c = Calendar.getInstance()

            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this,
                { view, year, monthOfYear, dayOfMonth -> datePick.text =
                    (dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        timePick.setOnClickListener {
            val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                if (hour < 10 && minute < 10) timePick.text = "0$hour:0$minute"
                else if (hour < 10) timePick.text = "0$hour:$minute"
                else if (minute < 10) timePick.text = "$hour:0$minute"
                else timePick.text = "$hour:$minute"
            }
            val timePickerDialog = TimePickerDialog(
                this,
                timeSetListener,
                12, // Default hour
                0,  // Default minute
                true // Use 24 hour format
            )
            timePickerDialog.show()
        }

        val exit = findViewById<ImageButton>(R.id.imageButton2)
        exit.setOnClickListener { finish() }
    }
}