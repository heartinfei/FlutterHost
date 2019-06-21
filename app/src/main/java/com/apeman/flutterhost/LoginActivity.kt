package com.apeman.flutterhost

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.FrameLayout
import io.flutter.facade.Flutter
import io.flutter.plugin.common.MethodChannel
import io.flutter.view.FlutterView

/**
 * Flutter Host
 * @author Rango on 2019-06-21 wangqiang@smzdm.com
 */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //strategy1()
        strategy2()
    }

    private fun strategy1() {
        setContentView(R.layout.activity_login)
        val flutterView = supportFragmentManager.findFragmentById(R.id.flutterFragment)!!.view as FlutterView
        initMethodChannel(flutterView)
    }

    private fun strategy2() {
        val matchParent = FrameLayout.LayoutParams.MATCH_PARENT
        val flutterView = Flutter.createView(this, lifecycle, "login")
        addContentView(flutterView, FrameLayout.LayoutParams(matchParent, matchParent))
        initMethodChannel(flutterView)
    }

    private fun initMethodChannel(flutterView: FlutterView) {
        MethodChannel(flutterView, "flutter/module").setMethodCallHandler { call, result ->
            val args = call.arguments as ArrayList<String>
            Log.i("flutter", args.toString())
            onLogined(args[0], args[1])
        }
    }

    private fun onLogined(name: String, psw: String) {
        Log.i("flutter", "UserName:$name,Password:$psw")
        MainActivity.launch(this, name, psw)
    }
}
