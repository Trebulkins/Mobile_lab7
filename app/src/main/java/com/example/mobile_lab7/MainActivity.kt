package com.example.mobile_lab7

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import java.sql.Date
import java.sql.Time

class MainActivity : AppCompatActivity() {
    @Entity
    data class Crime(
        @PrimaryKey(autoGenerate = true) val cid: Int,
        @ColumnInfo(name = "crime") val crimeName: String?,
        @ColumnInfo(name = "date") val date: Date?,
        @ColumnInfo(name = "time") val time: Time?
    )

    @Dao
    interface CrimeDao {
        @Query("SELECT * FROM crime")
        fun getAll(): List<Crime>

        @Query("SELECT * FROM crime WHERE cid IN (:crimeIds)")
        fun loadAllByIds(crimeIds: IntArray): List<Crime>

        @Insert
        fun insertAll(vararg crimes: Crime)

        @Delete
        fun delete(crime: Crime)
    }

    @Database(entities = arrayOf(Crime::class), version = 1)
    abstract class CrimeDatabase : RoomDatabase() {
        abstract fun CrimeDao(): CrimeDao
    }

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

        // get the instance of the application's database
        val db = Room.databaseBuilder(
            applicationContext, CrimeDatabase::class.java, "CRIME_DATABASE"
        ).build()

        // create instance of DAO to access the entities
        val userDao = db.CrimeDao()

        // using the same DAO perform the Database operations
        val users: List<Crime> = userDao.getAll()
    }
}