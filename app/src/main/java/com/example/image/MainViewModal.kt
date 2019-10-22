package com.example.image

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.image.data.ImageRepository
import com.example.image.modal.Contact
import com.example.image.modal.Photo
import com.example.image.utils.DataCallbackListener
import com.example.image.utils.ErrorCode
import com.example.image.utils.NetworkStatus
import java.util.*

class MainViewModal(imageRepository: ImageRepository) : ViewModel() {

    private val imageDataSource: ImageRepository = ImageRepository.getInstance()
    val contacts = MutableLiveData<List<Contact>>()

    val images: MutableLiveData<List<Photo>> by lazy {
        MutableLiveData<List<Photo>>()
    }

    val showProgressBar: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }


    fun initContact(context: Context){
        showProgressBar.value = true
        contacts.value =  imageDataSource.getContactList(context)
    }

    fun getImages(networkStatus: NetworkStatus){
        showProgressBar.value = true
        imageDataSource.getImages(networkStatus, object :DataCallbackListener<List<Photo>>{
            override fun onSuccess(response: List<Photo>) {
                if(!response.isEmpty()){
                    images.value = response
                }
                showProgressBar.value = false

            }

            override fun onError(errorCode: Int) {
                if (errorCode == ErrorCode.CONNECTION_PROBLEM){
                    errorMessage.value = "Connection problem"
                }else{
                    errorMessage.value = "Network problem"

                }
                showProgressBar.value = false
            }
        })
    }
}