package com.ilyakrn.foodies.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ilyakrn.foodies.R

//теги njdfhf
@Preview
@Composable
fun ProductProperties(discount: Boolean = true, spicy: Boolean = true, vegetarian: Boolean = true){

    Column {
        //скидка
        if(discount) {
            Spacer(modifier = Modifier.height(12.dp))
            Icon(
                modifier = Modifier.size(44.dp),
                painter = painterResource(id = R.drawable.discount),
                contentDescription = "discount",
                tint = Color.Unspecified
            )
        }
        //острое
        if(spicy) {
            Spacer(modifier = Modifier.height(12.dp))
            Icon(
                modifier = Modifier.size(44.dp),
                painter = painterResource(id = R.drawable.spicy),
                contentDescription = "spicy",
                tint = Color.Unspecified
            )
        }
        //без мяса
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