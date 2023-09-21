package com.maurobueno.dao;

import com.maurobueno.excecao.CapituloNaoEncontradoException;
import com.maurobueno.modelo.Capitulo;

import java.util.List;


public interface CapituloDAO
{
	long inclui(Capitulo umCapitulo);
	void altera(Capitulo umCapitulo) throws CapituloNaoEncontradoException;
	void exclui(long id) throws CapituloNaoEncontradoException;
	Capitulo recuperaUmCapitulo(long numero) throws CapituloNaoEncontradoException;
	List<Capitulo> recuperaCapitulo();
}