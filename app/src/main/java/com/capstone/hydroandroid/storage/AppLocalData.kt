package com.capstone.hydroandroid.storage

class AppLocalData (private val storage: Storage) {
    fun setUserLoggedIn(user: UserLoggedIn) {
        storage.apply {
            setString(KEY_ACCESS_TOKEN, user.accessToken)
            setString(KEY_EMAIL, user.email)
            setString(KEY_USER_ID, user.username)
        }
    }

    fun dropUserLoggedIn() {
        storage.apply {
            remove(KEY_ACCESS_TOKEN)
            remove(KEY_EMAIL)
            remove(KEY_USER_ID)
        }
    }

    val isUserHasLoggedIn: Boolean
        get() = !storage.getString(KEY_EMAIL).isNullOrEmpty()

    val getUsername: String?
        get() = storage.getString(KEY_USER_ID)

    val getKeyAccessToken: String?
        get() = storage.getString(KEY_ACCESS_TOKEN)

    companion object {
        const val KEY_ACCESS_TOKEN = "access_token"
        const val KEY_EMAIL = "email"
        const val KEY_USER_ID = "user_id"
    }
}