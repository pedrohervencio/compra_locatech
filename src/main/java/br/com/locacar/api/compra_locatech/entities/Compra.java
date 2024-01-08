package br.com.locacar.api.compra_locatech.entities;

import br.com.locacar.api.compra_locatech.valueobjects.Proposta;
import br.com.locacar.api.compra_locatech.valueobjects.Status;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_compra")
public class Compra {
    @Id
    private Long numero;
    @ManyToOne
    @JoinColumn(name = "diretor_numero")
    private Diretor diretor;
    @ManyToOne
    @JoinColumn(name = "administrativo_numero")
    private Administrativo administrativo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;
    private Proposta proposta;
    private Status status;

    public Compra() {}

    public Compra(Long numero, Diretor diretor, Administrativo administrativo, Veiculo veiculo,
                  Proposta proposta, Status status) {
        this.numero = numero;
        this.diretor = diretor;
        this.administrativo = administrativo;
        this.veiculo = veiculo;
        this.proposta = proposta;
        this.status = status;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Diretor getDiretor() {
        return diretor;
    }

    public void setDiretor(Diretor diretor) {
        this.diretor = diretor;
    }

    public Administrativo getAdministrativo() {
        return administrativo;
    }

    public void setAdministrativo(Administrativo administrativo) {
        this.administrativo = administrativo;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public void setProposta(Proposta proposta) {
        this.proposta = proposta;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compra compra = (Compra) o;
        return Objects.equals(numero, compra.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString() {
        return "Compra{" +
                "numero=" + numero +
                ", diretor=" + diretor +
                ", administrativo=" + administrativo +
                ", veiculo=" + veiculo +
                ", proposta=" + proposta +
                ", status=" + status +
                '}';
    }
}
