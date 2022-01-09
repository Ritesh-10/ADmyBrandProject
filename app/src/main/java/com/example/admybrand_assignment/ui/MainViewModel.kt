package com.example.admybrand_assignment.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.admybrand_assignment.api.RetrofitClient
import com.example.admybrand_assignment.data.User
import com.example.admybrand_assignment.data.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    val listuser=MutableLiveData<ArrayList<User>>()

    fun setSearchUsers(query:String)
    {
        RetrofitClient.apiinstance
            .getSearchUsers(query).enqueue(object : Callback<UserResponse>
            {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>,
                ) {
                   if(response.isSuccessful)
                   {
                       listuser.postValue(response.body()?.items)
                   }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                   Log.d("Failure", t.message.toString())
                }

            })
    }
    fun getSearchUsers():LiveData<ArrayList<User>>
    {
        return listuser
    }

}