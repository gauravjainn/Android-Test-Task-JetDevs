package com.loginkotlindemo.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.loginkotlindemo.WebService.APIServiceFactory
import com.loginkotlindemo.Repository.LoginResponse
import com.loginkotlindemo.Repository.ValidationRepository

class LoginViewModel: AndroidViewModel {
    private var validationRepository: ValidationRepository
    private var retroRepository: APIServiceFactory
    private val retroObservable: LiveData<LoginResponse>

    constructor(application: Application) : super(application){
        validationRepository = ValidationRepository(application)
        retroRepository = APIServiceFactory()
        retroObservable = retroRepository.loginWebService()
    }

    fun validateCredentials(username:String,passWord:String): LiveData<String>{
        return validationRepository.validateCredentials(username,passWord)
    }

    fun getLoginObservable(): LiveData<LoginResponse> {
        return retroObservable
    }
}