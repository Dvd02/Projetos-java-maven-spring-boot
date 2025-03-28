package br.com.david.mapper.curtom;

import java.math.BigDecimal;

public class PosseDO {

    private Long id;
    
    private PessoaDO pessoa;
    
    private String nome;
    
    private String descricao;
    
    private BigDecimal valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PessoaDO getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaDO pessoa) {
        this.pessoa = pessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}