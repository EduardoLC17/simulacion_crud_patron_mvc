package com.example.srodenas.simulacioncrud.Views

import com.example.srodenas.simulacioncrud.Data.RepositoryClient

class Dialog {
    private var clientAdd: ((Int, String, String, String) -> Unit)? = null
    private var clientUpdate: ((Int, String, String, String) -> Unit)? = null
    private var clientDel: ((Int) -> Unit)? = null

    // el controller me pasa las tres lambdas
    fun setListeners(
        add: (Int, String, String, String) -> Unit,
        update: (Int, String, String, String) -> Unit,
        del: (Int) -> Unit
    ) {
        clientAdd = add
        clientUpdate = update
        clientDel = del
    }

    fun show(typeAction: Int, id: Int = -1) {
        when (typeAction) {
            0 -> clientAdd?.invoke(RepositoryClient.incrementPrimary(), "NUEVO CLIENTE", "NUEVOS APELLIDOS", "622222222")
            1 -> if (id != -1) clientUpdate?.invoke(id, "CAMBIADO", "APELLIDOS CAMBIADOS", "611111111")
            2 -> if (id != -1) clientDel?.invoke(id)
        }
    }
}