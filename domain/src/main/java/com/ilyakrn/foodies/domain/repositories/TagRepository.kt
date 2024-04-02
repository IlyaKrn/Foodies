package com.ilyakrn.foodies.domain.repositories

import com.ilyakrn.foodies.domain.models.core.Product
import com.ilyakrn.foodies.domain.models.core.Tag

//репозиторий тегов
interface TagRepository {
    fun getTagList(listener: (List<Tag>) -> Unit)
}