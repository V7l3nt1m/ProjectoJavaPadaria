/*------------------------------------
Tema: Gestão de uma Padaria
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: ProducaoModelo.java
Data: 15.06.2024
--------------------------------------*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;


public class VendaModelo implements RegistGeneric
{
    private int id, qtdEntrada;
    private StringBufferModelo produto;
    private Double precoUni, precoTotal;
    private DataModelo dataVenda;
    private boolean status;

    public VendaModelo()
    {
        id = 0;
        qtdEntrada = 0;
        precoUni = 0.0;
        precoTotal = 0.0;
		produto = new StringBufferModelo("", 20);
        dataVenda = new DataModelo();
        status = false;
    }

    public VendaModelo(int id, int qtdEntrada, double precoUni, double precoTotal,
   String produto,  String dataVenda, boolean estado)
    {
        this.id = id;
        this.qtdEntrada = qtdEntrada;
        this.precoUni = precoUni;
        this.precoTotal = precoTotal;
		this.produto = new StringBufferModelo(produto, 20); 
		this.dataVenda = new DataModelo(dataVenda);
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

        public String getProduto()
        {
            return produto.toStringEliminatingSpaces();
        }

        public Double getPrecoUni()
        {
            return precoUni;
        }

        public Double getPrecoTotal()
        {
            return precoTotal;
        }

         public String getDataVenda()
        {
            return dataVenda.toString();
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

        public void setProd(String novoProd)
        {
            produto = new StringBufferModelo(novoProd,50);
        }

        public void setDataVenda(String novaDataVenda)
        {
            dataVenda = new DataModelo( novaDataVenda );
        }

        public void setPrecoUn(double novoPrecoU)
        {
            precoUni = novoPrecoU;
        }

        public void setPrecoTotal(double novoPrecoTotal)
        {
            precoTotal = novoPrecoTotal;
        }

        public void setStatus(boolean new_status)
        {
            this.status = new_status;
        }

        public String toString()
        {
            String str = "Dados de Producao\n\n";

            str += "Id: " + getId() + "\n";
            str += "Quantidade: " + getQtdEntrada() + "\n";
            str += "Produto: " + getProduto() + "\n";
            str += "Data da compra: " + getDataVenda() + "\n";
            str += "Preco Unitario: " + getPrecoUni() + "\n";
            str += "Preco Total: " + getPrecoTotal() + "\n";
            str += "Estado: " + getStatus() + "\n"; 
            return str;
        }

        public long sizeof()
	    {
            
            try
            {
                return 120;// 269 bytes
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
            stream.writeDouble(precoUni);
            stream.writeDouble(precoTotal);
			produto.write(stream); 
			dataVenda.write(stream);
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
            precoUni = stream.readDouble();
            precoTotal = stream.readDouble();
            produto.read(stream); 
            dataVenda.read(stream);
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
        VendaFile file = new VendaFile();
        file.eliminarDados(this);
    }
    
    public void salvar()
    {
        VendaFile file = new VendaFile();
        file.salvarDados(this);
    }

    public void editarDados()
    {
        VendaFile file = new VendaFile();
        file.alterarDados(this);
    }
}