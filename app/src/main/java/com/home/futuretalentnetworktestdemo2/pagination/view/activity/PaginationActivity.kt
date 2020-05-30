package com.home.futuretalentnetworktestdemo2.pagination.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.home.futuretalentnetworktestdemo2.pagination.view.adapter.PaginationAdapter
import com.home.futuretalentnetworktestdemo2.pagination.viewmodel.PaginationViewModel
import com.home.futuretalentnetworktestdemo2.R
import com.home.futuretalentnetworktestdemo2.common.base.BaseActivity
import com.home.futuretalentnetworktestdemo2.databinding.ActivityPaginationBinding
import javax.inject.Inject

class PaginationActivity : BaseActivity() {

    companion object {
        fun start(context: Activity) {
            val intent = Intent(context, PaginationActivity::class.java)
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: PaginationViewModel
    private lateinit var binding: ActivityPaginationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        initializeViewModel()
    }

    private fun initializeView() {
        binding = binding(this, R.layout.activity_pagination)
    }

    private fun initializeViewModel() {
        viewModel =
            ViewModelProviders.of(this, viewModelFactory)
                .get(PaginationViewModel::class.java)
        val adapter = PaginationAdapter()
        binding.recyclerView.adapter = adapter
        viewModel.pagedListLiveData?.observe(this, Observer { adapter.submitList(it) })
    }
}
