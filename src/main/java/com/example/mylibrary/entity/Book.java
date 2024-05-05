package com.example.mylibrary.entity;

import com.example.mylibrary.Between;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name should be present")
    @NotNull(message = "Name should be present")
    @Pattern(regexp = ".*cat.*", message = "Substring 'cat' is not present")
    private String name;

    // @Min(value = 1800, message = "Should be greater than 1800")
    // @Max(value = 2023, message = "Should be less than 2023")
    @Between(from = 1800, to=2023, message = "Year should be between 1800 and 2023")
    @NotNull(message = "Year should be present")
    @Column(name = "y")
    private Integer year;
    private Integer authorId;

    public Book(String name, Integer year, Integer authorId) {
        this.name = name;
        this.year = year;
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", authorId=" + authorId +
                '}';
    }
}
