/*------------------------------------
Tema: Gestão de uma Padaria
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: EntradaModelo.java
Data: 30.05.2024
--------------------------------------*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;
import java.util.Random;


public class EntradaModelo implements RegistGeneric
{
    int id, qtdEntrada;
    DataModelo dataEntrada, dataValidade;
    Double custoUnit, custoTotal;
    StringBufferModelo ingrediente, unidadeMedida;
    Random random;
    

    public EntradaModelo()
    {
        random = new Random();

        id = random.nextInt(10);
        qtdEntrada = 0;
        custoUnit = 0.0;
        custoTotal = 0.0;
		ingrediente = new StringBufferModelo("", 50); 
		unidadeMedida = new StringBufferModelo("", 8);
		dataEntrada = new DataModelo();
        dataValidade = new DataModelo();
    }

    public EntradaModelo(int id, int qtdEntrada, double custoUnit, double custoTotal, String ingrediente, String unidadeMedida,
   String dataEntrada,  String dataValidade)
    {
        this.id = id;
        this.qtdEntrada = qtdEntrada;
        this.custoUnit = custoUnit;
        this.custoTotal = custoTotal;
		this.ingrediente = new StringBufferModelo(ingrediente, 50); 
		this.unidadeMedida = new StringBufferModelo(unidadeMedida, 5);
		this.dataEntrada = new DataModelo(dataEntrada);
		this.dataValidade = new DataModelo(dataValidade);
    }

    public int getId()
        {
            return id;
        }

        public int getQtdEntrada()
        {
            return qtdEntrada;
        }

        public String getIngrediente()
        {
            return ingrediente.toStringEliminatingSpaces();
        }

        public String getUnidadeMedida()
        {
            return unidadeMedida.toStringEliminatingSpaces();

        }

        public String getDataEntrada()
        {
            return dataEntrada.toString();
        }

        public String getDataValidade()
        {
            return dataValidade.toString();
        }

        public Double getCustoUnit()
        {
            return custoUnit;
        }

        public Double getCustoTotal()
        {
            return custoTotal;
        }


        public void setId(int novoId)
        {
            id = novoId;
        }

        public void setQtdEntrada(int novoQtdEntrada)
        {
            qtdEntrada = novoQtdEntrada;
        }

        public void setIngrediente(String novoIngrediente)
        {
            ingrediente = new StringBufferModelo(novoIngrediente, 50);
        }

        public void setUnidadeMedida(String novoUnidadeMedida)
        {
            unidadeMedida = new StringBufferModelo(novoUnidadeMedida, 50);

        }

        public void setDataEntrada(String novaDataEntrada)
        {
            dataEntrada = new DataModelo( novaDataEntrada );
        }

        public void setDataValidade(String novaDataValidade)
        {
            dataValidade = new DataModelo( novaDataValidade );
        }

        public void setCustoUnit(double novoCustoUnit)
        {
            custoUnit = novoCustoUnit;
        }

        public void setCustoTotal(double novoCustoTotal)
        {
            custoTotal = novoCustoTotal;
        }

        public long sizeof()
	{		
		try
		{
			return 55 * 2 + 4 + 24; // 396	
		}
		catch(Exception ex)
		{
			return 0;
		}
				
	}
	
	public void write(RandomAccessFile stream)
	{
		try
		{
			stream.writeInt(id);
            stream.writeInt(qtdEntrada);
            stream.writeDouble(custoUnit);
            stream.writeDouble(custoTotal);
			ingrediente.write(stream); 
			unidadeMedida.write(stream);
			dataEntrada.write(stream);
			dataValidade.write(stream);

			
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Falha ao tentar Escrever no Ficheiro");
		}
	}
	public void read(RandomAccessFile stream)
	{
		try
		{
			id = stream.readInt();
            qtdEntrada = stream.readInt();
            custoUnit = stream.readDouble();
            custoTotal = stream.readDouble();
			ingrediente.read(stream); 
            unidadeMedida.read(stream); 
            dataEntrada.read(stream);
            dataValidade.read(stream);		
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Falha ao tentar Ler no Ficheiro");
		}
    }

    public void salvar()
    {
        EntradaFile file = new EntradaFile();
        file.salvarDados(this);
    }
}