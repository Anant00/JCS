package com.app.jcs.view.activities.auth

class AuthResource<T>(val status: AuthStatus, val data: T?, val message: String?) {

    enum class AuthStatus {
        AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED
    }

    companion object {

        fun <T> authenticated(message: String?, data: T?): AuthResource<T> {
            return AuthResource(
                AuthStatus.AUTHENTICATED,
                data,
                message
            )
        }

        fun <T> error(msg: String?, data: T?): AuthResource<T> {
            return AuthResource(
                AuthStatus.ERROR,
                data,
                msg
            )
        }

        fun <T> loading(data: T?): AuthResource<T> {
            return AuthResource(
                AuthStatus.LOADING,
                data,
                null
            )
        }

        fun <T> logout(data: T?): AuthResource<T> {
            return AuthResource<T>(
                AuthStatus.NOT_AUTHENTICATED,
                data,
                null
            )
        }
    }
}
