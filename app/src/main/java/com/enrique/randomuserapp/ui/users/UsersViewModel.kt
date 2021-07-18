package com.enrique.randomuserapp.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enrique.randomuserapp.core.Failure
import com.enrique.randomuserapp.domain.GetRandomUsersUseCase
import com.enrique.randomuserapp.model.user.UserDomain
import com.enrique.randomuserapp.model.user.UserView
import com.enrique.randomuserapp.core.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val getRandomUsersUseCase: GetRandomUsersUseCase) :
    ViewModel() {

    private val _userList = MutableLiveData<MutableList<UserView>>()
    val userList: LiveData<MutableList<UserView>> = _userList

    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading

    private val _networkError = MutableLiveData<Boolean>()
    val networkError = _networkError

    private val _generalError = MutableLiveData<Boolean>()
    val generalError = _generalError

    private val _featureError = MutableLiveData<Boolean>()
    val featureError = _featureError

    fun getUsers() {
        _loading.postValue(true)
        viewModelScope.launch {
            when (val result = getRandomUsersUseCase.getUsers()) {
                is Result.Success<List<UserDomain>> -> onGetUserSuccess(result.response)
                is Result.Error -> onGetUserFail(result.failure)
            }
        }
    }

    private fun onGetUserSuccess(userDomain: List<UserDomain>) {
        val usersViewList = mutableListOf<UserView>()
        userDomain.forEach {
            usersViewList.add(it.toUserView())
        }
        _userList.postValue(usersViewList)
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