package com.example.datadeliverationdemo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            Intent(this,AnotherActivity::class.java).apply {
                putExtra("String","Lin")
                putExtra("Int",0)
                putExtra("Person",Person("Lin",32))
                startActivity(this)
            }

            //use singleton
            SingletonData.setData("Singleton")

            //use Application class
            val myApp = applicationContext as MyApp
            myApp.globalData = "Application Class"

            //use SharedPreferences
            val sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE)
            sharedPreferences.edit().apply {
                putString("key_string","use SharedPreferences")
                apply()
            }
            //use Room

            //use DataStore
            myApp.dataStore.apply {
                CoroutineScope(Dispatchers.IO).launch {
                    edit {
                            settings -> settings[stringPreferencesKey("data_store")] = "data store"
                    }
                }
            }

        }
    }
}