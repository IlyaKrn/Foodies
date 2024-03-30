package com.ilyakrn.foodies.domain.usecases

import com.ilyakrn.foodies.domain.models.core.Category
import com.ilyakrn.foodies.domain.models.core.Product
import com.ilyakrn.foodies.domain.models.core.Tag
import com.ilyakrn.foodies.domain.models.extended.ProductExtended
import com.ilyakrn.foodies.domain.repositories.CategoryRepository
import com.ilyakrn.foodies.domain.repositories.ProductRepository
import com.ilyakrn.foodies.domain.repositories.TagRepository

class GetCategoryListUseCase(private val categoryRepository: CategoryRepository) {

    fun invoke(): List<Category>{
        return categoryRepository.getCategoryList()
    }

}