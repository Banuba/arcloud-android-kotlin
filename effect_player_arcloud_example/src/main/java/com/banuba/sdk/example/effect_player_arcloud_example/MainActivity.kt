package com.banuba.sdk.example.effect_player_arcloud_example

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.banuba.sdk.example.effect_player_arcloud_example.arcloud.ArCloudMasksActivity
import com.banuba.sdk.example.effect_player_arcloud_example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.openCameraButton.setOnClickListener {
            startActivity(Intent(applicationContext, CameraPreviewActivity::class.java))
        }

        binding.applyMaskButton.setOnClickListener {
            startActivity(Intent(applicationContext, MaskActivity::class.java))
        }

        binding.applyArCloudMasksButton.setOnClickListener {
            startActivity(Intent(applicationContext, ArCloudMasksActivity::class.java))
        }

        binding.recordVideoButton.setOnClickListener {
            startActivity(Intent(applicationContext, VideoRecordingActivity::class.java))
        }
    }
}
