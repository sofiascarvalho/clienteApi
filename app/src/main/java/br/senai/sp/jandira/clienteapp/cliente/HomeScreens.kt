package br.senai.sp.jandira.clienteapp.cliente

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.clienteapp.R
import br.senai.sp.jandira.clienteapp.ui.theme.ClienteAppTheme

@Composable
fun HomeScreens(modifier: Modifier = Modifier){
    Scaffold(
        topBar = {
            BarraDeTiTulo()
        },
        bottomBar = {
            BarraDeNavegacao()
        },
        floatingActionButton = {
            BotoaoFlutuante()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.background
                )
        ) {
            Row {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "Icone",
                    tint = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Lista de clientes"
                )
            }
            LazyColumn {
                items(10){
                    ClienteCard()
                }
            }
        }
    }
}


@Composable
fun ClienteCard (modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(
                start = 8.dp,
                end = 8.dp,
                bottom = 4.dp
            ),
        colors = CardDefaults
            .cardColors(
                contentColor = MaterialTheme.colorScheme.primaryContainer
            )
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
                    text = "nome",
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = "email",
                    color = MaterialTheme.colorScheme.onPrimaryContainer)
            }
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "texto",
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Preview
@Composable
private fun ClienteCardPreview() {
    ClienteCard()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraDeTiTulo (modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        colors = TopAppBarDefaults
            .topAppBarColors(
                containerColor = MaterialTheme
                    .colorScheme.primary
            ),
        title = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = "Rebeka Marcelino do Prado",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        text = "rebeka.marcelino.prado@gmail.com",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Card(
                    modifier = Modifier
                        .size(60.dp),
                    shape = CircleShape
                ) {
                    Image(
                        painter = painterResource(R.drawable.perfilbeka),
                        contentDescription = "foto perfil"
                    )
                }

            }
        }
    )
}

//@Preview
@Composable
private fun BarraDeTiTuloPreview() {
    BarraDeTiTulo()
}

@Composable
fun BarraDeNavegacao (modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        NavigationBarItem(
            selected = false,
            onClick = {},
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
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        )
    }
}

//@Preview
@Composable
private fun BarraDeNavegacaoPreview() {
    BarraDeNavegacao()
}

@Composable
fun BotoaoFlutuante (modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = {},
        containerColor = MaterialTheme.colorScheme.tertiary
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Botão adicionar",
            tint = MaterialTheme.colorScheme.onTertiary
        )
    }
}

//@Preview
@Composable
private fun BotoaoFlutuantePreview() {
    BotoaoFlutuante()
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreensPreview(){
    ClienteAppTheme {
        HomeScreens()
    }
}