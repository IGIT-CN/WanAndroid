package com.zhuzichu.android.wan

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main_new.*

class ActivityMainNew : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_new)
        val navController = Navigation.findNavController(this, R.id.container)
        NavigationUI.setupWithNavController(bottom, navController)
    }

}