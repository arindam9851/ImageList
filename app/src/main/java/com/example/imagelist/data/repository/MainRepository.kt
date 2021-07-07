package com.example.imagelist.data.repository

import com.example.imagelist.data.api.ApiInterface
import com.example.imagelist.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository
constructor(
    private val apiInterface: ApiInterface
) {


    suspend fun getImage(count: Int): Flow<DataState<List<String>>> = flow {
        emit(DataState.Loading)
        try {
            val networkResponse = apiInterface.getImage(count)
            emit(DataState.Success(networkResponse))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}