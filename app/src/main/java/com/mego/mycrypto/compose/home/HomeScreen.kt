package com.mego.mycrypto.compose.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mego.mycrypto.domain.Coin
import com.mego.mycrypto.ui.CoinsListViewModel
import org.koin.androidx.compose.koinViewModel
import java.math.RoundingMode

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CoinSection(modifier: Modifier = Modifier, coin: Coin) {
    Surface( modifier = modifier
        .fillMaxWidth()
        .padding(8.dp) , shape = MaterialTheme.shapes.medium, shadowElevation = 9.dp) {
        Card(modifier = modifier.fillMaxWidth().height(64.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(8.dp)) {
                // TODO add cache
                GlideImage(model = coin.image?.smallUrl ,
                    contentDescription = "",
                    modifier = Modifier.size(32.dp))
                Column() {
                    Text(text = coin.name, style = MaterialTheme.typography.labelLarge )
                    Text(text = coin.symbol,
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray)
                }
                Spacer(modifier = Modifier.weight(1.0f))
                Column(horizontalAlignment = Alignment.End) {
                    val price = coin.marketData?.currentPrice?.dollar?.toBigDecimal()?.setScale(3,RoundingMode.UP)?.toFloat()
                    Text(text = "$price$", style = MaterialTheme.typography.labelLarge)

                    val percentageChange = coin.marketData?.price_change_percentage_24h?.toBigDecimal()?.setScale(2,RoundingMode.HALF_UP)?.toFloat() ?: 0f

                    Text(text = if (percentageChange>0) "+$percentageChange%" else "$percentageChange%" ,
                        style = MaterialTheme.typography.labelSmall,
                        color = if (percentageChange >= 0) Color(0xFF3cc784) else Color.Red )
                }
            }
        }
    }
}

@Composable
fun CoinsList( coinsListViewModel: CoinsListViewModel = koinViewModel() ) {
    val coinsListState = coinsListViewModel.coinsList   //coinsListFlow.collectAsState()
    LazyColumn(verticalArrangement = Arrangement.spacedBy(3.dp)) {
        items(items = coinsListState,
            key=  {coin -> coin.id} ) {
                coin ->
            CoinSection(coin = coin)
        }
    }
}

@Composable
fun HomeScreen( ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AppBar()
        Text(text = "Coins List", style = MaterialTheme.typography.labelMedium)
        Spacer(modifier = Modifier.height(16.dp))
        CoinsList()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(coinsListViewModel: CoinsListViewModel = koinViewModel()) {
    var exp by remember { mutableStateOf(false) }
    var input by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    ExposedDropdownMenuBox(expanded = exp, onExpandedChange = { exp = !exp }) {
        OutlinedTextField(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 16.dp)
                .fillMaxWidth()
                .menuAnchor(),
            shape = MaterialTheme.shapes.extraLarge ,
            colors = TextFieldDefaults.colors(focusedContainerColor = Color.White, unfocusedContainerColor = Color.White),
            value = input ,
            onValueChange = {
                input=it
                coinsListViewModel.updateCoinsAutocompleteList(input) },
            placeholder = { Text(text = "Search for Crypto Currency") },
            leadingIcon = { Icon(imageVector = Icons.Default.Paid , contentDescription = "")} )

        val filteredList = coinsListViewModel.autocompleteFilteredList

        if ( filteredList.isNotEmpty() ) {
            ExposedDropdownMenu(expanded = exp,
                onDismissRequest = {
                    exp= false
                    coinsListViewModel.clearAutocompleteList()
                    input = ""})
            {
                filteredList.forEach{ coin ->
                    DropdownMenuItem(
                        onClick = {
                            coinsListViewModel.addToMyFavCoin(coin.id)
                            coinsListViewModel.clearAutocompleteList()
                            exp=false
                            input = ""
                            focusManager.clearFocus()
                        },
                        text = { Text(text = coin.name) }
                    )
                }
            }
        }
    }
}


/*
@Preview
@Composable
fun CoinListPreview() {
    MyCryptoTheme {
        HomeScreen()
    }
}
*/