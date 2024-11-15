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


public class EntradaModelo implements RegistGeneric
{
    int id, qtdEntrada;
    StringBufferModelo ingrediente, unidadeMedida;
    Double custoUnit, custoTotal;
    DataModelo dataEntrada, dataValidade;
    boolean status;


    public EntradaModelo()
    {
        id = 0;
        qtdEntrada = 0;
        custoUnit = 0.0;
        custoTotal = 0.0;
		ingrediente = new StringBufferModelo("", 50); 
		unidadeMedida = new StringBufferModelo("", 20);
		dataEntrada = new DataModelo();
        dataValidade = new DataModelo();
        status = false;
    }

    public EntradaModelo(int id, int qtdEntrada, double custoUnit, double custoTotal, String ingrediente, String unidadeMedida,
   String dataEntrada,  String dataValidade, boolean estado)
    {
        this.id = id;
        this.qtdEntrada = qtdEntrada;
        this.custoUnit = custoUnit;
        this.custoTotal = custoTotal;
		this.ingrediente = new StringBufferModelo(ingrediente, 50); 
		this.unidadeMedida = new StringBufferModelo(unidadeMedida, 20);
		this.dataEntrada = new DataModelo(dataEntrada);
		this.dataValidade = new DataModelo(dataValidade);
        this.status = estado;
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

        public Double getCustoUnit()
        {
            return custoUnit;
        }

        public Double getCustoTotal()
        {
            return custoTotal;
        }

         public String getDataEntrada()
        {
            return dataEntrada.toString();
        }

        public String getDataValidade()
        {
            return dataValidade.toString();
        }

        public boolean getStatus()
        {
            return status;
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
            unidadeMedida = new StringBufferModelo(novoUnidadeMedida, 20);

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

        public void setStatus(boolean new_status)
        {
            this.status = new_status;
        }

        public String toString()
        {
            String str = "Dados das Entradas Modelo\n\n";

            str += "Id: " + getId() + "\n";
            str += "Quantidade: " + getQtdEntrada() + "\n";
            str += "Material: " + getIngrediente() + "\n";
            str += "Unidade de Medida: " + getUnidadeMedida() + "\n";
            str += "Data de Entrada: " + getDataEntrada() + "\n";
            str += "Data de Validade: " + getDataValidade() + "\n";
            str += "Custo Unitario: " + getCustoUnit() + "\n";
            str += "Custo Total: " + getCustoTotal() + "\n";
            str += "Estado: " + getStatus() + "\n"; 
            return str;
        }

        public long sizeof()
	    {
            
            try
            {
                return 70 * 2 + 4*2 + 1 + 8*2 + 12*2; // (soma dos Stringbuffers)*2 + 4 bytes (id e qtd entrada) + 1 byte (boolean) + 8*2+ 12 bytes * 2(datas) 
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
            stream.writeBoolean(status);
			
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
            status = stream.readBoolean();	
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Falha ao tentar Ler no Ficheiro");
		}
    }

    public void eliminar()
    {
        EntradaFile file = new EntradaFile();
        file.eliminarDados(this);
    }
    
    public void salvar()
    {
        EntradaFile file = new EntradaFile();
        file.salvarDados(this);
    }

    public void editarDados()
    {
        EntradaFile file= new EntradaFile();
        file.alterarDados(this);
    }
}