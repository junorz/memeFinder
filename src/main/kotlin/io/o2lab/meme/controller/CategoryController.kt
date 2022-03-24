package io.o2lab.meme.controller

import io.o2lab.meme.domain.Category
import io.o2lab.meme.service.CategoryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CategoryController(
    val categoryService: CategoryService,
) {
    @GetMapping("/category/all")
    fun getAllCategories(): List<Category> {
        return categoryService.getAllCategories();
    }
}
