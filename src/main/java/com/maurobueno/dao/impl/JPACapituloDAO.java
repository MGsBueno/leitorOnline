package com.maurobueno.dao.impl;
import com.maurobueno.util.FabricaDeEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import java.util.List;

public class JPACapituloDAO implements CapituloDAO
{	
	public long inclui(Capitulo umCapitulo)
	{
		EntityManager em = null;
		EntityTransaction tx = null;

		try
		{	// transiente - objeto novo: ainda não persistente
			// persistente - após ser persistido
			// destacado - objeto persistente não vinculado a um entity manager
			em = FabricaDeEntityManager.criarEntityManager();

			tx = em.getTransaction();

			tx.begin();
			em.persist(umCapitulo);

			// O que acontece quando o método persist é executado:
			// 1. Os dados do objeto umProduto são inseridos no banco de dados (sem commit)
			// 2. O valor da coluna de auto incremento do banco de dados é atribuído
			//    ao campo id do objeto umProduto.
			// 3. O objeto umProduto é inserido na lista de objetos monitorados do entity
			//    manager.

			// umProduto.setNome("abc");  // Provoca o agendamento de um update no banco
			                              // de dados. O update será executado no momento
			                              // do commit.
			tx.commit();
			return umCapitulo.getId();
		}
		catch(RuntimeException e)
		{	if (tx != null)
			{
				tx.rollback();
			}
			throw e;
		}
		finally
		{
			em.close();
		}
	}

	public Capitulo recuperaUmCapitulo(long numero) throws CapituloNaoEncontradoException
	{
		EntityManager em = null;

		try
		{
			em = FabricaDeEntityManager.criarEntityManager();
			Capitulo umCapitulo = em.find(Capitulo.class, numero);

			// Características no método find():
			// 1. É genérico: não requer um cast.
			// 2. Retorna null caso a linha não seja encontrada no banco.

			if(umCapitulo == null)
			{	throw new CapituloNaoEncontradoException("Capitulo não encontrado");
			}

			return umCapitulo;
		}
		finally
		{   em.close();
		}
	}

	public void altera(Capitulo umCapitulo) throws CapituloNaoEncontradoException
	{
		EntityManager em = null;
		EntityTransaction tx = null;
		Capitulo capitulo = null;
		try
		{
			em = FabricaDeEntityManager.criarEntityManager();
			tx = em.getTransaction();
			tx.begin();

			capitulo = em.find(Capitulo.class, umCapitulo.getId(), LockModeType.PESSIMISTIC_WRITE);

			if (capitulo == null) {
				throw new CapituloNaoEncontradoException(
					"Capitulo número " + umCapitulo.getId() + "não encontrado.");
			}
			// O merge entre nada e tudo é tudo. Ao tentar alterar um capitulo deletado ele será re-inserido
			// no banco de dados.
			em.merge(umCapitulo);

			tx.commit();
		}
		catch(RuntimeException e)
		{
			if (tx != null)
		    {   tx.rollback();
		    }
		    throw e;
		}
		finally
		{   em.close();
		}
	}

	public void exclui(long numero) throws CapituloNaoEncontradoException
	{
		EntityManager em = null;
		EntityTransaction tx = null;

		try
		{
			em = FabricaDeEntityManager.criarEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Capitulo capitulo = em.find(Capitulo.class, numero);

			if(capitulo == null)
			{	tx.rollback();
				throw new CapituloEncontradoException("Capitulo não encontrado");
			}

			em.remove(capitulo);
			tx.commit();
		}
		catch(RuntimeException e)
		{
			if (tx != null)
		    {   tx.rollback();
		    }
		    throw e;
		}
		finally
		{   em.close();
		}
	}

	public List<Capitulo> recuperaCapitulos()
	{
		EntityManager em = null;

		try
		{	em = FabricaDeEntityManager.criarEntityManager();

			List<Capitulo> capitulos = em
				.createQuery("select p from Capitulo p order by p.id")
				.getResultList();

			// Retorna um List vazio caso a tabela correspondente esteja vazia.

			return capitulos;
		}
		finally
		{   em.close();
		}
	}
}