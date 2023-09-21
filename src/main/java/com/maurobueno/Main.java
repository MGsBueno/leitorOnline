package com.maurobueno;
import java.util.Scanner;


import com.maurobueno.dao.CapituloDAO;
import com.maurobueno.dao.impl.JPACapituloDAO;
import com.maurobueno.excecao.CapituloNaoEncontradoException;
import com.maurobueno.modelo.Capitulo;
import com.maurobueno	.util.FabricaDeDAOs;

import corejava.Console;

import java.util.List;

public class Principal
{	public static void main (String[] args)
{
//		Logger logger = LoggerFactory.getLogger(Principal.class);
//		logger.error("Mensagem de log emitida utilizando o LOG4J");
    // fatal - error - warning - info - debug


    String titulo;
    String urlConteudo;
    int numeroCapitulo; // Novo campo para o número do capítulo
    Capitulo umCapitulo;


    CapituloDAO capituloDAO = FabricaDeDAOs.getDAO(CapituloDAO.class);

    boolean continua = true;
    while (continua)
    {	System.out.println('\n' + "O que você deseja fazer?");
        System.out.println('\n' + "1. Cadastrar um capitulo");
        System.out.println("2. Alterar um capitulo");
        System.out.println("3. Remover um capitulo");
        System.out.println("4. Listar todos os capitulo");
        System.out.println("5. Sair");

        int opcao = Console.readInt('\n' +
                "Digite um número entre 1 e 5:");

        switch (opcao)
        {	case 1:
        {
            titulo = Console.readLine('\n' +
                    "Informe o titulo do capitulo: ");

            urlConteudo = Console.readLine('\n' +
                    "Informe a url do capitulo: ");

            numeroCapitulo = Console.readInt(
                    "Informe o numero do capitulo: ");

            umCapitulo = new Capitulo(titulo, urlConteudo, numeroCapitulo = Integer.parseInt(numeroCapitulo));

            capituloDAO.inclui(umCapitulo);

            System.out.println('\n' + "Capitulo número " +
                    umCapitulo.getId() + " incluído com sucesso!");

            break;
        }

            case 2:
            {
                int resposta = Integer.parseInt(Console.readLine('\n' +
                        "Digite o número do capitulo que você deseja alterar: "));

                try
                {
                    umCapitulo = capituloDAO.recuperaUmCapitulo(resposta);
                }
                catch(CapituloNaoEncontradoException e)
                {	System.out.println('\n' + e.getMessage());
                    break;
                }

                System.out.println('\n' +
                        "Número = " + umCapitulo.getId() +
                        "    Titulo = " + umCapitulo.getTitulo() +
                        "    url = " + umCapitulo.getUrlConteudo()
                );

                System.out.println('\n' + "O que você deseja alterar?");
                System.out.println('\n' + "1. Titulo");
                System.out.println("2. Url");
                System.out.println("3. Mumero");

                int opcaoAlteracao = integer.parseInt(Console.readLine('\n' +
                        "Digite um número de 1 a 3:"));


                switch (opcaoAlteracao)
                {	case 1:
                    String novoTitulo = Console.
                            readLine("Digite o novo titulo: ");

                    umCapitulo.setTitulo(novoTitulo);

                    try
                    {
                        capituloDAO.altera(umCapitulo);

                        System.out.println('\n' +
                                "Alteração de nome efetuada com sucesso!");
                    }
                    catch(CapituloNaoEncontradoExceptione)
                    {	System.out.println('\n' + e.getMessage());
                    }

                    break;


                    case 2:
                        String novaUrl = Console.
                                readLine("Digite o novo endereço de Url: ");

                        umCapitulo.setUrlConteudo(novaUrl);

                        try
                        {
                            capituloDAO.altera(umCapitulo);

                            System.out.println('\n' +
                                    "Alteração de Url efetuada com sucesso!");
                        }
                        catch(CapituloNaoEncontradoExceptione)
                        {	System.out.println('\n' + e.getMessage());
                        }

                        break;

                    case 3:
                        int novoNumero = Console.
                                readInt("Digite o novo numero: ");

                        umCapitulo.setNumeroCapitulo(novoNumero);

                        try
                        {
                            capituloDAO.altera(umCapitulo);

                            System.out.println('\n' +
                                    "Alteração de lance mínimo efetuada " +
                                    "com sucesso!");
                        }
                        catch(CapituloNaoEncontradoException e)
                        {	System.out.println('\n' + e.getMessage());
                        }

                        break;

                    default:
                        System.out.println('\n' + "Opção inválida!");
                }

                break;
            }

            case 3:
            {	int resposta = Console.readInt('\n' +
                    "Digite o número do capitulo que você deseja remover: ");

                try
                {
                    umCapitulo = capituloDAO.recuperaUmCapitulo(resposta);
                }
                catch(CapituloNaoEncontradoException e)
                {	System.out.println('\n' + e.getMessage());
                    break;
                }

                System.out.println('\n' +
                        "Número = " + umCapitulo.getId() +
                        "    Nome = " + umCapitulo.getNome());

                String resp = Console.readLine('\n' +
                        "Confirma a remoção do capitulo?");

                if(resp.equals("s"))
                {	try
                {
                    capituloDAO.exclui (umCapitulo.getId());
                    System.out.println('\n' +
                            "Capitulo removido com sucesso!");
                }
                catch(CapituloNaoEncontradoException e)
                {	System.out.println('\n' + e.getMessage());
                }
                }
                else
                {	System.out.println('\n' + "Capitulo não removido.");
                }

                break;
            }

            case 4:
            {
                List<Capitulo> capitulo = capituloDAO.recuperaCapitulo();

//                  Utilizando um consumer. Consumer é uma interface funcional. Ela recebe um
//                  argumento e não retorna nada. Para que um valor seja aceito pelo Consumer
//                  deve ser executado o método accept.


//                  Utilizando method reference. Method references são expressões que possuem
//                  o mesmo tratamento de expressões lambda, mas em vez de prover um corpo  à
//                  expressão lambda, eles (os method references) referenciam um método existente
//                  pelo nome.

                for (Capitulo capituloget : capitulo)
                {
                    System.out.println('\n' +
                            "Id = " + capituloget.getId() +
                            "  Nome = " + capituloget.getTitle() +
                            "  url = " + capituloget.getUrlConteudo() +
                            "  numero = " +  Integer.parseInt(capituloget.numeroCapitulo));
                }

                break;
            }

            case 5:
            {	continua = false;
                break;
            }

            default:
                System.out.println('\n' + "Opção inválida!");
        }
    }
}
}
