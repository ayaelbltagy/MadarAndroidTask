package com.example.madarandroid.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madarandroid.data.data.entity.UserEntity
import com.example.madarandroid.data.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _usersList = MutableStateFlow(emptyList<UserEntity>())
    val usersList = _usersList.asStateFlow()


    fun getUsers() {
        viewModelScope.launch(IO) {
            repository.getAllUsers().collectLatest {
                _usersList.tryEmit(it)
            }
        }
    }

    fun insertUser(user: UserEntity) {
        viewModelScope.launch(IO) {
            repository.insert(user)
        }
    }


    // views values
    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    fun setUserName(name: String) {
        _userName.tryEmit(name)
    }

    private val _userAge= MutableStateFlow("")
    val userAge = _userAge.asStateFlow()

    fun setUserAge(age: String) {
        _userAge.tryEmit(age)
    }

    private val _userJob= MutableStateFlow("")
    val userJob = _userJob.asStateFlow()

    fun setUserJob(age: String) {
        _userJob.tryEmit(age)
    }

    private val _userGender= MutableStateFlow("")
    val userGender = _userGender.asStateFlow()

    fun setUserGender(age: String) {
        _userGender.tryEmit(age)
    }
}