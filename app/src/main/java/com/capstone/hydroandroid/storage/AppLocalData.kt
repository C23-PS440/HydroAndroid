package com.capstone.hydroandroid.storage

class AppLocalData (private val storage: Storage) {
    fun setUserLoggedIn(user: UserLoggedIn) {
        storage.apply {
            setString(KEY_ACCESS_TOKEN, user.token)
            setString(KEY_NAME, user.name)
            setString(KEY_USER_ID, user.userId)
            setString(KEY_EMAIL, user.email)
        }
    }

    fun dropUserLoggedIn() {
        storage.apply {
            remove(KEY_ACCESS_TOKEN)
            remove(KEY_NAME)
            remove(KEY_USER_ID)
            remove(KEY_EMAIL)
        }
    }

    val isUserHasLoggedIn: Boolean
        get() = !storage.getString(KEY_ACCESS_TOKEN).isNullOrEmpty()

    val getUsername: String?
        get() = storage.getString(KEY_NAME)

    val getKeyAccessToken: String?
        get() = storage.getString(KEY_ACCESS_TOKEN)

    val getEmail: String?
        get() = storage.getString(KEY_EMAIL)

    companion object {
        const val KEY_USER_ID = "user_id"
        const val KEY_NAME = "name"
        const val KEY_ACCESS_TOKEN = "access_token"
        const val KEY_EMAIL = "email"
    }
}