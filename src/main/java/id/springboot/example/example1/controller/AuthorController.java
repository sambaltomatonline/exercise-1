package id.springboot.example.example1.controller;

import id.springboot.example.example1.model.Author;
import id.springboot.example.example1.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/author-management")
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    private void init(){

        log.info("init called");

        Author author1 = new Author("Author 1","email.author1@gmail.com","M");
        Author author2 = new Author("Author 2","email.author2@gmail.com","F");

        authorRepository.save(author1);
        authorRepository.save(author2);

    }

    @GetMapping(path = "/authors")
    public ResponseEntity<List<Author>> getAllAuthors(){

        List<Author> authors = authorRepository.findAll();

        if (authors.isEmpty()) {
            init();
            authors = authorRepository.findAll();
        }
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @PostMapping(path = "/author", consumes = "application/json")
    public ResponseEntity<Author> addAuthor(@RequestBody Author author){
        Author objAuthor = authorRepository.save(author);
        return new ResponseEntity<>(objAuthor, HttpStatus.CREATED);
    }

    @PutMapping(path = "/author/{id}", consumes = "application/json")
    public ResponseEntity<?> updateAuthor(@RequestBody Author author, @PathVariable("id") Long id){

        boolean authorExist = authorRepository.existsById(id);

        if (!authorExist) {
            return new ResponseEntity<>("Update Failed, Author ID "+id+" tidak ditemukan.", HttpStatus.BAD_REQUEST);
        }

        Author objAuthor = authorRepository.findById(id).get();
        objAuthor.setName(author.getName());
        objAuthor.setGender(author.getGender());
        objAuthor.setEmail(author.getEmail());

        objAuthor = authorRepository.save(objAuthor);

        return new ResponseEntity<>(objAuthor, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/author/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable("id") Long id) {

        boolean authorExist = authorRepository.existsById(id);

        if (!authorExist) {
            return new ResponseEntity<String>("Delete Failed, Author ID "+id+" tidak ditemukan.", HttpStatus.BAD_REQUEST);
        }

        authorRepository.deleteById(id);
        return new ResponseEntity<String>("Author ID "+id+" Berhasil dihapus.", HttpStatus.OK);
    }

}
