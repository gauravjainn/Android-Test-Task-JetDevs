package com.loginkotlindemo.Repository

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class LoginResponse {
    @SerializedName("errorCode")
    private var errorCode: String? = null

    @SerializedName("errorMessage")
    private var errorMessage: String? = null

    @SerializedName("user")
    @Expose
    private var user: User? = null

    fun getErrorCode(): String? {
        return errorCode
    }

    fun setErrorCode(errorCode: String) {
        this.errorCode = errorCode
    }

    fun getErrorMessage(): String? {
        return errorMessage
    }

    fun setErrorMessage(errorMessage: String) {
        this.errorMessage = errorMessage
    }

    fun getUser(): User? {
        return user
    }

    fun setUser(user: User) {
        this.user = user
    }

    inner class User {
        @SerializedName("userId")
        private var userId: Long = 0

        @SerializedName("userName")
        private var userName: String? = null

        fun getId(): Long {
            return userId
        }

        fun setId(userId: Long) {
            this.userId = userId
        }

        fun getUserName(): String? {
            return userName
        }

        fun setUserName(userName: String) {
            this.userName = userName
        }
    }
}