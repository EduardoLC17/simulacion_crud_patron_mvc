package com.example.srodenas.simulacioncrud.Views

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.srodenas.simulacioncrud.Logic.Controller
import com.example.srodenas.simulacioncrud.R

class MainActivity : AppCompatActivity() {

    private lateinit var myText: TextView
    private lateinit var myDialog: Dialog
    private lateinit var controller: Controller

    companion object {
        const val TAG = "---SALIDA---"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "Esto es un ejemplo de log")

        myText = findViewById(R.id.textView)
        myDialog = Dialog()                          // 1. creo el dialogo
        controller = Controller(this, myDialog)      // 2. creo el controller con this y el dialogo
        controller.start()                           // 3. le cedo el control
    }

    // lo llama el controller para actualizar la vista
    fun updateView(msg: String, data: String) {
        myText.text = msg + "\n\n" + data
    }
}