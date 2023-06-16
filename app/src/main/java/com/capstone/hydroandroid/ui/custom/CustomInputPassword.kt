package com.capstone.hydroandroid.ui.custom

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class CustomInputPassword : AppCompatEditText {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(text: CharSequence, p1: Int, p2: Int, p3: Int) {
                error =
                    if (text.isNotEmpty() && text.toString().trim().length < MIN_PASSWORD)
                        "Password harus terdiri dari setidaknya 6 karakter"
                    else
                        null
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    companion object {
        const val MIN_PASSWORD = 6
    }
}