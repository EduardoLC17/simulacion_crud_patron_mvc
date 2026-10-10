package com.example.srodenas.simulacioncrud.Logic

import android.util.Log
import android.widget.ImageView
import com.example.srodenas.simulacioncrud.Data.RepositoryClient
import com.example.srodenas.simulacioncrud.R
import com.example.srodenas.simulacioncrud.Views.Dialog
import com.example.srodenas.simulacioncrud.Views.MainActivity
import kotlin.random.Random

// version con lambdas: el controller ya no implementa la interfaz
class Controller(private val activity: MainActivity, private val dialog: Dialog) {

    private var myListClient: MutableList<Client>

    init {
        // cargo los datos del repositorio
        myListClient = RepositoryClient.arrayClient.toMutableList()
    }

    // aqui empieza todo, lo llama el activity
    fun start() {
        // las lambdas ahora estan aqui, en el controller
        dialog.setListeners(
            { id, name, apellidos, telefono -> clientAdd(id, name, apellidos, telefono) },
            { id, name, apellidos, telefono -> clientUpdate(id, name, apellidos, telefono) },
            { id -> clientDel(id) }
        )

        val btnAdd = activity.findViewById<ImageView>(R.id.myButtonAdd)
        val btnEdit = activity.findViewById<ImageView>(R.id.myButtomEdit)
        val btnDel = activity.findViewById<ImageView>(R.id.myButtomDel)

        btnAdd.setOnClickListener { dialog.show(0) }
        btnEdit.setOnClickListener { dialog.show(1, devIdRandom()) }
        btnDel.setOnClickListener { dialog.show(2, devIdRandom()) }

        refreshView("Lista inicial cargada")
    }

    private fun clientAdd(id: Int, name: String, apellidos: String, telefono: String) {
        myListClient.add(Client(id, name, apellidos, telefono))
        refreshView("El cliente con id = $id, ha sido insertado correctamente")
    }

    private fun clientDel(id: Int) {
        if (myListClient.removeAll { it.id == id })
            refreshView("El cliente con id = $id, ha sido eliminado correctamente")
        else
            refreshView("El cliente con id = $id, no ha sido encontrado para eliminar")
    }

    private fun clientUpdate(id: Int, name: String, apellidos: String, telefono: String) {
        val cliente = myListClient.find { it.id == id }
        if (cliente != null) {
            cliente.name = name
            cliente.apellidos = apellidos
            cliente.telefono = telefono
            refreshView("El cliente con id = $id, ha sido actualizado correctamente")
        } else {
            refreshView("El cliente con id = $id, no ha sido encontrado para actualizar")
        }
    }

    // saca el mensaje y la lista por el Log, y actualiza la vista del activity
    private fun refreshView(msg: String) {
        Log.d(MainActivity.TAG, msg)
        Log.d(MainActivity.TAG, myListClient.toString())

        val texto = myListClient.joinToString("\n") {
            "${it.id} - ${it.name} ${it.apellidos} - ${it.telefono}"
        }
        activity.updateView(msg, texto)
    }

    // id de un cliente al azar, -1 si no hay ninguno
    private fun devIdRandom(): Int {
        if (myListClient.isEmpty()) return -1
        return myListClient[Random.nextInt(myListClient.size)].id
    }
}