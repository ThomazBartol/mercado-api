package br.com.fiap.Mercado.controller;

import br.com.fiap.Mercado.model.MyCharacter;
import br.com.fiap.Mercado.repository.CharacterRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("characters")
@Slf4j
public class CharacterController {

    @Autowired
    private CharacterRepository repository;

    @GetMapping
    @Operation(summary = "Listar personagens", description = "Retorna um array com todos os personagens")
    @Cacheable("chatacters")
    public List<MyCharacter> index(){
        return repository.findAll();
    }

    @PostMapping
    @CacheEvict(value = "characters", allEntries = true)
    @Operation(summary = "Cadastrar Personagem", description = "Cadastra um personagem no banco", responses = @ApiResponse(responseCode = "400", description = "Validação falhou"))
    @ResponseStatus(code = HttpStatus.CREATED)
    public MyCharacter create(@RequestBody @Valid MyCharacter character) {
        System.out.println("Personagem " + character.getName() + " criado com sucesso");
        return repository.save(character);
    }
}
