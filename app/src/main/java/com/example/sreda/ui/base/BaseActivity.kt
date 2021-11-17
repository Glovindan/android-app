package com.example.sreda.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

abstract class BaseActivity<VM : ViewModel>(kClass: KClass<VM>) : AppCompatActivity() {

    protected val viewModel: VM by lazy {
        ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
            .get(kClass.java)
    }

    protected fun <T> LiveData<T>.observe(block: (T) -> Unit) {
        observe(this@BaseActivity, { block(it) })
    }
}
