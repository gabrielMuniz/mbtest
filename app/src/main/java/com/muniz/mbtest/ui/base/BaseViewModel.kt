package com.muniz.mbtest.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    protected val context
        get() = getApplication<Application>()

}