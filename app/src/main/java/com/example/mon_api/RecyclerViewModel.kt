package com.example.mon_api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import com.google.gson.Gson

class RecyclerViewModel : ViewModel() {

    private val _widgetData = MutableLiveData<List<Widget>>()
    val widgetData: LiveData<List<Widget>> get() = _widgetData

    fun fetchDataFromApi() {
        val apiLink = "https://run.mocky.io/v3/06780b47-23b7-4218-829b-7e5b7ed30fb3"
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = OkHttpClient().newCall(Request.Builder().url(apiLink).build()).execute()
                val responseData = response.body?.string()

                withContext(Dispatchers.Main) {
                    if (responseData != null) {
                        val apiResponse = Gson().fromJson(responseData, ApiResponse::class.java)
                        _widgetData.value = apiResponse.widgets
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
