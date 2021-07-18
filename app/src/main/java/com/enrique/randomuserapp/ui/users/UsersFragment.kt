package com.enrique.randomuserapp.ui.users

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.enrique.randomuserapp.R
import com.enrique.randomuserapp.databinding.FragmentUsersBinding
import com.enrique.randomuserapp.model.user.UserView
import com.enrique.randomuserapp.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment(R.layout.fragment_users) {
    private val viewModel: UsersViewModel by viewModels()
    private val binding by viewBinding(FragmentUsersBinding::bind)
    private var adapter = UsersAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUsers()
        binding.rvUsers.layoutManager = LinearLayoutManager(context)
        binding.rvUsers.adapter = adapter
        setUpUsersListener()

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getUsers()
            binding.loading.visibility = View.VISIBLE
        }
    }

    private fun setUpUsersListener() {
        viewModel.loading.observe(
            viewLifecycleOwner, {
                renderLoadingState(it)
            }
        )
        viewModel.userList.observe(
            viewLifecycleOwner, {
                renderUsersList(it)
            }
        )
        viewModel.networkError.observe(
            viewLifecycleOwner, {
                renderNetworkError(it)
            }
        )
        viewModel.featureError.observe(
            viewLifecycleOwner, {
                renderFeatureError(it)
            }
        )
        viewModel.generalError.observe(
            viewLifecycleOwner, {
                renderGeneralError(it)
            }
        )
    }

    private fun renderLoadingState(loading: Boolean) {
        if (loading) {
            binding.loading.visibility = View.VISIBLE
        } else {
            binding.loading.visibility = View.GONE
        }
    }

    private fun renderError(error: Boolean, msg: String, errorTv: TextView) {
        if (error) {
            binding.rvUsers.visibility = View.GONE
            errorTv.visibility = View.VISIBLE
            errorTv.text = msg
        } else {
            errorTv.visibility = View.GONE
        }
    }

    private fun renderNetworkError(error: Boolean) {
        renderError(error, getString(R.string.network_error), binding.tvError)

    }

    private fun renderFeatureError(error: Boolean) {
        renderError(error, getString(R.string.user_not_found_error), binding.tvError)

    }

    private fun renderGeneralError(error: Boolean) {
        renderError(error, getString(R.string.general_error), binding.tvError)
    }

    private fun renderUsersList(userList: MutableList<UserView>) {
        binding.swipeRefreshLayout.isRefreshing = false
        binding.loading.visibility = View.GONE
        binding.rvUsers.visibility = View.VISIBLE
        adapter.users.clear()
        adapter.users.addAll(userList)
        adapter.notifyDataSetChanged()
    }
}