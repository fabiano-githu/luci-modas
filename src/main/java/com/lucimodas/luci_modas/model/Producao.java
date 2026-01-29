package com.lucimodas.luci_modas.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "producoes")
public class Producao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeProducao;
    private String donaTrabalho;
    private String quemTrouxe;
    private String codigo;

    @Column(length = 500)
    private String descricao;

    private Integer quantidade;

    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;

    private LocalDate dataChegada;
    private LocalDate dataPrimeiraEntrega;
    private LocalDate dataEntregaFinal;

    private String tamanho;

    // Caminho da imagem salva no PC
    private String caminhoImagem;

    /* =======================
       GETTERS E SETTERS
       ======================= */

    public Long getId() {
        return id;
    }

    public String getNomeProducao() {
        return nomeProducao;
    }

    public void setNomeProducao(String nomeProducao) {
        this.nomeProducao = nomeProducao;
    }

    public String getDonaTrabalho() {
        return donaTrabalho;
    }

    public void setDonaTrabalho(String donaTrabalho) {
        this.donaTrabalho = donaTrabalho;
    }

    public String getQuemTrouxe() {
        return quemTrouxe;
    }

    public void setQuemTrouxe(String quemTrouxe) {
        this.quemTrouxe = quemTrouxe;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(LocalDate dataChegada) {
        this.dataChegada = dataChegada;
    }

    public LocalDate getDataPrimeiraEntrega() {
        return dataPrimeiraEntrega;
    }

    public void setDataPrimeiraEntrega(LocalDate dataPrimeiraEntrega) {
        this.dataPrimeiraEntrega = dataPrimeiraEntrega;
    }

    public LocalDate getDataEntregaFinal() {
        return dataEntregaFinal;
    }

    public void setDataEntregaFinal(LocalDate dataEntregaFinal) {
        this.dataEntregaFinal = dataEntregaFinal;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }
}
