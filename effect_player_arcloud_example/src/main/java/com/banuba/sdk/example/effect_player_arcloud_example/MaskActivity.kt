package com.banuba.sdk.example.effect_player_arcloud_example

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.banuba.sdk.effect_player.Effect
import com.banuba.sdk.example.effect_player_arcloud_example.databinding.ActivityApplyMaskBinding
import com.banuba.sdk.input.CameraDevice
import com.banuba.sdk.input.CameraInput
import com.banuba.sdk.manager.BanubaSdkManager
import com.banuba.sdk.output.SurfaceOutput
import com.banuba.sdk.player.Player
import com.banuba.sdk.player.PlayerTouchListener


/**
 * Sample activity that shows how to apply masks with Banuba SDK.
 * Some Banuba masks can change their appearance if tapping on them.
 */
class MaskActivity : AppCompatActivity() {

    companion object {
        private const val MASK_NAME = "UnluckyWitch"

        private const val REQUEST_CODE_APPLY_MASK_PERMISSION = 1001

        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }

    private val player by lazy(LazyThreadSafetyMode.NONE) {
        Player()
    }

    private val cameraDevice by lazy(LazyThreadSafetyMode.NONE) {
        CameraDevice(requireNotNull(this.applicationContext), this@MaskActivity)
    }

    private val surfaceOutput by lazy(LazyThreadSafetyMode.NONE) {
        SurfaceOutput(binding.surfaceView.holder)
    }

    private val maskUri by lazy(LazyThreadSafetyMode.NONE) {
        Uri.parse(BanubaSdkManager.getResourcesBase())
                .buildUpon()
                .appendPath("effects")
                .appendPath(MASK_NAME)
                .build()
    }

    private var shouldApply = false
    private var effect: Effect? = null

    private lateinit var binding: ActivityApplyMaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApplyMaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        player.use(CameraInput(cameraDevice))
        player.use(surfaceOutput)

        // Set custom OnTouchListener to change mask style.
        binding.surfaceView.setOnTouchListener(PlayerTouchListener(this, player))

        binding.showMaskButton.setOnClickListener {
            shouldApply = !shouldApply

            updateUIState()

            if (shouldApply) {
                // The mask is loaded asynchronously and applied
                effect = player.loadAsync(maskUri.toString())
            } else {
                // The mask is unloaded
                player.loadAsync("")
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (allPermissionsGranted()) {
            cameraDevice.start()
        } else {
            requestPermissions(REQUIRED_PERMISSIONS, REQUEST_CODE_APPLY_MASK_PERMISSION)
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            results: IntArray
    ) {
        if (requireAllPermissionsGranted(permissions, results)) {
            cameraDevice.start()
        } else {
            finish()
        }
        super.onRequestPermissionsResult(requestCode, permissions, results)
    }

    override fun onResume() {
        super.onResume()
        player.play()
    }

    override fun onPause() {
        super.onPause()
        player.pause()
    }

    override fun onStop() {
        cameraDevice.stop()
        super.onStop()
    }

    override fun onDestroy() {
        cameraDevice.close()
        surfaceOutput.close()
        player.close()
        super.onDestroy()
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun updateUIState() {
        binding.showMaskButton.text = if (shouldApply) {
            getString(R.string.hide_mask)
        } else {
            getString(R.string.show_mask)
        }

        binding.maskStyleView.visibility = if (shouldApply) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}