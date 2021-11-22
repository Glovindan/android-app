package com.example.sreda.ui.list

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.recyclerview.widget.RecyclerView
import com.example.sreda.R
import com.example.sreda.databinding.ActivityListBinding
import com.example.sreda.ui.base.BaseActivity

/**
 * @author s.timirov
 */
class ListActivity : BaseActivity<ListViewModel>(ListViewModel::class) {

    private var binding: ActivityListBinding? = null
    private val adapter: ListAdapter = ListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityListBinding.inflate(layoutInflater)
        this.binding = binding
        setContentView(binding.root)

        binding.loadButton.setOnClickListener { onLoadButtonClick() }
        binding.usersRecyclerView.adapter = adapter

        subscribe()
    }

    private fun subscribe() {
        viewModel.userList.observe { list ->
            adapter.userList = list
        }
        viewModel.errorLiveData.observe {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun onLoadButtonClick() {
        val page = binding?.input?.text?.toString()?.toInt()
        if (page != null) {
            viewModel.loadUsers(page)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
