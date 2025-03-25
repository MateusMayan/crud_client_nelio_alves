package com.mayan.crud_clientes.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cpf;
    private Double income;
    private LocalDate birthDate;
    private Integer children;

    public Client() {
    }

    public Client(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Campo obrigatório") @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Campo obrigatório") @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres") String name) {
        this.name = name;
    }

    public @CPF String getCpf() {
        return cpf;
    }

    public void setCpf(@CPF String cpf) {
        this.cpf = cpf;
    }

    public @PositiveOrZero(message = "A renda deve ser um valor positivo") Double getIncome() {
        return income;
    }

    public void setIncome(@PositiveOrZero(message = "A renda deve ser um valor positivo") Double income) {
        this.income = income;
    }

    public @PastOrPresent(message = "A data de nascimento não pode ser futura") LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(@PastOrPresent(message = "A data de nascimento não pode ser futura") LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public @PositiveOrZero(message = "A quantidade de filhos deve ser um valor positivo") Integer getChildren() {
        return children;
    }

    public void setChildren(@PositiveOrZero(message = "A quantidade de filhos deve ser um valor positivo") Integer children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


}
