package com.home.futuretalentnetworktestdemo2.list.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.home.futuretalentnetworktestdemo2.R
import com.home.futuretalentnetworktestdemo2.common.extension.click
import com.home.futuretalentnetworktestdemo2.databinding.ActivityListRecyclerViewItemBinding
import com.home.futuretalentnetworktestdemo2.list.model.pojo.UserBriefPojo

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    var setOnItemClickListener: ((login: String) -> Unit)? = null

    private var list: MutableList<UserBriefPojo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ActivityListRecyclerViewItemBinding
            .inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userBriefPojo = list[position]
        holder.bindData(userBriefPojo, setOnItemClickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(list: MutableList<UserBriefPojo>) {
        this.list = list
    }

    class ViewHolder(binding: ActivityListRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val avatarImageView: ImageView = binding.imageViewAvatar
        private val loginTextView: TextView = binding.textViewLogin
        private val staffCardView: CardView = binding.cardViewStaff
        private val rootConstraintLayout: ConstraintLayout = binding.constraintLayoutRoot

        fun bindData(
            userBriefPojo: UserBriefPojo?,
            clickItemListener: ((login: String) -> Unit)?
        ) {
            userBriefPojo?.apply {
                loginTextView.text = login
                avatarImageView.load(avatar_url) {
                    crossfade(true)
                    placeholder(R.color.color_DAC6AC)
                    transformations(CircleCropTransformation())
                }
                rootConstraintLayout.click { clickItemListener?.invoke(login) }
                if (site_admin) {
                    staffCardView.visibility = View.VISIBLE
                } else {
                    staffCardView.visibility = View.GONE
                }
            }
        }
    }
}