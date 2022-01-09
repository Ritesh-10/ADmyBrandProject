package com.example.admybrand_assignment

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admybrand_assignment.data.User
import com.example.admybrand_assignment.databinding.ActivityMainBinding
import com.example.admybrand_assignment.ui.Adapter
import com.example.admybrand_assignment.ui.Detail.DetailUserActivity
import com.example.admybrand_assignment.ui.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = Adapter()
        adapter.notifyDataSetChanged()
        adapter.setOnItemClickCallBack(object :Adapter.OnItemClickCallBack
        {
            override fun onItemClicked(data: User) {
                Intent(this@MainActivity,DetailUserActivity::class.java).also{
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME,data.login)
                    startActivity(it)
                }
            }

        })
        viewModel = ViewModelProvider(this,
            ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)


        binding.apply {
            user.layoutManager = LinearLayoutManager(this@MainActivity)
            user.setHasFixedSize(true)
            user.adapter = adapter

            btnSearch.setOnClickListener {
                searchUser()
            }
            etQuery.setOnKeyListener { v, keyCode, event ->
                    if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                        searchUser()
                        return@setOnKeyListener true
                    }
                    return@setOnKeyListener false
                }
            }
        viewModel.getSearchUsers().observe(this,{
            if(it!=null)
            {
                adapter.setList(it)
                showLoading(false)
            }
        })
        }
    private fun searchUser(){
        binding.apply {
            val query=etQuery.text.toString()
            if(query.isEmpty()) return
            showLoading(true)
            viewModel.setSearchUsers(query)
        }
    }
    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}