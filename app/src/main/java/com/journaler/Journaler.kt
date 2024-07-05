package com.journaler


import android.app.Application
import android.content.Context
import android.util.Log


class Journaler : Application() {

    companion object {
        val tag = "Journaler"
        var ctx: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
        Log.v(tag, "[APP ON CREATE]")
    }

}