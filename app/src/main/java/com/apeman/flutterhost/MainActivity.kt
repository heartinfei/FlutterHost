package com.apeman.flutterhost

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

/**
 *
 * @author Rango on 2019-06-21 wangqiang@smzdm.com
 */
class MainActivity : AppCompatActivity() {

    private val tv by lazy {
        findViewById<TextView>(R.id.tv)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val name = intent.getStringExtra("name")
        val psw = intent.getStringExtra("psw")
        tv.text = "name:$name,psw:$psw"
    }

    companion object {
        fun launch(ctx: Context, name: String, psw: String) {
            Intent(ctx, MainActivity::class.java).apply {
                putExtra("name", name)
                putExtra("psw", psw)
                ctx.startActivity(this)
            }
        }
    }
}
