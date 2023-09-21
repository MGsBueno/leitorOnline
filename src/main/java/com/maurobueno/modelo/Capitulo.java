package com.maurobueno.modelo;

import javax.persistence.*;

@Table(name="Capitulo")

public class Capitulo
{
    private Long id;
    private String nome;
    private String titulo;
    private String urlConteudo;
    private int numeroCapitulo;


    // ********* Construtores *********

    public Capitulo()
    {
    }

    public Capitulo(String titulo, String urlConteudo, int numeroCapitulo) {
        this.titulo = titulo;
        this.urlConteudo = urlConteudo;
        this.numeroCapitulo = numeroCapitulo;
    }

    // ********* Métodos do Tipo Get *********

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    public Long getId()
    {	return id;
    }

    public String getTitulo() {
        return titulo;
    }
    public String getUrlConteudo() {
        return urlConteudo;
    }

    public int getNumeroCapitulo() {
        return numeroCapitulo;
    }


    // ********* Métodos do Tipo Set *********

    private void setId(Long id)
    {	this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setUrlConteudo(String urlConteudo) {
        this.urlConteudo = urlConteudo;
    }

    public void setNumeroCapitulo(int numeroCapitulo) {
        this.numeroCapitulo = numeroCapitulo;
    }
}


