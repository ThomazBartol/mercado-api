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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @PutMapping("/{id}")
    @Operation(summary = "Editar Personagem", description = "Atualiza os dados de um personagem")
    @CacheEvict(value = "characters", allEntries = true)
    public ResponseEntity<MyCharacter> update(@PathVariable Long id, @RequestBody @Valid MyCharacter updatedCharacter) {

        MyCharacter character = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personagem não encontrado com o id: " + id));

        character.setName(updatedCharacter.getName());
        character.setClasse(updatedCharacter.getClasse());

        MyCharacter savedCharacter = repository.save(character);

        return ResponseEntity.ok(savedCharacter);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Personagem", description = "Remove um personagem do banco de dados")
    @CacheEvict(value = "characters", allEntries = true)
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        MyCharacter character = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personagem não encontrado com o id: " + id));

        repository.delete(character);

        return ResponseEntity.noContent().build();
    }
}
