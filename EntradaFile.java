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

	public static void main(String args[])
	{
		listarMaterial();
	}
}