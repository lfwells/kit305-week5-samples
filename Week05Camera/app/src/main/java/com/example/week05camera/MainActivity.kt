package com.example.week05camera

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import com.example.week05camera.databinding.ActivityMainBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var ui : ActivityMainBinding

    //step 5
    private val getPermissionResult = registerForActivityResult(ActivityResultContracts.RequestPermission()) { result : Boolean ->
        if (result) {
            // Permission is granted.
            takeAPicture()
        } else {
            Toast.makeText(this, "Cannot access camera, permission denied", Toast.LENGTH_LONG).show()
        }
    }

    //step 6, part 2
    private val getCameraResult = registerForActivityResult(ActivityResultContracts.TakePicture()) { result: Boolean  ->
        //step 7, part 1
        if (result)
        {
            setPic(ui.myImageView)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)

        ui.btnCamera.setOnClickListener {
            requestToTakeAPicture()
        }
    }

    //step 4
    @RequiresApi(Build.VERSION_CODES.M)
    private fun requestToTakeAPicture()
    {
        getPermissionResult.launch(Manifest.permission.CAMERA)
    }

    //step 6, part 1
    private fun takeAPicture() {

            //try {
                val photoFile: File = createImageFile()!!
                val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "com.example.week05camera",
                        photoFile
                )
                getCameraResult.launch(photoURI)
            //} catch (e: Exception) {}

    }

    //step 6, part 3
    lateinit var currentPhotoPath: String

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        return File.createTempFile(
                "JPEG_${timeStamp}_", /* prefix */
                ".jpg", /* suffix */
                storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    //step 7, part 2
    private fun setPic(imageView: ImageView) {
        // Get the dimensions of the View
        val targetW: Int = imageView.measuredWidth
        val targetH: Int = imageView.measuredHeight

        val bmOptions = BitmapFactory.Options().apply {
            // Get the dimensions of the bitmap
            inJustDecodeBounds = true

            BitmapFactory.decodeFile(currentPhotoPath, this)

            val photoW: Int = outWidth
            val photoH: Int = outHeight

            // Determine how much to scale down the image
            val scaleFactor: Int = Math.max(1, Math.min(photoW / targetW, photoH / targetH))

            // Decode the image file into a Bitmap sized to fill the View
            inJustDecodeBounds = false
            inSampleSize = scaleFactor
        }
        BitmapFactory.decodeFile(currentPhotoPath, bmOptions)?.also { bitmap ->
            imageView.setImageBitmap(bitmap)
        }
    }
}