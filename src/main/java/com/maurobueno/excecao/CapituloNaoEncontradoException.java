package com.maurobueno.excecao;

public class CapituloNaoEncontradoException extends Exception
{	
	private final static long serialVersionUID = 1;
	
	private int codigo;
	
	public CapituloNaoEncontradoException(String msg)
	{	super(msg);
	}

	public CapituloNaoEncontradoException(int codigo, String msg)
	{	super(msg);
		this.codigo = codigo;
	}
	
	public int getCodigoDeErro()
	{	return codigo;
	}
}	