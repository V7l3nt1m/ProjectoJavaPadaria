/*------------------------------------
Tema: Gestão de uma Padaria
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: EstoqueFile.java
Data: 13.06.2024
--------------------------------------*/

import javax.swing.*;
import SwingComponents.*;
import Calendario.*;
import java.io.*;
import java.util.*;

public class EstoqueFile extends ObjectsFile
{
	
	public EstoqueFile()
	{
		super("EstoqueFile.dat", new EstoqueModelo() );
	}

	public static String[][] listarProdutosM()
	{
		EstoqueFile ficheiro = new EstoqueFile();
		EstoqueModelo modelo = new EstoqueModelo();
		ProducaoModelo modeloProd = new ProducaoModelo();
		ProducaoFile modeloFileProd = new ProducaoFile();

		String [][] dados=null;
		int qtdRegistros = (int)(ficheiro.getNregistos());
		int index = 0;
		int contador =0;
		try
		{
			ficheiro.stream.seek(4);
			for (int c = 0; c < qtdRegistros; ++c)
			{
				modelo.read( ficheiro.stream );
				if(modelo.getStatus() == true && modelo.getIngrediente().equals("null"))
				{
					index++;
				}
			}
			dados = new String[index][6];

			ficheiro.stream.seek(4);

			for (int c = 0; c < qtdRegistros; ++c)
			{
				modelo.read( ficheiro.stream );
				if (modelo.getIngrediente().equals("null")) 
				{
					modeloProd = modeloFileProd.pesquisarEntradaPorProduto(modelo.getProdutoAcabado());
					dados[contador][0] = "" + modelo.getId();
					dados[contador][1] = "" + modelo.getProdutoAcabado();
					dados[contador][2] = "" + modelo.getNivelMinimo();
					dados[contador][3] = "" + modelo.getNivelAtual();
					dados[contador][4] = "" + modeloProd.getPrecoUni();
					dados[contador][5] = modelo.getDataEntradaEstoque();
					contador++;
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
		return dados;

	}
	public static String[][] estoqueMatriz()
	{
		EstoqueFile ficheiro = new EstoqueFile();
		EstoqueModelo modelo = new EstoqueModelo();

		String [][] dados=null;
		int qtdRegistros = (int)(ficheiro.getNregistos());
		int index = 0;
		int contador =0;
		try
		{
			ficheiro.stream.seek(4);
			for (int c = 0; c < qtdRegistros; ++c)
			{
				modelo.read( ficheiro.stream );
				if(modelo.getStatus() == true && Integer.parseInt(modelo.getProdutoAcabado()) == 0)
				{
					index++;
				}
			}
			dados = new String[index][8];

			ficheiro.stream.seek(4);

			for (int c = 0; c < qtdRegistros; ++c)
			{
				modelo.read( ficheiro.stream );
				if(modelo.getStatus() == true && Integer.parseInt(modelo.getProdutoAcabado()) == 0)
				{
					dados[contador][0] = "" + modelo.getId();
					dados[contador][1] = modelo.getIngrediente();
					dados[contador][2] = modelo.getUnidadeMedida();
					dados[contador][3] = "" + modelo.getNivelMinimo();
					dados[contador][4] = "" + modelo.getNivelAtual();
					dados[contador][5] = modelo.getDataEntradaEstoque();
					contador++;
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	

		return dados;
	}

	public static void pesquisarIngredienteEstoqueV(String nomeProcurado)
	{
		EstoqueFile ficheiro = new EstoqueFile();
		EstoqueModelo modelo = new EstoqueModelo();
		String output = "";
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				
				if (modelo.getIngrediente().equalsIgnoreCase( nomeProcurado ) && modelo.getStatus() == true)
				{
					output += modelo.toString();
					output += "---------------------------------------";
					JOptionPane.showMessageDialog(null, modelo.toString(), 
					"Gestao de Padaria", JOptionPane.INFORMATION_MESSAGE);
				}
			}					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}		
	}

	public static EstoqueModelo getProdutoPesquisa(String produto)
	{
		EstoqueFile ficheiro = new EstoqueFile();
		EstoqueModelo modelo = new EstoqueModelo();
		
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				
				if (modelo.getStatus() == true && modelo.getProdutoAcabado().equals(produto))
					return modelo;
			}					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
		return modelo;	
	}

	public static StringVector getAllProdutosEstoque()
	{
		EstoqueFile ficheiro = new EstoqueFile();
		EstoqueModelo modelo = new EstoqueModelo();
		StringVector vector = new StringVector();
		
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				if(modelo.getNivelAtual() > 0 && modelo.getStatus() == true && Integer.parseInt(modelo.getProdutoAcabado()) == 0)
					vector.add(modelo.getIngrediente());
			}					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
		return vector;	
	}

	public static EstoqueModelo pesquisarIngredienteEstoque(String ingrediente)
	{
		EstoqueFile ficheiro = new EstoqueFile();
		EstoqueModelo modelo = new EstoqueModelo();
		
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				
				if (modelo.getIngrediente().equalsIgnoreCase( ingrediente ) && modelo.getStatus() == true)
					return modelo;
			}					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
		return modelo;	
	}
	

	public static StringVector getAllIngredientes()
	{
		EstoqueFile ficheiro = new EstoqueFile();
		EstoqueModelo modelo = new EstoqueModelo();
		StringVector vector = new StringVector();
		
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				if(modelo.getStatus() == true && Integer.parseInt(modelo.getProdutoAcabado()) == 0)
					vector.add( modelo.getIngrediente() );
			}
						
			vector.sort();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	
		return vector;
	}
	
	public void salvarDados(EstoqueModelo modelo)
	{
		try
		{
			//colocar o File Pointer no final do ficheiro
			stream.seek( stream.length() );
			
			//escrever os dados no ficheiro
			modelo.write(stream);

			incrementarProximoCodigo();
			
			JOptionPane.showMessageDialog(null, "Dados Salvos com Sucesso!");
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Falha ao Salvar um Novo Material");
		}
	}

	public void alterarDados(EstoqueModelo modelo_novo)
	{
		EstoqueModelo modelo_antigo = new EstoqueModelo();
		
		try
		{
			stream.seek(4);
			
			for(int i = 0; i < getNregistos(); ++i)
			{
				modelo_antigo.read( stream );
				
				if (i == 0 && modelo_antigo.getId() == modelo_novo.getId())
				{
					stream.seek(4); 
					modelo_novo.write( stream );
					JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
					return;
				}	
				else
				{
					if (modelo_antigo.getId() + 1 == modelo_novo.getId())
					{
						modelo_novo.write( stream);
						JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
						return;
					}
							
				}			
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		
	}

	public void eliminarDados(EstoqueModelo modelo_novo)
	{
		EstoqueModelo modelo_antigo = new EstoqueModelo();
		
		try
		{
			stream.seek(4);
			
			for(int i = 0; i < getNregistos(); ++i)
			{
				modelo_antigo.read( stream );
				
				if (i == 0 && modelo_antigo.getId() == modelo_novo.getId())
				{
					stream.seek(4); 
					modelo_novo.write( stream );
					JOptionPane.showMessageDialog(null, "Dados Eliminados com sucesso!");
					return;
				}	
				else
				{
					if (modelo_antigo.getId() + 1 == modelo_novo.getId())
					{
						modelo_novo.write( stream);
						JOptionPane.showMessageDialog(null, "Dados Eliminados alterados com sucesso!");
						return;
					}
							
				}			
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		
	}

	public static EstoqueModelo pesquisarEntradaPorId(String id)
	{
		EstoqueFile ficheiro = new EstoqueFile();
		EstoqueModelo modelo = new EstoqueModelo();
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				if((""+modelo.getId()).equals(id) && modelo.getStatus() == true)
					return modelo;
			}					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}		

		return modelo;
	}

	public void adicionarDados(EstoqueModelo modelo_novo)
	{
		EstoqueModelo modelo_antigo = new EstoqueModelo();
		
		try
		{
			stream.seek(4);
			
			for(int i = 0; i < getNregistos(); ++i)
			{
				modelo_antigo.read( stream );
				
				if (i == 0 && modelo_antigo.getId() == modelo_novo.getId())
				{
					stream.seek(4); 
					modelo_novo.write( stream );
					return;
				}	
				else
				{
					if (modelo_antigo.getId() + 1 == modelo_novo.getId())
					{
						modelo_novo.write( stream);
						return;
					}
							
				}			
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void main(String args[])
	{
		listarProdutosM();
	}
}