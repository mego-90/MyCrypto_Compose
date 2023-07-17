package com.mego.mycrypto.compose.rewards

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mego.mycrypto.ui.theme.*

data class MyBannerData (
    val header:String,
    val mainText:String,
    val btnText:String,
    val color:Color,
    val toastText:String)

val bannersList = listOf<MyBannerData>(
    MyBannerData("Beginner Guide", "Learn how to get started", "Invest Today", MyDarkBlue, "Thank You"),
    MyBannerData("Welcome Mohammad,", "Make your first Investment today", "Invest Today", MyBlueLight, "Thank You"),
    MyBannerData("Refer and Earn", "Refer your Friend \n and Win Cryptocoins ", "Refer Now", MyOrange, "Thank You"),
    MyBannerData("Rewards", "Like, Share \n & get free coupons", "Share Now", MyPurple, "Thank You"),
    MyBannerData("Rewards", "Spin Wheel \n & Win Free Tokens!", "Get Tokens", MyPink, "Thank You")
)
@Composable
fun GeneralBanner(bannerData: MyBannerData) {
    val context= LocalContext.current

    Surface(
        color = bannerData.color,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp)
            .fillMaxWidth()) {

        Column() {
            Text(
                text = bannerData.header,
                style = MaterialTheme.typography.labelMedium, color = Color.White,
                modifier = Modifier.padding(start = 16.dp,top=16.dp, bottom = 8.dp))

            Text(
                text = bannerData.mainText,
                style = MaterialTheme.typography.headlineSmall, color = Color.White,
                modifier = Modifier.padding(start = 16.dp))

            Button(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(start = 16.dp,top = 24.dp, bottom = 24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White) ,
                onClick = { Toast.makeText(context, bannerData.toastText, Toast.LENGTH_LONG).show() }) {
                    Text(text = bannerData.btnText, color = bannerData.color)
            }

        }
    }
}

@Composable
fun RewardsScreen() {
    LazyColumn() {
        items(bannersList) { banner ->
            GeneralBanner(bannerData = banner)
        }
    }
}

@Preview
@Composable
fun RewardsScreenPreview() {
    MyCryptoTheme() {
        Surface {
            RewardsScreen()
        }

    }
}