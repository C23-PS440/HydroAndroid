package com.capstone.hydroandroid.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.capstone.hydroandroid.R

class CustomButtonRegister : AppCompatButton {

    private lateinit var enabledBackground: Drawable
    private lateinit var disabledBackground: Drawable
    private var txtColor: Int = 0

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        background = if(isEnabled) enabledBackground else disabledBackground

        setTextColor(txtColor)
        textSize = 12f
        gravity = Gravity.CENTER
        text = if(isEnabled) "Register" else "Isi Dulu"
    }

    private fun init() {
        txtColor = ContextCompat.getColor(context, android.R.color.background_light)
        enabledBackground = ContextCompat.getDrawable(context, R.drawable.bg_button) as Drawable
        disabledBackground = ContextCompat.getDrawable(context, R.drawable.bg_button_disable) as Drawable

//        val cornerRadius = 20
//        val backgroundDrawable = background.mutate() as GradientDrawable
//        backgroundDrawable.cornerRadius = cornerRadius.toFloat()
//        background = backgroundDrawable
    }

//    private fun init() {
//        txtColor = ContextCompat.getColor(context, android.R.color.background_light)
//        enabledBackground = createCustomBackgroundDrawable(R.drawable.bg_button)
//        disabledBackground = createCustomBackgroundDrawable(R.drawable.bg_button_disable)
//
//        background = if (isEnabled) enabledBackground else disabledBackground
//    }
//
//    private fun createCustomBackgroundDrawable(resId: Int): Drawable {
//        val cornerRadius = 20 // Set the desired corner radius in pixels
//        val backgroundDrawable = ContextCompat.getDrawable(context, resId)?.mutate() as? GradientDrawable
//        backgroundDrawable?.cornerRadius = cornerRadius.toFloat()
//        return backgroundDrawable ?: ColorDrawable(Color.TRANSPARENT)
//    }
}