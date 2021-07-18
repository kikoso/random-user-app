package com.enrique.randomuserapp.ui.userDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enrique.randomuserapp.core.Failure
import com.enrique.randomuserapp.model.user.UserDomain
import com.enrique.randomuserapp.model.user.UserView
import com.enrique.randomuserapp.core.Result
import com.enrique.randomuserapp.domain.GetUserByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(private val getUserByIdUseCase: GetUserByIdUseCase) :
    ViewModel() {

    private val _userDetails = MutableLiveData<UserView>()
    val userDetails: LiveData<UserView> = _userDetails

    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading

    private val _networkError = MutableLiveData<Boolean>()
    val networkError = _networkError

    private val _generalError = MutableLiveData<Boolean>()
    val generalError = _generalError

    private val _featureError = MutableLiveData<Boolean>()
    val featureError = _featureError

    fun getUser(id: String) {
        _loading.postValue(true)
        viewModelScope.launch {
            when (val result = getUserByIdUseCase.getUserById(id)) {
                is Result.Success<UserDomain> -> onGetUserSuccess(result.response)
                is Result.Error -> onGetUserFail(result.failure)
            }
        }
    }

    private fun onGetUserSuccess(userDomain: UserDomain) {
        _loading.postValue(false)
        _userDetails.postValue(userDomain.toUserView())
    }

    private fun onGetUserFail(failure: Failure) {
        _loading.postValue(false)
        when (failure) {
            is Failure.FeatureFailure -> featureError.postValue(true)
            is Failure.GeneralFailure -> generalError.postValue(true)
            is Failure.NetworkConnection -> networkError.postValue(true)
        }
    }
}