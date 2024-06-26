package com.ilyakrn.foodies.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ilyakrn.foodies.domain.models.core.Category

//элемент списка категорий
@Composable
fun CategoryCard(category: Category, isSelected: Boolean = false, onClick: () -> Unit = {}){
    Box{
        //если категория выделена
        AnimatedVisibility(
            visible = isSelected,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.shapes.small
                    )
                    .height(60.dp)
                    .padding(16.dp, 0.dp)
                    .clickable {
                        if(isSelected)
                            onClick()
                    }
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = category.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
        //если категория не выделена
        AnimatedVisibility(
            visible = !isSelected,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .height(60.dp)
                    .padding(16.dp, 0.dp)
                    .clickable {
                        if(!isSelected)
                            onClick()
                    }
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = category.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}