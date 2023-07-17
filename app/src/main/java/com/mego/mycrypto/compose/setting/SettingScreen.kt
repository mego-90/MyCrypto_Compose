package com.mego.mycrypto.compose.setting

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Snackbar
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mego.mycrypto.R
import com.mego.mycrypto.ui.theme.MyBlueLight
import com.mego.mycrypto.ui.theme.MyCryptoTheme

data class SettingInfo(val text:String, val icon:ImageVector, val route:String)

val settingsList = listOf<SettingInfo>(
    SettingInfo("History", Icons.Outlined.History, "Setting/History"),
    SettingInfo("Bank Details", Icons.Outlined.CommentBank, "Setting/Bank_Details"),
    SettingInfo("Notifications", Icons.Outlined.Notifications, "Setting/Notifications"),
    SettingInfo("Security", Icons.Outlined.Security, "Setting/Security"),
    SettingInfo("Help And Support", Icons.Outlined.Help, "Setting/Help"),
    SettingInfo("Terms And Conditions", Icons.Outlined.DocumentScanner, "Setting/Terms")
)

@Composable
fun SettingItem(settingInfo: SettingInfo) {
    MyCryptoTheme() {
        Surface() {
            val context = LocalContext.current
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        Toast.makeText(context, "Navigate to ${settingInfo.route}", Toast.LENGTH_LONG).show()
                    }
            ) {
                Icon(
                    imageVector = settingInfo.icon,
                    contentDescription = "",
                    modifier = Modifier
                        .size(48.dp)
                        .padding(8.dp),
                    tint = MyBlueLight )
                Text(text = settingInfo.text, style = MaterialTheme.typography.labelLarge)
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Filled.ChevronRight ,// Icons.Default.ArrowForward,
                    contentDescription = "",
                    modifier = Modifier
                        .size(32.dp)
                        .padding(end = 8.dp),
                    tint = Color.Gray
                )
            }
        }
    }
}

@Composable
fun SettingList() {
    LazyColumn() {
        itemsIndexed( settingsList) { index,item ->
            SettingItem(settingInfo = item)
            if (index < settingsList.lastIndex)
                Divider(thickness = 1.dp,
                    color = Color.Gray.copy(alpha = 0.5f),
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp) )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ContactDetailsBanner(modifier: Modifier = Modifier) {
    Surface(shape = MaterialTheme.shapes.large, color = MyBlueLight, modifier = modifier.padding(16.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxWidth()) {
            GlideImage(model = R.drawable.pic,
                contentDescription = "",
                modifier = modifier
                    .padding(16.dp)
                    .size(96.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            //name
            Text(text = "Mohammad Mansour",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                modifier = modifier.padding(4.dp))
            //email
            Text(text = "mego.it@gmail.com",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                modifier = modifier.padding(4.dp))
            //phone
            Text(text = "+91 999 9999-999",
                style = MaterialTheme.typography.labelMedium,
                color = Color.White,
                modifier = modifier.padding(4.dp))
        }
    }
}

@Composable
fun SettingScreen() {
    Surface() {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ContactDetailsBanner()
            Spacer(modifier = Modifier.height(8.dp))
            SettingList()
        }
    }
}

@Preview
@Composable
fun SettingItemPreview() {
    val settingInfo = SettingInfo("Call Details", Icons.Default.Call, "")
    SettingItem(settingInfo = settingInfo)
}

@Preview
@Composable
fun SettingListPreview() {
    SettingList()
}

@Preview
@Composable
fun SettingScreenPreview() {
   SettingScreen()
}
