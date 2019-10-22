package com.example.image.data

import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import com.example.image.modal.Contact
import com.example.image.modal.Photo
import com.example.image.utils.DataCallbackListener
import com.example.image.utils.NetworkStatus

class ImageRepository: ImageDataSource{
    companion object{
        @Volatile
        private var  INSTANCE: ImageRepository? = null
        fun getInstance(): ImageRepository = INSTANCE?: synchronized(this){
            INSTANCE?:ImageRepository().also {
                INSTANCE = it
            }
        }
    }

    override fun getImages(networkStatus: NetworkStatus,
                           callbackListener: DataCallbackListener<List<Photo>>) {

    }

    override fun getSize(imageId: String, networkStatus: NetworkStatus) {

    }

    override fun getContactList(context: Context):List<Contact> {
        var cursor: Cursor = context.contentResolver
            .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null, null, null, null)
        var list  = ArrayList<Contact>()
        while (cursor.moveToNext()) run {
            var name: String =
                cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            var phone: String  =
                cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

            var contact = Contact(name, phone)
            list.add(contact)
        }
        cursor.close()
        return list
    }

}