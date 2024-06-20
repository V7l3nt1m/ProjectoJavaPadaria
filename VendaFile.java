/*------------------------------------
Tema: Gestão de uma Padaria
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: VendaFile.java
Data: 15.06.2024
--------------------------------------*/

import javax.swing.*;
import SwingComponents.*;
import Calendario.*;
import java.io.*;
import java.util.*;

public class VendaFile extends ObjectsFile
{
	
	public VendaFile()
	{
		super("VendaFile.dat", new VendaModelo() );
	}

	
	public static String[][] listarMaterialV()
	{
		VendaFile ficheiro = new VendaFile();
		VendaModelo modelo = new VendaModelo();
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
			dados = new String[index][9];

			ficheiro.stream.seek(4);

			for (int c = 0; c < qtdRegistros; ++c)
			{
				modelo.read( ficheiro.stream );
				if(modelo.getStatus() == true)
				{
					dados[contador][0] = "" + modelo.getId();
					dados[contador][1] = modelo.getProduto();
					dados[contador][3] = "" + modelo.getQtdEntrada();
					dados[contador][4] = "" +  modelo.getPrecoUni();
					dados[contador][5] = "" + modelo.getPrecoTotal();
					dados[contador][6] = modelo.getDataVenda();
					dados[contador][7] = modelo.getClienteNome();
					dados[contador][8] = modelo.getTipoPagamento();
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

	

	public static StringVector getAllNames()
	{
		VendaFile ficheiro = new VendaFile();
		VendaModelo modelo = new VendaModelo();
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

	public static void listarMaterial()
	{
		VendaFile ficheiro = new VendaFile();
		VendaModelo modelo = new VendaModelo();

		String output = "Listagem de Dados do Ficheiro\n\n";

		try
		{
			ficheiro.stream.seek(4);
			for (int i = 0; i < ficheiro.getNregistos()+1; ++i)
			{
				modelo.read( ficheiro.stream );
				if(modelo.getStatus() == true)
				{
					output += "---------------------------------\n";
					output += modelo.toString() + "\n";
				}
			}

			JTextArea area = new JTextArea(40, 60);
			area.setText( output );
			area.setFocusable(false);
			JOptionPane.showMessageDialog(null, new JScrollPane( area ), 
					"Gestao de Padaria", JOptionPane.INFORMATION_MESSAGE);
		}
        catch(Exception ex)
		{
			ex.printStackTrace();
		}	
		
	}

	public static VendaModelo pesquisarVendaPorId(String id)
	{
		VendaFile ficheiro = new VendaFile();
		VendaModelo modelo = new VendaModelo();
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
	
	public static VendaModelo pesquisarVendaPorProduto(String nomeProcurado)
	{
		VendaFile ficheiro = new VendaFile();
		VendaModelo modelo = new VendaModelo();
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				
				if (modelo.getProduto().equalsIgnoreCase( nomeProcurado ) &&
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

	public static void pesquisarVendaPorProd(String nomeProcurado)
	{
		VendaFile ficheiro = new VendaFile();
		VendaModelo modelo = new VendaModelo();
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
	
	public void salvarDados(VendaModelo modelo)
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

	public void alterarDados(VendaModelo modelo_novo)
	{
		VendaModelo modelo_antigo = new VendaModelo();
		
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

	public void eliminarDados(VendaModelo modelo_novo)
	{
		VendaModelo modelo_antigo = new VendaModelo();
		
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