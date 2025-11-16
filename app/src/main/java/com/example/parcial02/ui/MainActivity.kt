package com.example.parcial02

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // conecta con tu XML

        // ejemplo: si tuvieras un TextView con id "textView"
        // val tv = findViewById<TextView>(R.id.textView)
    }
}
