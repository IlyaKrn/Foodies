package com.ilyakrn.foodies.domain.repositories

import com.ilyakrn.foodies.domain.models.core.Tag

interface TagRepository {
    fun getTagList(): List<Tag>;
}