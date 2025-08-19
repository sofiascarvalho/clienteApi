package br.senai.sp.jandira.clienteapp.cliente.cliente.componentes

import android.content.res.Configuration
import android.util.Patterns
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.clienteapp.model.Cliente
import br.senai.sp.jandira.clienteapp.service.Conexao
import br.senai.sp.jandira.clienteapp.ui.theme.ClienteAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.await

@Composable
fun ClienteFrom(padding: PaddingValues, controleNavegacao: NavHostController?) {

    var nomeCliente by remember { mutableStateOf("") }
    var emailCliente by remember { mutableStateOf("") }
    var isNomeError by remember { mutableStateOf(false) }
    var isEmailError by remember { mutableStateOf(false) }

    // variável que determina se a messagem deve aparecer
    var mostrarMensagemSucesso by remember { mutableStateOf(false) }

    fun validar(): Boolean{
        isNomeError = nomeCliente.length<3
        isEmailError = !Patterns.EMAIL_ADDRESS.matcher(emailCliente).matches()
        return !isEmailError && !isEmailError
    }

    //CRIAR INSTANCIA DA CONEXAO COM A API
    val clienteApi = Conexao().getClienteService()

    Surface (
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ){
            Row(
                modifier= Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "person"
                )
                Text(
                    text = "Novo Cliente",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = nomeCliente,
                onValueChange = {nomeCliente = it},
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                label = {
                    Text(text = "Nome do Cliente")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Next
                )
            )
            OutlinedTextField(
                value = emailCliente,
                onValueChange = {emailCliente = it},
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                label = {
                    Text(text = "Email do Cliente")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                )
            )
            Button(
                onClick = {
                    if (validar()){
                        val cliente = Cliente(
                            id = null,
                            nome = nomeCliente,
                            email = emailCliente
                        )
                        GlobalScope.launch ( Dispatchers.IO ){
                            val clienteNovo = clienteApi
                                .gravar(cliente)
                                .await()
                            mostrarMensagemSucesso = true
                        }
                    }else{
                        println("***************** Dados incorretos")
                    }
                },
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            ) {
                Text(text = "Gravar cliente")
            }
        }
        if (mostrarMensagemSucesso){
            AlertDialog(
                onDismissRequest = {
                    mostrarMensagemSucesso = false
                    nomeCliente=""
                    emailCliente=""
                },
                title = {
                    Text(text = "Sucesso")
                        },
                text = {
                    Text(text = "Cliente $nomeCliente gravado com sucesso!\nDeseja cadastrar outro cliente?")
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            nomeCliente=""
                            emailCliente=""
                            mostrarMensagemSucesso=false
                        }
                    ) {
                        Text(text = "Sim")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            controleNavegacao!!.navigate("conteudo")
                        }
                    ) {
                        Text(text = "Não")
                    }
                }
            )
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun ClienteFormPreview() {
    ClienteAppTheme {
        ClienteFrom(PaddingValues(0.dp), null)
    }
}