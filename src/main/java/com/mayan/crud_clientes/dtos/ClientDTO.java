package com.mayan.crud_clientes.dtos;

import com.mayan.crud_clientes.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public class ClientDTO {

    private Long id;

    @NotBlank(message = "Campo obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String name;

    @CPF(message = "CPF inválido")
    private String cpf;

    @PositiveOrZero(message = "A renda deve ser um valor positivo")
    private Double income;

    @PastOrPresent(message = "A data de nascimento não pode ser futura")
    private LocalDate birthDate;

    @PositiveOrZero(message = "A quantidade de filhos deve ser um valor positivo")
    private Integer children;

    public ClientDTO() {
    }

    public ClientDTO(Client client) {
        id = client.getId();
        name = client.getName();
        cpf = client.getCpf();
        income = client.getIncome();
        birthDate = client.getBirthDate();
        children = client.getChildren();
    }

    public ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public @NotBlank(message = "Campo obrigatório") @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres") String getName() {
        return name;
    }

    public @CPF(message = "CPF inválido") String getCpf() {
        return cpf;
    }

    public @PositiveOrZero(message = "A renda deve ser um valor positivo") Double getIncome() {
        return income;
    }

    public @PastOrPresent(message = "A data de nascimento não pode ser futura") LocalDate getBirthDate() {
        return birthDate;
    }

    public @PositiveOrZero(message = "A quantidade de filhos deve ser um valor positivo") Integer getChildren() {
        return children;
    }
}
