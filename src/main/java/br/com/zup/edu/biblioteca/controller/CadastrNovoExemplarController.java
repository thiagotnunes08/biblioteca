package br.com.zup.edu.biblioteca.controller;

import br.com.zup.edu.biblioteca.model.Exemplar;
import br.com.zup.edu.biblioteca.model.Livro;
import br.com.zup.edu.biblioteca.repository.ExemplarRepository;
import br.com.zup.edu.biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/livro/{isbn}/exemplares")
public class CadastrNovoExemplarController {

    @Autowired
    private final LivroRepository livroRepository;
    private final ExemplarRepository exemplarRepository;

    public CadastrNovoExemplarController(LivroRepository livroRepository, ExemplarRepository exemplarRepository) {
        this.livroRepository = livroRepository;
        this.exemplarRepository = exemplarRepository;
    }

    @PostMapping
    public ResponseEntity<String> cadastra(@PathVariable String isbn, UriComponentsBuilder uriComponentsBuilder){

    Livro livro =  livroRepository.findByISBN(isbn).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ISBN nao encontrado"));


       Exemplar novoExemplar = new Exemplar(livro);


      exemplarRepository.save(novoExemplar);

        URI location = uriComponentsBuilder.path("/livro/{id}/exemplares/{id}").buildAndExpand(livro.getId(),novoExemplar.getId()).toUri();

     return ResponseEntity.created(location).body("exemplar criado!");

    }
}
