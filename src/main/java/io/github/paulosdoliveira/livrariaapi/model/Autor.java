package io.github.paulosdoliveira.livrariaapi.model;


import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(length = 90)
    private String descricaoBreve;

    @Column(nullable = false)
    private Date dataNascimento;

}
