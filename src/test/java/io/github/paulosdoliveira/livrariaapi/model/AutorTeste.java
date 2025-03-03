package io.github.paulosdoliveira.livrariaapi.model;

import io.github.paulosdoliveira.livrariaapi.dto.livro.autor.AutorOptionDTO;
import io.github.paulosdoliveira.livrariaapi.repositories.AutorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class AutorTeste {


    @Autowired
    private AutorRepository repository;

    @Test
    public void buscarOptonAutorTeste(){
        var listaNomes = repository.nomesAutores();
        var listaId = repository.idAutores();
        List<AutorOptionDTO> dados = new ArrayList<>();
        for(int i = 0; i < listaNomes.size(); i++){
            String nome  = listaNomes.get(i);
            UUID id = listaId.get(i);
            dados.add(new AutorOptionDTO(nome, id));
        }
        dados.forEach(System.out::println);
    }




}
