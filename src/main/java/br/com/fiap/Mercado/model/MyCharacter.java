package br.com.fiap.Mercado.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyCharacter {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "não pode estar em branco")
    private String name;
    @NotNull(message = "não pode estar vazio")
    private CharacterClass classe;
    @Min(1) @Max(99)
    private Integer nivel;
    @PositiveOrZero
    private Integer coins;

    @PrePersist
    public void prePersist() {
        if (nivel == null) {
            nivel = 1;
        }
        if (coins == null) {
            coins = 0;
        }
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public CharacterClass getClasse(){
        return classe;
    }

    public void setClasse(CharacterClass classe){
        this.classe = classe;
    }
}
