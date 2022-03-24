package io.o2lab.meme.service

import io.o2lab.meme.domain.Category
import io.o2lab.meme.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(
    val categoryRepository: CategoryRepository,
) {
    fun getAllCategories(): List<Category> {
        return categoryRepository.findAll().toList()
    }
}
