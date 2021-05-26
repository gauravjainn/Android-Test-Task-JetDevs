package com.loginkotlindemo.Repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.regex.Pattern

class ValidationRepository(application: Application) {

    var application:Application

    init {
        this.application = application
    }

    fun validateCredentials(username:String,password:String): LiveData<String> {
     val loginErrorMessage = MutableLiveData<String>()
      if(!username.trim().isNullOrEmpty()){
             if(password.length<8 && !isPasswordValid(password)){
                 loginErrorMessage.value = "Invalid Password"
             }else{
                 loginErrorMessage.value = "Successful Login"
             }
      }else{
          loginErrorMessage.value = "Invalid User Name"
      }

        return  loginErrorMessage
    }





    fun isEmailValid(email: String): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    fun isPasswordValid(password: String): Boolean{
        val expression  ="^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#\$%^&+=!])(?=\\\\S+\$).{4,}\$";
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }



}
