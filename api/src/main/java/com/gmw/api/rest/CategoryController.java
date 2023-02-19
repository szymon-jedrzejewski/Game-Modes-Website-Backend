package com.gmw.api.rest;

import com.gmw.api.rest.activity.category.CreateCategoryActivity;
import com.gmw.api.rest.activity.category.DeleteCategoryActivity;
import com.gmw.api.rest.activity.category.FindAllCategoriesActivity;
import com.gmw.api.rest.activity.category.UpdateCategoryActivity;
import com.gmw.category.tos.ExistingCategoryTO;
import com.gmw.category.tos.NewCategoryTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @GetMapping("/findAll")
    public ResponseEntity<List<ExistingCategoryTO>> findCategories() {
        FindAllCategoriesActivity activity = new FindAllCategoriesActivity();
        return activity.execute();
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createCategory(@RequestBody NewCategoryTO category) {
        CreateCategoryActivity activity = new CreateCategoryActivity(category);
        return activity.execute();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateCategory(@RequestBody ExistingCategoryTO existingCategoryTO) {
        UpdateCategoryActivity activity = new UpdateCategoryActivity(existingCategoryTO);
        return activity.execute();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        DeleteCategoryActivity activity = new DeleteCategoryActivity(id);
        return activity.execute();
    }
}
