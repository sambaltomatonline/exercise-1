package id.springboot.example.example1.controller;

import id.springboot.example.example1.model.Category;
import id.springboot.example.example1.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/category-management")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    private void init(){

        log.info("init called");

        Category kategori1 = new Category("Fiksi");
        Category kategori2 = new Category("Non Fiksi");

        categoryRepository.save(kategori1);
        categoryRepository.save(kategori2);

    }

    @GetMapping(path = "/categories")
    public ResponseEntity<List<Category>> getAllCategories(){

        List<Category> categories = categoryRepository.findAll();

        if (categories.isEmpty()) {
            init();
            categories = categoryRepository.findAll();
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping(path = "/category", consumes = "application/json")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        Category objCategory = categoryRepository.save(category);
        return new ResponseEntity<>(objCategory, HttpStatus.CREATED);
    }

    @PutMapping(path = "/category/{id}", consumes = "application/json")
    public ResponseEntity<?> updateCategory(@RequestBody Category category, @PathVariable("id") Long id){

        boolean categoryExist = categoryRepository.existsById(id);

        if (!categoryExist) {
            return new ResponseEntity<>("Update Failed, Category ID "+id+" tidak ditemukan.", HttpStatus.BAD_REQUEST);
        }

        Category objCategory = categoryRepository.findById(id).get();
        objCategory.setCategoryName(category.getCategoryName());

        objCategory = categoryRepository.save(objCategory);

        return new ResponseEntity<>(objCategory, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {

        boolean categoryExist = categoryRepository.existsById(id);

        if (!categoryExist) {
            return new ResponseEntity<String>("Delete Failed, Category ID "+id+" tidak ditemukan.", HttpStatus.BAD_REQUEST);
        }

        categoryRepository.deleteById(id);
        return new ResponseEntity<String>("Category ID "+id+" Berhasil dihapus.", HttpStatus.OK);
    }

}
