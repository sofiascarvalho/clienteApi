package br.senai.sp.jandira.clienteapp.cliente.cliente.componentes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import br.senai.sp.jandira.clienteapp.ui.theme.ClienteAppTheme


@Composable
fun BarraInferior(controleNavegacao: NavHostController?) {

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        NavigationBarItem(
            selected = false,
            onClick = {
                controleNavegacao!!.navigate("conteudo")
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = {
                controleNavegacao!!.navigate("cadastro")
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Novo cliente",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        )
    }
}

@Preview
@Composable
private fun BarraInferiorPreview(){
    ClienteAppTheme {
        BarraInferior(null)
    }
}