package com.gmw.api.rest;

import com.gmw.api.rest.activity.category.CreateCategoryActivity;
import com.gmw.api.rest.activity.category.DeleteCategoryActivity;
import com.gmw.api.rest.activity.category.FindAllCategoriesActivity;
import com.gmw.api.rest.activity.category.UpdateCategoryActivity;
import com.gmw.category.tos.ExistingCategoryTO;
import com.gmw.category.tos.NewCategoryTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class CategoryController {

    @GetMapping("/findAll")
    public ResponseEntity<List<ExistingCategoryTO>> findCategories() {
        FindAllCategoriesActivity activity = new FindAllCategoriesActivity();
        return activity.execute();
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createCategory(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                               @RequestBody NewCategoryTO category) {
        CreateCategoryActivity activity = new CreateCategoryActivity(category, token);
        return activity.execute();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateCategory(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                               @RequestBody ExistingCategoryTO existingCategoryTO) {
        UpdateCategoryActivity activity = new UpdateCategoryActivity(existingCategoryTO, token);
        return activity.execute();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                               @PathVariable Long id) {
        DeleteCategoryActivity activity = new DeleteCategoryActivity(id, token);
        return activity.execute();
    }
}
