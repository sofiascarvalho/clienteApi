package br.senai.sp.jandira.clienteapp.cliente.cliente.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.clienteapp.model.Cliente
import br.senai.sp.jandira.clienteapp.service.ClienteService
import br.senai.sp.jandira.clienteapp.service.Conexao
import br.senai.sp.jandira.clienteapp.ui.theme.ClienteAppTheme
import kotlinx.coroutines.Dispatchers
import retrofit2.await

@Composable
fun Conteudo (paddingValues: PaddingValues) {

    val clienteApi = Conexao().getClienteService()

    var clientes by remember {
        mutableStateOf(listOf<Cliente>())
    }



    LaunchedEffect(Dispatchers.IO) { //faz a chamada para a api
        clientes = clienteApi.exibirTodos().await()
    }

    Column (
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ){
        Spacer(modifier = Modifier.height(5.dp))
        Row {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Person"
            )
            Text(
                text = "Lista de Clientes"
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
        Spacer(modifier = Modifier.height(5.dp))
        LazyColumn (
            contentPadding = PaddingValues(bottom = 80.dp)
        ){ // a cada interacao da lazycolumn, um novo card é gerado
            items(clientes){ cliente ->
                CardCliente(cliente, clienteApi)
            }
        }
    }
}

@Composable
private fun CardCliente(
    cliente: Cliente,
    clienteApi: ClienteService
) {
    // variável que determina se a messagem deve aparecer
    var mostrarMensagemDelete by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                bottom = 8.dp
            )
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = cliente.nome,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = cliente.email,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            IconButton(
                onClick = {
                    mostrarMensagemDelete = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
        if (mostrarMensagemDelete) {
            AlertDialog(
                onDismissRequest = {
                    mostrarMensagemDelete = false
                },
                title = {
                    Text(text = "Alerta!")
                },
                text = {
                    Text(text = "Tem certeza que deseja excluir o cliente\n\n${cliente.nome}?")
                },

            )
        }
    }
}

@Preview
@Composable
private fun ConteudoPreview(){
    ClienteAppTheme {
        Conteudo(PaddingValues(16.dp))
    }
}