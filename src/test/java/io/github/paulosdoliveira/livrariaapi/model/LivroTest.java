package io.github.paulosdoliveira.livrariaapi.model;


import io.github.paulosdoliveira.livrariaapi.repositories.LivroRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Year;
import java.util.List;

@SpringBootTest
public class LivroTest {

    @Autowired
    private LivroRepository repository;



}
