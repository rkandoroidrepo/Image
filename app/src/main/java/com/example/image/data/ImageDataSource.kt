package com.example.image.data

import android.content.Context
import com.example.image.modal.Contact
import com.example.image.modal.Photo
import com.example.image.utils.NetworkStatus
import com.example.image.utils.DataCallbackListener
interface ImageDataSource{

    /**
     * Gets image list
     */
    fun getImages(networkStatus: NetworkStatus,
                  callbackListener: DataCallbackListener<List<Photo>>)

    /**
     * gets different sizes of image
     * using image id
     */
    fun getSize(imageId: String, networkStatus: NetworkStatus)

    /**
     * Gets contact list
     */
    fun getContactList(context: Context): List<Contact>
}