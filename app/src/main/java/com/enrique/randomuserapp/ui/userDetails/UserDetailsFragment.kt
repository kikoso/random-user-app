package com.enrique.randomuserapp.ui.userDetails

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.enrique.randomuserapp.R
import com.enrique.randomuserapp.databinding.FragmentUserDetailsBinding
import com.enrique.randomuserapp.model.user.UserView
import com.enrique.randomuserapp.utils.viewBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : Fragment(R.layout.fragment_user_details) {
    private val viewModel: UserDetailsViewModel by viewModels()
    private val binding by viewBinding(FragmentUserDetailsBinding::bind)
    private val args: UserDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userId = args.userId
        viewModel.getUser(userId)
        setUpUsersListener()
    }

    private fun setUpUsersListener() {
        viewModel.loading.observe(
            viewLifecycleOwner, {
                renderLoadingState(it)
            }
        )
        viewModel.userDetails.observe(
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

    private fun renderUsersList(userDetails: UserView) {
        binding.content.visibility = View.VISIBLE
        binding.loading.visibility = View.GONE
        binding.tvName.text = userDetails.firstName
        binding.tvSurname.text = userDetails.lastName
        binding.tvLocation.text = userDetails.location
        binding.tvEmail.text = userDetails.email

        Picasso.get().load(userDetails.picture).into(binding.imvAvatar)
    }
}