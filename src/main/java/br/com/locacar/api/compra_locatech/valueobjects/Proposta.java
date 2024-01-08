package br.com.locacar.api.compra_locatech.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Proposta {
    private String fornecedor;
    private Integer preco;

    public Proposta() {}

    public Proposta(String fornecedor, Integer preco) {
        this.fornecedor = fornecedor;
        this.preco = preco;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Integer getPreco() {
        return preco;
    }

    public void setPreco(Integer preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proposta proposta = (Proposta) o;
        return Objects.equals(fornecedor, proposta.fornecedor) && Objects.equals(preco, proposta.preco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fornecedor, preco);
    }

    @Override
    public String toString() {
        return "Proposta{" +
                "fornecedor='" + fornecedor + '\'' +
                ", preco=" + preco +
                '}';
    }
}
