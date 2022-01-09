package com.example.admybrand_assignment.ui.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.admybrand_assignment.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_USERNAME="extra_username"
    }
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val username = intent.getStringExtra(EXTRA_USERNAME)

        viewModel=ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)

        if (username != null) {
            viewModel.setUserDetail(username)
        }
        viewModel.getUserDetail().observe(this,
        {
            if(it!=null)
            {
                binding.apply {
                    name.text=it.name
                    Username.text=it.login
                    followers.text="${it.followers}Followers"
                    following.text="${it.following}Following"
                    Glide.with(this@DetailUserActivity).load(it.avatar_url).transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop().into(profile)
                }
            }
        })
    }
}