package io.o2lab.meme.repository

import io.o2lab.meme.domain.Category
import org.springframework.data.repository.CrudRepository

interface CategoryRepository : CrudRepository<Category, Int> {}
