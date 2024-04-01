package com.ilyakrn.foodies.ui.components

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.ilyakrn.foodies.R

@Preview
@Composable
fun ProductProperties(discount: Boolean = true, spicy: Boolean = true, vegetarian: Boolean = true){

    Column {
        if(discount) {
            Spacer(modifier = Modifier.height(12.dp))
            Icon(
                modifier = Modifier.size(44.dp),
                painter = painterResource(id = R.drawable.discount),
                contentDescription = "discount",
                tint = Color.Unspecified
            )
        }
        if(spicy) {
            Spacer(modifier = Modifier.height(12.dp))
            Icon(
                modifier = Modifier.size(44.dp),
                painter = painterResource(id = R.drawable.spicy),
                contentDescription = "spicy",
                tint = Color.Unspecified
            )
        }
        if(vegetarian) {
            Spacer(modifier = Modifier.height(12.dp))
            Icon(
                modifier = Modifier.size(44.dp),
                painter = painterResource(id = R.drawable.vegetarian),
                contentDescription = "vegetarian",
                tint = Color.Unspecified
            )
        }
    }

}