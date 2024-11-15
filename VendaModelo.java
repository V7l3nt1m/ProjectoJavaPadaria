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
    private StringBufferModelo produto, clienteNome, tipoPagamento;
    private Double precoTotal, valorPago, troco;
    private DataModelo dataVenda;
    private boolean status;

    public VendaModelo()
    {
        id = 0;
        qtdEntrada = 0;
        precoTotal = 0.0;
		produto = new StringBufferModelo("", 50);
        clienteNome = new StringBufferModelo("", 50);
        tipoPagamento = new StringBufferModelo("", 20);
        valorPago = 0.0;
        troco = 0.0;
        dataVenda = new DataModelo();
        status = false;
    }

    public VendaModelo(int id, int qtdEntrada, double precoTotal,
   String produto, String clienteNome, String tipoPagamento,double valorPago, double troco,String dataVenda, boolean estado)
    {
        this.id = id;
        this.qtdEntrada = qtdEntrada;
        this.precoTotal = precoTotal;
		this.produto = new StringBufferModelo(produto, 50); 
        this.clienteNome = new StringBufferModelo(clienteNome, 50);
        this.tipoPagamento = new StringBufferModelo(tipoPagamento, 20); 
        this.valorPago = valorPago;
        this.troco = troco;
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

        public Double getPrecoTotal()
        {
            return precoTotal;
        }

        public Double getValorPago()
        {
            return valorPago;
        }

        public Double getTroco()
        {
            return troco;
        }

         public String getDataVenda()
        {
            return dataVenda.toString();
        }

        public boolean getStatus()
        {
            return status;
        }

        public String getTipoPagamento()
        {
            return tipoPagamento.toStringEliminatingSpaces();
        }

        public String getClienteNome()
        {
             return clienteNome.toStringEliminatingSpaces();
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


        public void setPrecoTotal(double novoPrecoTotal)
        {
            precoTotal = novoPrecoTotal;
        }

         public void setValorPago(double novoValorPago)
        {
            valorPago = novoValorPago;
        }

         public void setTroco(double novoTroco)
        {
            troco = novoTroco;
        }

        public void setClienteNome(String novoClienteNome)
        {
            clienteNome = new StringBufferModelo(novoClienteNome,50);
        }
        
        public void setTipoPagamento(String novoTipoPagamento)
        {
            tipoPagamento = new StringBufferModelo(novoTipoPagamento,20);
        }

        public void setStatus(boolean new_status)
        {
            this.status = new_status;
        }

        public String toString()
        {
            String str = "Dados da Venda\n\n";

            str += "Id: " + getId() + "\n";
            str += "Quantidade: " + getQtdEntrada() + "\n";
            str += "Preco Total: " + getPrecoTotal() + "\n";
            str += "Produto: " + getProduto() + "\n";
            str += "Nome cliente: " + getClienteNome() + "\n"; 
            str += "Tipo Pagamento: " + getTipoPagamento() + "\n"; 
            str += "Valor Pago: " + getValorPago() + "\n"; 
            str += "Troco: " + getTroco() + "\n"; 
            str += "Data da compra: " + getDataVenda() + "\n";
            str += "Estado: " + getStatus() + "\n"; 
            return str;
        }

        public long sizeof()
	    {
            
            try
            {
                return 120*2 + 4*2 + 8*2 + 12 + 1;// 269 bytes
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
            stream.writeDouble(precoTotal);
			produto.write(stream); 
            clienteNome.write(stream);
            tipoPagamento.write(stream); 
            stream.writeDouble(valorPago);
            stream.writeDouble(troco);
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
            precoTotal = stream.readDouble();
            produto.read(stream);
            clienteNome.read(stream);
            tipoPagamento.read(stream); 
            valorPago = stream.readDouble();
            troco = stream.readDouble();
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