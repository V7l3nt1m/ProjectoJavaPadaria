/*------------------------------------
Tema: Gestão de uma Padaria
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: EntradaFile.java
Data: 30.05.2024
--------------------------------------*/

import javax.swing.*;
import SwingComponents.*;
import Calendario.*;
import java.io.*;
import java.util.*;

public class EntradaFile extends ObjectsFile
{
	
	public EntradaFile()
	{
		super("EntradaFile.dat", new EntradaModelo() );
	}

	
	public static String [][] listarMaterialV()
	{
		EntradaFile ficheiro = new EntradaFile();
		EntradaModelo modelo = new EntradaModelo();
		
		int qtdRegistros = (int)(ficheiro.getNregistos());
		
		String [][] dados = new String[qtdRegistros][8];

		try
		{
			ficheiro.stream.seek(4);
			int index = 0;
			for (int c = 0; c < qtdRegistros; ++c)
			{
				modelo.read( ficheiro.stream );
				if(modelo.getStatus())
				{
					dados[index][0] = "" + modelo.getId();
					dados[index][1] = modelo.getIngrediente();
					dados[index][2] = modelo.getUnidadeMedida();
					dados[index][3] = "" + modelo.getQtdEntrada();
					dados[index][4] = "" +  modelo.getCustoUnit();
					dados[index][5] = "" + modelo.getCustoTotal();
					dados[index][6] = modelo.getDataEntrada();
					dados[index][7] = modelo.getDataValidade();
					index++;
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
		EntradaFile ficheiro = new EntradaFile();
		EntradaModelo modelo = new EntradaModelo();
		StringVector vector = new StringVector();

		try
		{
			ficheiro.stream.seek(4);
			Set<String> uniqueSet = new LinkedHashSet<>();
			for (int i = 0; i < ficheiro.getNregistos()+1; ++i)
			{
				modelo.read( ficheiro.stream );
                uniqueSet.add(modelo.getIngrediente());
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
		EntradaFile ficheiro = new EntradaFile();
		EntradaModelo modelo = new EntradaModelo();

		String output = "Listagem de Dados do Ficheiro\n\n";

		try
		{
			ficheiro.stream.seek(4);
			for (int i = 0; i < ficheiro.getNregistos()+1; ++i)
			{
				modelo.read( ficheiro.stream );

				output += "---------------------------------\n";
				output += modelo.toString() + "\n";
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

	public static EntradaModelo pesquisarEntradaPorId(String id)
	{
		EntradaFile ficheiro = new EntradaFile();
		EntradaModelo modelo = new EntradaModelo();
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos()+1; ++i)
			{
				modelo.read( ficheiro.stream );
				if((""+modelo.getId()).equals(id))
					return modelo;
			}					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}		

		return modelo;
	}
	
	public static EntradaModelo pesquisarEntradaPorNomeM(String nomeProcurado)
	{
		EntradaFile ficheiro = new EntradaFile();
		EntradaModelo modelo = new EntradaModelo();
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos()+1; ++i)
			{
				modelo.read( ficheiro.stream );
				
				if (modelo.getIngrediente().equalsIgnoreCase( nomeProcurado ) &&
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
		EntradaFile ficheiro = new EntradaFile();
		EntradaModelo modelo = new EntradaModelo();
		String output = "";
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos()+1; ++i)
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
	
	public void salvarDados(EntradaModelo modelo)
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

	public void alterarDados(EntradaModelo modelo_novo)
	{
		EntradaModelo modelo_antigo = new EntradaModelo();
		
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

	public void eliminarDados(EntradaModelo modelo_novo)
	{
		EntradaModelo modelo_antigo = new EntradaModelo();
		
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