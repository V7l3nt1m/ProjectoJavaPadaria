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


public class ProducaoModelo implements RegistGeneric
{
    private int id, qtdProd;
    private StringBufferModelo produto;
    private Double precoUni, custoTotal;
    private DataModelo dataProd;
    private boolean status;

    public ProducaoModelo()
    {
        id = 0;
        qtdProd = 0;
        precoUni = 0.0;
        custoTotal = 0.0;
		produto = new StringBufferModelo("", 20);
        dataProd = new DataModelo();
        status = false;
    }

    public ProducaoModelo(int id, int qtdProd, double precoUni, double custoTotal,
   String produto,  String dataProd, boolean estado)
    {
        this.id = id;
        this.qtdProd = qtdProd;
        this.precoUni = precoUni;
        this.custoTotal = custoTotal;
		this.produto = new StringBufferModelo(produto, 20); 
		this.dataProd = new DataModelo(dataProd);
        this.status = estado;
    }

        public int getId()
        {
            return id;
        }

        public int getQtdProducao()
        {
            return qtdProd;
        }

        public String getProduto()
        {
            return produto.toStringEliminatingSpaces();
        }

        public Double getPrecoUni()
        {
            return precoUni;
        }

        public Double getCustoTotal()
        {
            return custoTotal;
        }

         public String getDataProd()
        {
            return dataProd.toString();
        }

        public boolean getStatus()
        {
            return status;
        }


        public void setId(int novoId)
        {
            id = novoId;
        }

        public void setQtdProd(int novoQtdProd)
        {
            qtdProd = novoQtdProd;
        }

        public void setProd(String novoProd)
        {
            produto = new StringBufferModelo(novoProd,50);
        }

        public void setDataProd(String novaDataEntrada)
        {
            dataProd = new DataModelo( novaDataEntrada );
        }

        public void setPrecoUn(double novoPrecoU)
        {
            precoUni = novoPrecoU;
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
            String str = "Dados de Producao\n\n";

            str += "Id: " + getId() + "\n";
            str += "Quantidade Produzida: " + getQtdProducao() + "\n";
            str += "Produto: " + getProduto() + "\n";
            str += "Data de Producao: " + getDataProd() + "\n";
            str += "Preco Unitario: " + getPrecoUni() + "\n";
            str += "Custo Total: " + getCustoTotal() + "\n";
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
            stream.writeInt(qtdProd);
            stream.writeDouble(precoUni);
            stream.writeDouble(custoTotal);
			produto.write(stream); 
			dataProd.write(stream);
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
            qtdProd = stream.readInt();
            precoUni = stream.readDouble();
            custoTotal = stream.readDouble();
            produto.read(stream); 
            dataProd.read(stream);
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
        ProducaoFile file = new ProducaoFile();
        file.eliminarDados(this);
    }
    
    public void salvar()
    {
        ProducaoFile file = new ProducaoFile();
        file.salvarDados(this);
    }

    public void editarDados()
    {
        ProducaoFile file = new ProducaoFile();
        file.alterarDados(this);
    }
}