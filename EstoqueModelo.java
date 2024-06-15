/*------------------------------------
Tema: Gestão de uma Padaria
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: EstoqueModelo.java
Data: 13.06.2024
--------------------------------------*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;


public class EstoqueModelo implements RegistGeneric
{
    int id, nivelMinimo, nivelAtual;
    StringBufferModelo ingrediente, unidadeMedida, produto;
    Double precoUnit, precoTotal; //usar apenas nos produtos acabados
    DataModelo dataEntradaEstoque; //apenas em produtos acabados


    public EstoqueModelo()
    {
        id = 0;
        nivelMinimo = 1;
        nivelAtual = 0;
        precoUnit = 0.0;
        precoTotal = 0.0;

		ingrediente = new StringBufferModelo("", 50); 
		unidadeMedida = new StringBufferModelo("", 20);
        produto = new StringBufferModelo("", 50); 
		dataEntradaEstoque = new DataModelo();
    }

    public EstoqueModelo(int id, int nivelMinimo, int nivelAtual, double precoUnit, double precoTotal, String ingrediente, String unidadeMedida,
   String produto, String dataEntradaEstoque)
    {
        this.id = id;
        this.nivelMinimo = nivelMinimo;
        this.nivelAtual = nivelAtual;
        this.precoUnit = precoUnit;
        this.precoTotal = precoTotal;
		this.ingrediente = new StringBufferModelo(ingrediente, 50); 
		this.unidadeMedida = new StringBufferModelo(unidadeMedida, 20);
        this.produto = new StringBufferModelo(produto, 50); 
		this.dataEntradaEstoque = new DataModelo(dataEntradaEstoque);
    }

        public int getId()
        {
            return id;
        }

        public int getNivelMinimo()
        {
            return nivelMinimo;
        }

        public int getNivelAtual()
        {
            return nivelAtual;
        }

        public String getIngrediente()
        {
            return ingrediente.toStringEliminatingSpaces();
        }

        public String getUnidadeMedida()
        {
            return unidadeMedida.toStringEliminatingSpaces();

        }

        public Double getPrecoUnit()
        {
            return precoUnit;
        }

        public Double getPrecoTotal()
        {
            return precoTotal;
        }

        public String getProduto()
        {
            return produto.toStringEliminatingSpaces();

        }

        public String getDataEntradaEstoque()
        {
            return dataEntradaEstoque.toString();
        }

        
        public void setId(int novoId)
        {
            id = novoId;
        }

        public void setNivelMinimo(int novoNivelMinimo)
        {
            nivelMinimo = novoNivelMinimo;
        }

        public void setNivelAtual(int novoNivelAtual)
        {
            nivelAtual = novoNivelAtual;
        }

        public void setIngrediente(String novoIngrediente)
        {
            ingrediente = new StringBufferModelo(novoIngrediente, 50);
        }

        public void setUnidadeMedida(String novoUnidadeMedida)
        {
            unidadeMedida = new StringBufferModelo(novoUnidadeMedida, 20);

        }

        public void setDataEntradaEstoque(String novaDataEntradaEstoque)
        {
            dataEntradaEstoque = new DataModelo( novaDataEntradaEstoque );
        }

         public void setProduto(String novoProduto)
        {
            produto = new StringBufferModelo(novoProduto, 20);

        }
       
        public void setPrecoUnit(double novoPrecoUnit)
        {
            precoUnit = novoPrecoUnit;
        }

        public void setPrecoTotal(double novoPrecoTotal)
        {
            precoTotal = novoPrecoTotal;
        }

    
        public String toString()
        {
            String str = "Dados das Entradas Modelo\n\n";

            str += "Id: " + getId() + "\n";
            str += "Ingrediente: " + getIngrediente() + "\n";
            str += "Produto: " + getProduto() + "\n";
            str += "Unidade de Medida: " + getUnidadeMedida() + "\n";
            str += "Nivel Minimo: " + getNivelMinimo() + "\n";
            str += "Nivel Atual: " + getNivelAtual() + "\n";
            str += "Data de Entrada em Estoque: " + getDataEntradaEstoque() + "\n";
            str += "Preco unitario: " + getPrecoUnit() + "\n";
            str += "Preco total: " + getPrecoTotal() + "\n";
            return str;
        }

        public long sizeof()
	    {
            
            try
            {
                return 4*2+8*2+50*2+8*2+24*3; // 212 bytes
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
            stream.writeInt(nivelMinimo);
            stream.writeInt(nivelAtual);
            stream.writeDouble(precoUnit);
            stream.writeDouble(precoTotal);
			ingrediente.write(stream); 
            unidadeMedida.write(stream);
            produto.write(stream); 
			dataEntradaEstoque.write(stream);
			
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
            nivelMinimo = stream.readInt();
            nivelAtual = stream.readInt();
            precoUnit = stream.readDouble();
            precoTotal = stream.readDouble();
			ingrediente.read(stream); 
            unidadeMedida.read(stream); 
            produto.read(stream);
            dataEntradaEstoque.read(stream);		
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Falha ao tentar Ler no Ficheiro");
		}
    }

    public void actualizar()
    {
        EstoqueFile file = new EstoqueFile();
        file.adicionarDados(this);
    }

    public void salvar()
    {
        EstoqueFile file = new EstoqueFile();
        file.salvarDados(this);
    }
}