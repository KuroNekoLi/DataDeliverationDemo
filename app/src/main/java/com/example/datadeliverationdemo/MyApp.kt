package com.example.datadeliverationdemo

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

class MyApp : Application() {
    var globalData: String? = null
    val dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")
}