package com.example.image

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.image.data.ImageRepository

class MainViewModalFactory(imageRepository: ImageRepository): ViewModelProvider.Factory{
    private var imageRepository = imageRepository
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModal(imageRepository = imageRepository) as T
    }

}