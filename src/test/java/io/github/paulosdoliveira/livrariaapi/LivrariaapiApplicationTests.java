package io.github.paulosdoliveira.livrariaapi;

import io.github.paulosdoliveira.livrariaapi.model.Foto;
import io.github.paulosdoliveira.livrariaapi.model.enums.ExtensaoFoto;
import io.github.paulosdoliveira.livrariaapi.repositories.FotoRepository;
import io.github.paulosdoliveira.livrariaapi.services.AutorService;
import io.github.paulosdoliveira.livrariaapi.services.FotoService;
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
    private FotoRepository repository;

    @Test
    void contextLoads() {
    }



}
