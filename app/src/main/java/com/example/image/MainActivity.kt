package com.example.image

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.image.data.ImageRepository
import com.example.image.modal.Contact

class MainActivity : AppCompatActivity() {
    val CONTACT_PERMISSION = 100

    val permission: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    var mainViewModal:MainViewModal? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModal = ViewModelProviders.of(this,
            MainViewModalFactory(ImageRepository.getInstance())).get(MainViewModal::class.java)
        observePermission()
        checkAndRequestPermission()
    }


    fun observePermission(){
        val contactObserver = Observer<List<Contact>> {
           var list: List<Contact>  = it
        }

        val permissionObserver = Observer<Boolean> {status ->
            if(status){
                mainViewModal?.initContact(this)
               mainViewModal?.contacts?.observe(this, contactObserver)
            }
        }
        permission.observe(this, permissionObserver)
    }

    private fun checkAndRequestPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR)
            != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS),
                CONTACT_PERMISSION)
        }else{
            // Permission granted
            permission.value = true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            CONTACT_PERMISSION ->{
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    permission.value = true
                }else{
                    permission.value = false
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
