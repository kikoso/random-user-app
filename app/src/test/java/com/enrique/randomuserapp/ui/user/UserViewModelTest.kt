package com.enrique.randomuserapp.ui.user

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.enrique.randomuserapp.MainCoroutineRule
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import com.enrique.randomuserapp.core.Result
import com.enrique.randomuserapp.core.Failure
import com.enrique.randomuserapp.domain.GetRandomUsersUseCase
import com.enrique.randomuserapp.model.user.UserDomain
import com.enrique.randomuserapp.ui.users.UsersViewModel

import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserViewModelTest : TestCase() {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val userName = "userName"

    private lateinit var userViewModel: UsersViewModel

    @Mock
    private lateinit var getUserUseCase: GetRandomUsersUseCase

    @Before
    fun setup() {
        userViewModel = UsersViewModel(getUserUseCase)
    }

    @Test
    fun `get user success should update data`() {
        val user =
            UserDomain(
                "1234",
                "male",
                "John",
                "Doe",
                "Mr.",
                "john@doe.com",
                "Spain",
                "https://randomuser.me/api/portraits/thumb/women/74.jpg"
            )
        `when`(runBlocking { getUserUseCase.getUsers() }).thenReturn(Result.Success(listOf(user)))

        userViewModel.userList.observeForever {
            assertEquals("1234", it[0].id)
            assertEquals("male", it[0].gender)
            assertEquals("John", it[0].firstName)
            assertEquals("Doe", it[0].lastName)
            assertEquals("Mr.", it[0].title)
            assertEquals("john@doe.com", it[0].email)
            assertEquals("Spain", it[0].location)
            assertEquals("https://randomuser.me/api/portraits/thumb/women/74.jpg", it[0].picture)
        }

        userViewModel.getUsers()
    }

    @Test
    fun `get user fail error network`() {
        val networkError = Result.Error(Failure.NetworkConnection)
        `when`(runBlocking { getUserUseCase.getUsers() }).thenReturn(networkError)
        userViewModel.networkError.observeForever {
            assertEquals(it, true)
        }
        userViewModel.featureError.observeForever {
            assertEquals(it, false)
        }
        userViewModel.generalError.observeForever {
            assertEquals(it, false)
        }
        userViewModel.getUsers()
    }


    @Test
    fun `get user fail error general`() {
        val generalError = Result.Error(Failure.GeneralFailure)
        `when`(runBlocking { getUserUseCase.getUsers() }).thenReturn(generalError)
        userViewModel.featureError.observeForever {
            assertEquals(it, false)
        }
        userViewModel.networkError.observeForever {
            assertEquals(it, false)
        }
        userViewModel.generalError.observeForever {
            assertEquals(it, true)
        }
        userViewModel.getUsers()
    }

}