package com.ilyakrn.foodies.domain.repositories

import com.ilyakrn.foodies.domain.models.core.Category

//репозиторий категорий
interface CategoryRepository {
    fun getCategoryList(listener: (List<Category>) -> Unit)
}