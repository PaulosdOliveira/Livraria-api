package io.github.paulosdoliveira.livrariaapi.model;


import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false , unique = true, length = 40)
    private String titulo;

    @Column(nullable = false , length = 150)
    private String descricao;

    @Column(nullable = false , unique = true)
    private String ISBN;

    @Column(nullable = false)
    private Date dataPublicacao;

    @JoinColumn
    @ManyToOne
    private Autor autor;


}
