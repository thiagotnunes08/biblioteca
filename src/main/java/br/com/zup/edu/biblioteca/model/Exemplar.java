package br.com.zup.edu.biblioteca.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Exemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   @ManyToOne(optional = false)
    private Livro livro;
   @Column(nullable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    public Exemplar(Livro livro) {
        this.livro = livro;
    }

    /**
     * @Deprecated = para uso do hibernate
     */
    @Deprecated
    public Exemplar() {
    }


    public Long getId() {
        return id;
    }
}
