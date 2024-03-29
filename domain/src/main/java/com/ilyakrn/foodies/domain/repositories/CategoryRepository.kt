package com.ilyakrn.foodies.domain.repositories

import com.ilyakrn.foodies.domain.models.core.Category

interface CategoryRepository {
    fun getCategoryList(): List<Category>;
}