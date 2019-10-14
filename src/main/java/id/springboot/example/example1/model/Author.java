package id.springboot.example.example1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotNull(message = "nama tidak boleh null")
    @NotBlank(message = "nama tidak boleh Kosong")
    private String name;

    @NonNull
    @NotNull
    @NotBlank
    @Email(message = "Email tidak valid.")
    private String email;

    @NonNull
    @NotNull
    @NotBlank
    @Size(min = 1, max = 1)
    private String gender;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "author")
    @JsonIgnore
    @ToString.Exclude
    private List<Book> bookList = new ArrayList<>();

}
