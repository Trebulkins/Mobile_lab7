package com.example.mobile_lab7

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.sql.Time

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val addBut = findViewById<ImageButton>(R.id.addButton)

        addBut.setOnClickListener {
            val aCrime = Intent(this@MainActivity, Criminal_info::class.java)
            aCrime.putExtra("Move", "add")
            startActivity(aCrime)
        }

        @Entity
        data class User(
            @PrimaryKey(autoGenerate = true) val uid: Int,
            @ColumnInfo(name = "crime") val crimeName: String?,
            @ColumnInfo(name = "date") val date: Date?,
            @ColumnInfo(name = "time") val time: Time?
        )
    }
}