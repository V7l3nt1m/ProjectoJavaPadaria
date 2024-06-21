/*------------------------------------
Tema: Gestão de uma Padaria
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: VendaVisao.java
Data: 15.06.2024
--------------------------------------*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.File;

public class VendaVisao extends JFrame
{
    private PainelNorte painelNorte;
    private PainelSul painelSul;
    private PainelCentro painelCentro;
    boolean editar;

    public VendaVisao(boolean alterar, VendaModelo modelo)
    {
        super("Registrar Venda");
        definirTema();
        setLayout(new BorderLayout());
        editar = alterar;

        getContentPane().add(painelNorte = new PainelNorte(), BorderLayout.NORTH);

        if (!alterar)
            getContentPane().add(painelCentro = new PainelCentro(), BorderLayout.CENTER);
       else
            getContentPane().add(painelCentro = new PainelCentro(modelo), BorderLayout.CENTER);


        getContentPane().add(painelSul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        //setSize(490,500);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public class PainelNorte extends JPanel
    {
        private JLabel titulo;
        private JSeparator separator;


        public PainelNorte()
        {     
            separator = new JSeparator(SwingConstants.HORIZONTAL);
            titulo = new JLabel("Tela de Venda");
            titulo.setFont(new Font("Monospaced", Font.BOLD,40));
            setBackground(Color.decode("#CBCBCB"));
            add(titulo);   
        }
    }

    public class PainelCentro extends JPanel implements KeyListener,ActionListener
    {
        private JComboBox produtoJCB, tipoPagamentoJCB;
        private JTextField qtdEntrada,idEstoque, precoUnit, precoTotal, clienteJTF, idJTF;
        private JTextFieldData dataVenda;

        private JLabel lblProd, lblQtdEn, lblDataVend, lblPrecUni, lblPrecoTot,lblCliente,lblTipoPagamento;
        private JPanel painelData, painelProdutos;
        private String tipoPag [] = {"Cash","Cartao"};
        private StringVector vectorProdutos;
        private String [][] produtosValores;
        private JTextField [] inputs;



        public PainelCentro()
        {   
            setLayout(new GridLayout(7,2));  
            StringVector produtosEmEstoque = EstoqueFile.getAllProdutosEstoque();
            ProducaoFile fileProf = new ProducaoFile();

			idJTF = new JTextField();
            VendaFile vendaFile = new VendaFile();
			idJTF.setText( "" + vendaFile.getProximoCodigo() );

            idEstoque = new JTextField();
            EstoqueFile entradafile2 = new EstoqueFile();
			idEstoque.setText( "" + entradafile2.getProximoCodigo() );
            
            lblProd = new JLabel("Produtos");
            add(lblProd);
            painelProdutos = new JPanel();
            EstoqueFile estoque = new EstoqueFile();
            vectorProdutos = estoque.getAllProdutosEstoque();

            inputs = new JTextField[vectorProdutos.size()];

            produtosValores = new String[vectorProdutos.size()][2];
            for(int i = 0; i<vectorProdutos.size(); i++)
            {
                ProducaoModelo modeloProd1 = fileProf.pesquisarEntradaPorProduto(""+vectorProdutos.get(i));
               inputs[i] = new JTextField(5);
                inputs[i].addKeyListener(this);
               inputs[i].setText(""+0);
               painelProdutos.add(new JLabel(""+vectorProdutos.get(i) + " ("+modeloProd1.getPrecoUni() +" kz) "));
               painelProdutos.add(inputs[i]);              
            }
            add(painelProdutos);

            lblDataVend = new JLabel("Data da Venda");

            painelData = new JPanel( new GridLayout(1, 1) );
            dataVenda = new JTextFieldData("Data?");
            painelData.add( dataVenda.getDTestField() );
			painelData.add( dataVenda.getDButton() );

            lblPrecoTot = new JLabel("Preço Total");
            precoTotal = new JTextField("0");
            precoTotal.setFocusable(false);

            lblCliente = new JLabel("Nome cliente");
            clienteJTF = new JTextField();

            lblTipoPagamento = new JLabel("Tipo de Pagamento");
            tipoPagamentoJCB = new JComboBox(tipoPag);

            add(lblDataVend);
            add(painelData);

            add(lblPrecoTot);
            add(precoTotal);

            add(lblCliente);
            add(clienteJTF);

            add(lblTipoPagamento);
            add(tipoPagamentoJCB);

            precoTotal.addKeyListener(this);
        }

        public PainelCentro(VendaModelo modelo)
        { 
            setLayout(new GridLayout(7,2));  
            idJTF = new JTextField();
            StringVector produtosEmEstoque = EstoqueFile.getAllProdutosEstoque();
            VendaModelo vendaFile = new VendaModelo();
            Double produtoGetPreco = ProducaoFile.getPrecoUnit(modelo.getProduto());
			idJTF.setText( "" + modelo.getId() );

            lblProd = new JLabel("Produtos");
            produtoJCB = new JComboBox(produtosEmEstoque);
            produtoJCB.setSelectedItem(modelo.getProduto());

            lblQtdEn = new JLabel("Quantidade");
            qtdEntrada = new JTextField();
            qtdEntrada.setText(""+modelo.getQtdEntrada());

            lblDataVend = new JLabel("Data da Venda");

            painelData = new JPanel( new GridLayout(1, 1) );
            dataVenda = new JTextFieldData("Data?");
            painelData.add( dataVenda.getDTestField() );
			painelData.add( dataVenda.getDButton() );
            dataVenda.getDTestField().setText(modelo.getDataVenda());

            lblPrecUni = new JLabel("Preço/unidade");
            precoUnit = new JTextField();
            precoUnit.setText(""+produtoGetPreco);
            precoUnit.setFocusable(false);

            lblPrecoTot = new JLabel("Preço Total");
            precoTotal = new JTextField("0");
            precoTotal.setText(""+modelo.getPrecoTotal());
            precoTotal.setFocusable(false);

            lblCliente = new JLabel("Nome cliente");
            clienteJTF = new JTextField(""+modelo.getClienteNome());

            lblTipoPagamento = new JLabel("Tipo de Pagamento");
            tipoPagamentoJCB = new JComboBox(tipoPag);
            tipoPagamentoJCB.setSelectedItem(modelo.getTipoPagamento());
            
            add(lblProd);
            add(produtoJCB);

            add(lblQtdEn);
            add(qtdEntrada);

            add(lblDataVend);
            add(painelData);

            add(lblPrecUni);
            add(precoUnit);

            add(lblPrecoTot);
            add(precoTotal);

            add(lblCliente);
            add(clienteJTF);

            add(lblTipoPagamento);
            add(tipoPagamentoJCB);

            qtdEntrada.addKeyListener(this);
            precoTotal.addKeyListener(this);
            produtoJCB.addActionListener(this);
        }

        public void actionPerformed(ActionEvent evt)
        {
            
        }

        public int getId2()
        {
            return Integer.parseInt( idEstoque.getText().trim());
        }

        public int getId()
        {
            return Integer.parseInt( idJTF.getText().trim());
        }

        public int getQtdEntrada()
        {
            return Integer.parseInt( qtdEntrada.getText().trim() );
        }


        public String getProduto()
        {
            if(produtoJCB != null)
            {
                return String.valueOf(produtoJCB.getSelectedItem());
            }
            else
                return "1";
        }

        public String getDataVenda()
        {
            return dataVenda.getDTestField().getText();
        }

        public Double getPrecoUni()
        {
            return Double.parseDouble(precoUnit.getText().trim());
        }

        public Double getPrecoTotal()
        {
            return Double.parseDouble(precoTotal.getText().trim());
        }

        public String getClienteNome()
        {
            return clienteJTF.getText().trim();
        }

        public String getTipoPagamento()
        {
            return String.valueOf(tipoPagamentoJCB.getSelectedItem());
        }

        public void setId(int id)
        {
            idJTF.setText(""+id);
        }

        public void setEstoqueId(int id)
        {
            idEstoque.setText(""+id);
        }

        public void setQtdEntrada(int qtdEntra)
        {
            qtdEntrada.setText("" + qtdEntra);
        }

        public void setProd(String produto)
        {
            produtoJCB.setSelectedItem(produto);
        }

        public void setDataVenda(String dataVend)
        {
            dataVenda.getDTestField().setText(dataVend);
        }

        public void setPrecoTot(double custoTot)
        {
            precoTotal.setText("" + custoTot);
        }

        public void setClienteNome(String clienteN)
        {
            clienteJTF.setText(clienteN);
        }

        public void setTipoPagamento(String tipoPaga)
        {
            tipoPagamentoJCB.setSelectedItem(tipoPaga);
        }

        public String [][] getValoresProd()
        {
            String [][] valores = new String[vectorProdutos.size()][2];

            for(int i = 0; i<vectorProdutos.size(); i++)
            {
                valores[i][0] = ""+vectorProdutos.get(i);

                if(inputs[i].getText().trim().equals(""))
                    valores[i][1] = "0";
                else
                    valores[i][1] = ""+inputs[i].getText().trim();
            }

            return valores;
        }

        public void setValoresProd(String [][] valores)
        {
            for (int i = 0; i < vectorProdutos.size(); i++)
            {
                String produtos = valores[i][0];
                String valor = valores[i][1];
                // Procura o índice do ingrediente no vectorIngredientes
                int index = vectorProdutos.indexOf(produtos);
                inputs[index].setText(valor);
            }
        }

        public void keyTyped(KeyEvent evt)
        {
           
        }

        public void keyPressed(KeyEvent evt)
        {
           
        }
        public void keyReleased(KeyEvent evt)
        {
            EstoqueFile estoque = new EstoqueFile();
            vectorProdutos = estoque.getAllProdutosEstoque();

            for(int k = 0; k<vectorProdutos.size(); k++)
            {
                if(evt.getSource() == inputs[k])
                {
                    double soma=0.0;
                    double custoTot=0.0;
                    ProducaoFile producaoFile = new ProducaoFile();

                    String [][] produtos = getValoresProd();
                    for(int i =0; i<produtos.length; i++)
                    {
                        soma+=(Double.parseDouble(produtos[i][1])*producaoFile.getPrecoUnit(""+produtos[i][0]));
                    }

                    precoTotal.setText(""+soma);
                }
            }
             
        }

        public void salvar()
        {   
            EstoqueFile fileEstoque = new EstoqueFile();
            int count = 0; // Contador para verificar quantos ingredientes têm níveis adequados
            String[][] produtosComprados = getValoresProd();

            // Primeiro, verifica se todos os ingredientes têm quantidade suficiente
            for (int g = 0; g < produtosComprados.length; g++) {
                EstoqueModelo modeloEstoque = fileEstoque.getProdutoPesquisa(produtosComprados[g][0]);
                int novoNivel = modeloEstoque.getNivelAtual() - Integer.parseInt(produtosComprados[g][1]);
                
                if (novoNivel < 0) {
                    JOptionPane.showMessageDialog(null, "O Produto: " + produtosComprados[g][0] + " não possui essa quantidade em estoque.", "Quantidade de Produto inválida", JOptionPane.ERROR_MESSAGE);
                    return; // Interrompe a execução se encontrar um ingrediente com quantidade insuficiente
                } else {
                    count++; // Incrementa o contador de ingredientes com quantidade suficiente
                }
            }

            // Se todos os ingredientes têm quantidade suficiente, podemos salvar os dados
            if (count == produtosComprados.length) 
            {
                for (int g = 0; g < produtosComprados.length; g++) 
                {
                    EstoqueModelo modeloEstoque2 = fileEstoque.getProdutoPesquisa(produtosComprados[g][0]);
                    if (modeloEstoque2.getProdutoAcabado().equals(produtosComprados[g][0])) 
                    {
                        int novoNivelEstoque2 = modeloEstoque2.getNivelAtual() - Integer.parseInt(produtosComprados[g][1]);
                        modeloEstoque2.setNivelAtual(novoNivelEstoque2);
                        modeloEstoque2.actualizar();
                    }
                    VendaModelo modelo = new VendaModelo(getId(), Integer.parseInt(produtosComprados[g][1]),
                    getPrecoTotal(),
                    produtosComprados[g][0],
                    getClienteNome(),
                    getTipoPagamento(),
                    getDataVenda(), true);

                    JOptionPane.showMessageDialog(null, modelo.toString());
                    modelo.salvar();
                    dispose();
                    setId(getId()+1);

                }
            }
        }

        public void alterar()
        {
            VendaModelo modelo = new VendaModelo(getId(), getQtdEntrada(),
            getPrecoTotal(),
            getProduto(),
            getClienteNome(),
            getTipoPagamento(),
            getDataVenda(), true);

            modelo.editarDados();
            dispose();
        }

    }


    public class PainelSul extends JPanel implements ActionListener
    {

        private JButton salvarJB, cancelarJB;
		
		public PainelSul()
		{
			add( salvarJB = new JButton("Salvar") );
			add( cancelarJB = new JButton("Cancelar") );

            salvarJB.addActionListener(this);
            cancelarJB.addActionListener(this);
		}

            public void actionPerformed(ActionEvent evt)
            {
                 if(evt.getSource() == salvarJB)
                {
                    if(editar)
                        painelCentro.alterar();
                    else
                        painelCentro.salvar();
                }
                else
                    dispose();
            }
    }

    public void definirTema() 
	 {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }
    }
}

//e visualizar numa textarea os dados que veem numa factura
//treinar adicionar um campo, e fazer pesquisa. Deixar um layout pronto para pesquisar
//se encontrar mais de 2 apenas retornar 1 (isso nas pesquisas)

//Analise 5 valores
//Entidades a fazer crud 2 valores (principal a funcionar + 1 valor)
//Apresentacao e login 1 valor
//imagem + 1 valor
//outros 10 defesa (alteracao e defesa)
//incluir 1 item no menu principal (1 ponto)
//incluir campos + 1 ponto
//se salvar mais 2 pontos
//pesquisa + 6 pontos se funcionar
//so ver se o campo esta vazio unica validacao
//adicionar tudo na analise
//iniciativa cada, 1 ponto
//colocar a unidade medida como tabela que depende da Materia prima