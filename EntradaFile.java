/*------------------------------------
Tema: Gestão de uma Padaria
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: EntradaModelo.java
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
		
		int qtdRegistros = (int)(ficheiro.getNregistos()+1);
		
		String [][] dados = new String[qtdRegistros][8];

		try
		{
			ficheiro.stream.seek(4);
			for (int c = 0; c < ficheiro.getNregistos()+1; ++c)
			{
				modelo.read( ficheiro.stream );
				dados[c][0] = "" + modelo.getId();
				dados[c][1] = modelo.getIngrediente();
				dados[c][2] = modelo.getUnidadeMedida();
				dados[c][3] = "" + modelo.getQtdEntrada();
				dados[c][4] = "" +  modelo.getCustoUnit();
				dados[c][5] = "" + modelo.getCustoTotal();
				dados[c][6] = modelo.getDataEntrada();
				dados[c][7] = modelo.getDataValidade();
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
				
				if (modelo.getIngrediente().equalsIgnoreCase( nomeProcurado ) )
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
				
				if (modelo.getIngrediente().equalsIgnoreCase( nomeProcurado ) )
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
}