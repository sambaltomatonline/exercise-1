package id.springboot.example.example1.controller;

import id.springboot.example.example1.model.Author;
import id.springboot.example.example1.model.Book;
import id.springboot.example.example1.model.Category;
import id.springboot.example.example1.repository.AuthorRepository;
import id.springboot.example.example1.repository.BookRepository;
import id.springboot.example.example1.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/book-management")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AuthorRepository authorRepository;

    private void init(){

        log.info("init called");

        Category kategori1 = new Category("Fiksi");
        Category kategori2 = new Category("Non Fiksi");

        kategori1 = categoryRepository.save(kategori1);
        kategori2 = categoryRepository.save(kategori2);

        Author author1 = new Author("Author 1","email.author1@gmail.com","M");
        Author author2 = new Author("Author 2","email.author2@gmail.com","F");

        author1 = authorRepository.save(author1);
        author2 = authorRepository.save(author2);

        Book book1 = new Book("Book 1","Book 1 Description", kategori1.getId(), author1.getId());
        Book book2 = new Book("Book 2","Book 2 Description", kategori2.getId(), author1.getId());
        Book book3 = new Book("Book 3","Book 3 Description", kategori2.getId(), author2.getId());

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

    }

    @GetMapping(path = "/books")
    public ResponseEntity<List<Book>> getAllBooks(){

        List<Book> books = bookRepository.findAll();

        if (books.isEmpty()) {
            init();
            books = bookRepository.findAll();
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping(path = "/book", consumes = "application/json")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        bookRepository.save(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PutMapping(path = "/book/{id}", consumes = "application/json")
    public ResponseEntity<?> updateBook(@RequestBody Book book, @PathVariable("id") Long id){

        boolean bookExist = bookRepository.existsById(id);

        if (!bookExist) {
            return new ResponseEntity<>("Update Failed, Book ID "+id+" tidak ditemukan.", HttpStatus.BAD_REQUEST);
        }

        Book objBook = bookRepository.findById(id).get();
        objBook.setTitle(book.getTitle());
        objBook.setDescription(book.getDescription());
        objBook.setAuthor_id(book.getAuthor_id());
        objBook.setCategory_id(book.getCategory_id());

        objBook = bookRepository.save(objBook);

        return new ResponseEntity<>(objBook, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/book/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {

        boolean bookExist = bookRepository.existsById(id);

        if (!bookExist) {
            return new ResponseEntity<String>("Delete Failed, Book ID "+id+" tidak ditemukan.", HttpStatus.BAD_REQUEST);
        }

        bookRepository.deleteById(id);
        return new ResponseEntity<String>("Book ID "+id+" Berhasil dihapus.", HttpStatus.OK);
    }

}
