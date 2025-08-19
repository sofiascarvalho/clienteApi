package br.senai.sp.jandira.clienteapp.cliente

import android.content.res.Configuration
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.clienteapp.cliente.cliente.componentes.BarraInferior
import br.senai.sp.jandira.clienteapp.cliente.cliente.componentes.BotaoFlutuante
import br.senai.sp.jandira.clienteapp.cliente.cliente.componentes.ClienteFrom
import br.senai.sp.jandira.clienteapp.cliente.cliente.componentes.Conteudo
import br.senai.sp.jandira.clienteapp.ui.theme.ClienteAppTheme

@Composable
fun ListaDeClientes(){

    var controleNavegacao = rememberNavController()

    Scaffold ( //scaffold cria a estrutura com "gavetas", onde vamos atribuir as funcoes
        topBar = {
            BarraDeTiTulo()
        },
        bottomBar = {
            BarraInferior(controleNavegacao)
        },
        floatingActionButton = {
            BotaoFlutuante(controleNavegacao)
        },
        content = { padding ->
            NavHost(navController = controleNavegacao,
                startDestination = "conteudo"){
                composable(route = "conteudo"){ Conteudo(padding)}
                composable(route = "cadastro") { ClienteFrom(padding, controleNavegacao) }
            }
        }
    )
}




@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ListaDeClientesPreview(){
    ClienteAppTheme {
        ListaDeClientes()
    }
}