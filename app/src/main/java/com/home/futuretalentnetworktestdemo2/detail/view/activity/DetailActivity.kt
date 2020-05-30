package com.home.futuretalentnetworktestdemo2.detail.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import coil.transform.CircleCropTransformation
import com.home.futuretalentnetworktestdemo2.detail.model.viewdata.DetailViewData
import com.home.futuretalentnetworktestdemo2.detail.model.viewstate.DetailViewState
import com.home.futuretalentnetworktestdemo2.detail.model.viewstate.Loading
import com.home.futuretalentnetworktestdemo2.detail.model.viewstate.NetworkError
import com.home.futuretalentnetworktestdemo2.detail.model.viewstate.Success
import com.home.futuretalentnetworktestdemo2.detail.viewmodel.DetailViewModel
import com.home.futuretalentnetworktestdemo2.R
import com.home.futuretalentnetworktestdemo2.common.base.BaseActivity
import com.home.futuretalentnetworktestdemo2.common.extension.click
import com.home.futuretalentnetworktestdemo2.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : BaseActivity() {

    companion object {
        fun start(context: Activity, bundle: Bundle? = null) {
            val intent = Intent(context, DetailActivity::class.java)
            bundle?.apply { intent.putExtras(this) }
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        initializeViewModel()
    }

    private fun initializeView() {
        binding = binding(this, R.layout.activity_detail)
        binding.imageViewCloseBlack.click { finish() }
    }

    private fun initializeViewModel() {
        intent?.extras?.let { bundle ->
            viewModel =
                ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)
            val userName = bundle.getString(getString(R.string.activity_list_key_user_name))
            viewModel.setUserName(userName!!)
            viewModel.requestData()
            viewModel.getViewState().observe(this, Observer { setViewState(it) })
        }
    }

    private fun setViewState(viewState: DetailViewState) {
        when (viewState) {
            is Loading -> setProgress(true)
            is Success -> {
                setProgress(false)
                showSuccess(viewState.data)
            }
            is NetworkError -> {
                setProgress(false)
                showError(viewState.message)
            }
        }
    }

    private fun setProgress(isLoading: Boolean) {
        if (isLoading) {
            showProgress()
        } else {
            hideProgress()
        }
    }

    private fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progress.visibility = View.GONE
    }

    private fun showSuccess(viewData: DetailViewData) {
        binding.constraintLayoutDetail.visibility = View.VISIBLE
        binding.imageViewAvatar.load(viewData.pojo.avatar_url) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
        binding.textViewName.text = viewData.pojo.name
        binding.textViewBio.text = viewData.pojo.bio
        binding.textViewLogin.text = viewData.pojo.login
        binding.textViewLocation.text = viewData.pojo.location
        binding.textViewBlog.text = viewData.pojo.blog
        if (viewData.pojo.site_admin) {
            binding.cardViewStaff.visibility = View.VISIBLE
        } else {
            binding.cardViewStaff.visibility = View.GONE
        }
    }

    private fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}