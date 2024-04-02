package com.ilyakrn.foodies.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ilyakrn.foodies.R
import com.ilyakrn.foodies.domain.models.core.Category
import com.ilyakrn.foodies.domain.models.extended.SelectedProductExtended
import com.ilyakrn.foodies.domain.usecases.GetBasketPriceUseCase
import com.ilyakrn.foodies.domain.usecases.GetProductListByCategoryUseCase

//панель со списком категорий
@Composable
fun CategoryBar(
    categories: List<Category>,
    selectedId: Long,
    onSelect: (Long) -> Unit = {}
) {
    Row{
        Spacer(modifier = Modifier.width(16.dp))
        LazyRow(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
        ){
            items(items = categories, itemContent = { item ->
                CategoryCard(
                    category = item,
                    isSelected = selectedId == item.id,
                    onClick = {
                        onSelect(item.id)
                    })
            })
        }
    }
}