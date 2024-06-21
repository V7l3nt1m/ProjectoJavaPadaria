/*------------------------------------
Tema: Gestão de uma Padaria
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: ProducaoFile.java
Data: 15.06.2024
--------------------------------------*/

import javax.swing.*;
import SwingComponents.*;
import Calendario.*;
import java.io.*;
import java.util.*;

public class ProducaoFile extends ObjectsFile
{
	
	public ProducaoFile()
	{
		super("ProducaoFile.dat", new ProducaoModelo() );
	}

	
	

	public static String[][] listarMaterialV()
	{
		ProducaoFile ficheiro = new ProducaoFile();
		ProducaoModelo modelo = new ProducaoModelo();
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
				if(modelo.getStatus() == true)
				{
					index++;
				}
			}
			dados = new String[index][6];

			ficheiro.stream.seek(4);

			for (int c = 0; c < qtdRegistros; ++c)
			{
				modelo.read( ficheiro.stream );
				if(modelo.getStatus() == true)
				{
					dados[contador][0] = "" + modelo.getId();
					dados[contador][1] = "" + modelo.getProduto();
					dados[contador][2] = ""+modelo.getQtdProducao();
					dados[contador][3] = "" +  modelo.getPrecoUni();
					dados[contador][4] = "" + modelo.getCustoTotal();
					dados[contador][5] = ""+modelo.getDataProd();
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
	
	public static Double getPrecoUnit(String produto)
	{
		ProducaoFile ficheiro = new ProducaoFile();
		ProducaoModelo modelo = new ProducaoModelo();
		Double precoUni=0.0;
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				
				if (modelo.getProduto().equalsIgnoreCase( produto ) &&
				 modelo.getStatus() == true)
				{
					precoUni = modelo.getPrecoUni();
				}
			}					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}		

		return precoUni;
	
	}

	public static StringVector getAllProdutos()
	{
		ProducaoFile ficheiro = new ProducaoFile();
		ProducaoModelo modelo = new ProducaoModelo();
		StringVector vector = new StringVector();

		try
		{
			ficheiro.stream.seek(4);
			Set<String> uniqueSet = new LinkedHashSet<>();
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				if(modelo.getStatus() == true)
                	uniqueSet.add(modelo.getProduto());
			}
			vector.addAll(uniqueSet);
			vector.sort();
		}
        catch(Exception ex)
		{
			ex.printStackTrace();
		}	

		return vector;
	}

	public static ProducaoModelo pesquisarEntradaPorId(String id)
	{
		ProducaoFile ficheiro = new ProducaoFile();
		ProducaoModelo modelo = new ProducaoModelo();
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
	
	public static ProducaoModelo pesquisarEntradaPorProduto(String prodProcurado)
	{
		ProducaoFile ficheiro = new ProducaoFile();
		ProducaoModelo modelo = new ProducaoModelo();
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				
				if (modelo.getProduto().equalsIgnoreCase( prodProcurado ) &&
				 modelo.getStatus() == true)
				{
					return modelo;
				}
			}					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}		

		return modelo;
	}

	public static void pesquisarEntradaPorNome(String nomeProcurado)
	{
		ProducaoFile ficheiro = new ProducaoFile();
		ProducaoModelo modelo = new ProducaoModelo();
		String output = "";
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				
				if (modelo.getProduto().equalsIgnoreCase( nomeProcurado ) && modelo.getStatus() == true)
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
	
	public void salvarDados(ProducaoModelo modelo)
	{
		try
		{
			JOptionPane.showMessageDialog(null,modelo.toString());
			
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

	public void alterarDados(ProducaoModelo modelo_novo)
	{
		ProducaoModelo modelo_antigo = new ProducaoModelo();
		
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

	public void eliminarDados(ProducaoModelo modelo_novo)
	{
		ProducaoModelo modelo_antigo = new ProducaoModelo();
		
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

}