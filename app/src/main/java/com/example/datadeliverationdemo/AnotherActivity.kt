package com.example.datadeliverationdemo

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.math.log

class AnotherActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another)

        val stringExtra = intent.getStringExtra("String")
        val intExtra = intent.getIntExtra("Int", -1)
        val serializableExtra = intent.getSerializableExtra("Person", Person::class.java)
        Log.i("LinLi", "string: $stringExtra int: $intExtra " +
                "person name: ${serializableExtra!!.name} " +
                "person age: ${serializableExtra.age}");

        val data = SingletonData.getData<String>()
        Log.i("LinLi", "data: $data");

        val myApp = applicationContext as MyApp
        val globalData = myApp.globalData
        Log.i("LinLi", "globalData: $globalData");

        val sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE)
        val string_prefs = sharedPreferences.getString("key_string", "")
        Log.i("LinLi", "sharedPreferences: $string_prefs");


        val dataStoreValueFlow =  myApp.dataStore.data.map {
            it[stringPreferencesKey("data_store")] ?: "default_value"
        }

        CoroutineScope(IO).launch {
            dataStoreValueFlow.collect { value ->
                Log.i("LinLi", "DataStoreValue: $value")
            }
        }
    }
}