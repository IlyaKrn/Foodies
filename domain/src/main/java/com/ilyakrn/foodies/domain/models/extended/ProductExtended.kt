package com.ilyakrn.foodies.domain.models.extended

import com.ilyakrn.foodies.domain.models.core.Tag

data class ProductExtended(
    val id: Long,
    val categoryId: Long,
    val name: String,
    val description: String,
    val image: String,
    val priceCurrent: Int,
    val priceOld: Int,
    val measure: Int,
    val measureUnit: String,
    val energyPer100Grams: Double,
    val proteinsPer100Grams: Double,
    val fatsPer100Grams: Double,
    val carbohydratesPer100Grams: Double,
    val tags: List<Tag>
)