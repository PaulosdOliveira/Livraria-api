package io.github.paulosdoliveira.livrariaapi.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table
public class Compras {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn
    @OneToOne
    private Usuarios usuario;

    @JoinColumn
    @OneToOne
    private Livro livro;
}
