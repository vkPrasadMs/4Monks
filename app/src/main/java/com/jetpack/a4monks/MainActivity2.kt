package com.jetpack.a4monks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jetpack.a4monks.databinding.VideoBinding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding:VideoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = VideoBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main2)




    }
}