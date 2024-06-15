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

	public static String [][] estoqueMatriz()
	{
		EstoqueFile ficheiro = new EstoqueFile();
		EstoqueModelo modelo = new EstoqueModelo();
		
		int qtdRegistros = (int)(ficheiro.getNregistos());
		String [][] dados = new String[qtdRegistros][6];

		try
		{
			ficheiro.stream.seek(4);
			for (int c = 0; c < qtdRegistros; ++c)
			{
				modelo.read( ficheiro.stream );
				
				dados[c][0] = "" + modelo.getId();
				dados[c][1] = modelo.getIngrediente();
				dados[c][2] = modelo.getUnidadeMedida();
				dados[c][3] = "" + modelo.getNivelMinimo();
				dados[c][4] = "" + modelo.getNivelAtual();
				dados[c][5] = modelo.getDataEntradaEstoque();
			
			}

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	

		return dados;
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
				
				if (modelo.getIngrediente().equalsIgnoreCase( ingrediente ))
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
}