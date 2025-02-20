package io.github.paulosdoliveira.livrariaapi;

import io.github.paulosdoliveira.livrariaapi.model.Foto;
import io.github.paulosdoliveira.livrariaapi.model.Livro;
import io.github.paulosdoliveira.livrariaapi.model.enums.ExtensaoFoto;
import io.github.paulosdoliveira.livrariaapi.repositories.FotoRepository;
import io.github.paulosdoliveira.livrariaapi.repositories.LivroRepository;
import io.github.paulosdoliveira.livrariaapi.services.AutorService;
import io.github.paulosdoliveira.livrariaapi.services.FotoService;
import io.github.paulosdoliveira.livrariaapi.services.LivroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@SpringBootTest
class LivrariaapiApplicationTests {

    @Autowired
    private AutorService service;

    @Autowired
    private LivroRepository repository;

    @Test
    void contextLoads() {
    }

    @Test
    void test(){
        var list = repository.findAll();
        list.forEach(System.out::println);
    }





}
