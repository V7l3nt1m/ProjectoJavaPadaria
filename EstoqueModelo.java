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
    StringBufferModelo ingrediente, unidadeMedida,produtoAcabado;
    Double precoUnit, precoTotal; 
    DataModelo dataEntradaEstoque; 
    boolean status;


    public EstoqueModelo()
    {
        id = 0;
        nivelMinimo = 1;
        nivelAtual = 0;
        precoUnit = 0.0;
        precoTotal = 0.0;
        status = false;


		ingrediente = new StringBufferModelo("", 50); 
		unidadeMedida = new StringBufferModelo("", 20);
        produtoAcabado = new StringBufferModelo("", 10); 
		dataEntradaEstoque = new DataModelo();
    }

    public EstoqueModelo(int id, int nivelMinimo, int nivelAtual, double precoUnit, double precoTotal, String ingrediente, String unidadeMedida, String produtoAcabado, String dataEntradaEstoque, boolean estado)
    {
        this.id = id;
        this.nivelMinimo = nivelMinimo;
        this.nivelAtual = nivelAtual;
        this.precoUnit = precoUnit;
        this.precoTotal = precoTotal;
		this.ingrediente = new StringBufferModelo(ingrediente, 50); 
		this.unidadeMedida = new StringBufferModelo(unidadeMedida, 20);
		this.produtoAcabado = new StringBufferModelo(produtoAcabado, 10);
		this.dataEntradaEstoque = new DataModelo(dataEntradaEstoque);
        this.status = estado;
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

        public String getProdutoAcabado()
        {
            return produtoAcabado.toStringEliminatingSpaces();
        }

        public Double getPrecoUnit()
        {
            return precoUnit;
        }

        public Double getPrecoTotal()
        {
            return precoTotal;
        }
        public String getDataEntradaEstoque()
        {
            return dataEntradaEstoque.toString();
        }



        public boolean getStatus()
        {
            return status;
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

        public void setProdutoAcabado(String novoProdutoAcabado)
        {
            produtoAcabado = new StringBufferModelo(novoProdutoAcabado, 10);
        }

        public void setUnidadeMedida(String novoUnidadeMedida)
        {
            unidadeMedida = new StringBufferModelo(novoUnidadeMedida, 20);

        }

        public void setDataEntradaEstoque(String novaDataEntradaEstoque)
        {
            dataEntradaEstoque = new DataModelo( novaDataEntradaEstoque );
        }
       
        public void setPrecoUnit(double novoPrecoUnit)
        {
            precoUnit = novoPrecoUnit;
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
            String str = "Dados do Estoque Modelo\n\n";

            str += "Id: " + getId() + "\n";
            str += "Ingrediente: " + getIngrediente() + "\n";
            str += "Produto Acabado: " + getProdutoAcabado() + "\n";
            str += "Unidade de Medida: " + getUnidadeMedida() + "\n";
            str += "Nivel Minimo: " + getNivelMinimo() + "\n";
            str += "Nivel Atual: " + getNivelAtual() + "\n";
            str += "Data de Entrada em Estoque: " + getDataEntradaEstoque() + "\n";
            str += "Preco unitario: " + getPrecoUnit() + "\n";
            str += "Preco total: " + getPrecoTotal() + "\n";
            str += "Estado: " + getStatus() + "\n"; 
            return str;
        }

        public long sizeof()
	    {
            
            try
            {
                return 80*2 + 4*2 + 8*2 + 1 + 12;// 212 bytes
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
            produtoAcabado.write(stream);
			dataEntradaEstoque.write(stream);
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
            nivelMinimo = stream.readInt();
            nivelAtual = stream.readInt();
            precoUnit = stream.readDouble();
            precoTotal = stream.readDouble();
			ingrediente.read(stream); 
            unidadeMedida.read(stream); 
            produtoAcabado.read(stream); 
            dataEntradaEstoque.read(stream);	
            status = stream.readBoolean();	
	
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Falha ao tentar Ler no Ficheiro Estoque");
		}
    }

    public void eliminar()
    {
        EstoqueFile file = new EstoqueFile();
        file.eliminarDados(this);
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

    public void editarDados()
    {
        EstoqueFile file= new EstoqueFile();
        file.alterarDados(this);
    }
}