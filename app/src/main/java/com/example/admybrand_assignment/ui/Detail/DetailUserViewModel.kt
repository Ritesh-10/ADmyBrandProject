package com.example.admybrand_assignment.ui.Detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.admybrand_assignment.api.RetrofitClient
import com.example.admybrand_assignment.data.DetailUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel:ViewModel() {
    val user=MutableLiveData<DetailUserResponse>()

    fun setUserDetail(username:String)
    {
        RetrofitClient.apiinstance.getUserDetail(username).enqueue(object : Callback<DetailUserResponse>{
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>,
            ) {
                if(response.isSuccessful)
                {
                    user.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                Log.d("failure", t.message.toString())
            }

        })
    }
    fun getUserDetail():LiveData<DetailUserResponse>
    {
        return user
    }
}