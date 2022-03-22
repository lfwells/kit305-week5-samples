package com.example.week05permissions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.week05permissions.databinding.ActivityMainBinding

const val REQUEST_CONTACT_LIST = 1

class MainActivity : AppCompatActivity() {
    lateinit var ui : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)

        ui.btnCheckContacts.setOnClickListener {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                when {
                    ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.READ_CONTACTS
                    ) == PackageManager.PERMISSION_GRANTED -> {
                        // You can use the API that requires the permission.
                        readContacts()
                    }
                    shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS) -> {
                        // In an educational UI, explain to the user why your app requires this
                        // permission for a specific feature to behave as expected. In this UI,
                        // include a "cancel" or "no thanks" button that allows the user to
                        // continue using your app without granting the permission.
                        Toast.makeText(this, "We need to access contacts, please consider!", Toast.LENGTH_LONG).show()
                    }
                    else -> {
                        // You can directly ask for the permission.
                        requestPermissions(
                                arrayOf(Manifest.permission.READ_CONTACTS),
                                REQUEST_CONTACT_LIST)
                    }
                }
            }
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CONTACT_LIST -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() &&
                                grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission is granted.
                    readContacts()
                } else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    Toast.makeText(this, "Cannot access contacts, permission denied", Toast.LENGTH_LONG).show()
                }
                return
            }
            // Add other 'when' lines to check for other permissions this app might request.
        }
    }

    private fun readContacts()
    {
        val cur = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
        Toast.makeText(this, "You have ${cur!!.count} contacts", Toast.LENGTH_LONG).show()
    }
}