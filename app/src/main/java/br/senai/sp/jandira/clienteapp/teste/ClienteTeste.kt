package br.senai.sp.jandira.clienteapp.teste

import br.senai.sp.jandira.clienteapp.model.Cliente
import br.senai.sp.jandira.clienteapp.service.Conexao

fun main(){
    val c1 = Cliente(
        nome = "Sofia",
        email = "sofia@gmail.com"
    )

    val retrofit = Conexao().getClienteService()
    val cliente = retrofit.gravar(c1)
}