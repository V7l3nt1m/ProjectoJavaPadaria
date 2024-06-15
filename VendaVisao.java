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

        setSize(490,500);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public class PainelNorte extends JPanel
    {
        private JLabel lblIng;
        private ImageIcon ingIco, redimImage;
        private Image redimLogo,image; 


        public PainelNorte()
        {          
            ingIco = new ImageIcon("imagens/ingr2.jpg");
            
            lblIng = new JLabel();
            lblIng.setIcon(ingIco);
            add(lblIng);
        }
    }

    public class PainelCentro extends JPanel implements KeyListener
    {
        private JComboBox produtoJCB;
        private JTextField qtdEntrada,idEstoque, precoUnit, precoTotal, cliente, idJTF;
        private JTextFieldData dataVenda;

        private JLabel lblProd, lblQtdEn, lblDataVend, lblPrecUni, lblPrecoTot,lblCliente;
        private JPanel painelData;

        public PainelCentro()
        {   
            setLayout(new GridLayout(7,2));  

			idJTF = new JTextField();
            VendaFile vendaFile = new VendaFile();
			idJTF.setText( "" + vendaFile.getProximoCodigo() );

            idEstoque = new JTextField();
            EstoqueFile entradafile2 = new EstoqueFile();
			idEstoque.setText( "" + entradafile2.getProximoCodigo() );

            lblProd = new JLabel("Produtos");
            produtoJCB = UInterfaceBox.createJComboBoxPersonalTab2("Produto.tab");

            lblQtdEn = new JLabel("Quantidade");
            qtdEntrada = new JTextField();

            lblDataVend = new JLabel("Data da Venda");

            painelData = new JPanel( new GridLayout(1, 1) );
            dataVenda = new JTextFieldData("Data?");
            painelData.add( dataVenda.getDTestField() );
			painelData.add( dataVenda.getDButton() );

            ProducaoFile fileProf = new ProducaoFile();
            ProducaoModelo modeloProd = fileProf.pesquisarEntradaPorProduto(getProduto());

            lblPrecUni = new JLabel("Preço/unidade");
            precoUnit = new JTextField();
            precoUnit.setText(""+modeloProd.getPrecoUni());

            lblPrecoTot = new JLabel("Preço Total");
            precoTotal = new JTextField("0");
            precoTotal.setFocusable(false);

            
            add(lblProd);
            add(produtoJCB);

            add(lblQtdEn);
            add(qtdEntrada);

            add(painelData);

            add(lblDataVend);

            add(lblPrecUni);
            add(precoUnit);

            add(lblPrecoTot);
            add(precoTotal);

            qtdEntrada.addKeyListener(this);
            precoUnit.addKeyListener(this);
            precoTotal.addKeyListener(this);

        }

        public PainelCentro(VendaModelo modelo)
        { 
            idJTF = new JTextField();
            VendaModelo vendaFile = new VendaModelo();
			idJTF.setText( "" + modelo.getId() );

            lblProd = new JLabel("Produtos");
            produtoJCB = UInterfaceBox.createJComboBoxPersonalTab2("Produto.tab");
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
            precoUnit.setText(""+modelo.getPrecoUni());

            lblPrecoTot = new JLabel("Preço Total");
            precoTotal = new JTextField("0");
            precoTotal.setText(""+modelo.getPrecoTotal());
            precoTotal.setFocusable(false);
            
            add(lblProd);
            add(produtoJCB);

            add(lblQtdEn);
            add(qtdEntrada);

            add(painelData);

            add(lblDataVend);

            add(lblPrecUni);
            add(precoUnit);

            add(lblPrecoTot);
            add(precoTotal);

            qtdEntrada.addKeyListener(this);
            precoUnit.addKeyListener(this);
            precoTotal.addKeyListener(this);
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
            return String.valueOf(produtoJCB.getSelectedItem());
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


        public void setPrecoUni(double precoUnita)
        {
            precoUnit.setText("" + precoUnita);
        }

        public void setPrecoTot(double custoTot)
        {
            precoTotal.setText("" + custoTot);
        }


        public void keyTyped(KeyEvent evt)
        {
           
        }

        public void keyPressed(KeyEvent evt)
        {
           
        }
        public void keyReleased(KeyEvent evt)
        {
            
            if((evt.getSource() == precoUnit || evt.getSource() == qtdEntrada) && !qtdEntrada.getText().isEmpty() && (getPrecoUni() != 0.0) && getQtdEntrada() != 0.0)
            {
               precoTotal.setText(String.valueOf(getQtdEntrada() * getPrecoUni()));
            }
            
        }

        public void salvar()
        {    
            VendaModelo modelo = new VendaModelo(getId(), getQtdEntrada(),getPrecoUni(),
            getPrecoTotal(),
            getProduto(),
            getDataVenda(), true);
            
            modelo.salvar();
            
            EstoqueModelo dados = EstoqueFile.pesquisarIngredienteEstoque(getProduto());
            File arquivoEstoque = new File("EstoqueFile.dat");
           
           if((arquivoEstoque.exists()) && (dados.getIngrediente()).equals(getProduto()))
            {
                int novoNivel = dados.getNivelAtual() - getQtdEntrada();
                dados.setNivelAtual(novoNivel);
                dados.actualizar();
            }
            else
            {
                EstoqueModelo novoEstoque = new EstoqueModelo(
                getId2(), 
            1, 
                getQtdEntrada(), 
                getPrecoUni(),
                getPrecoTotal(), 
                getProduto(), 
                null, 
                getDataVenda());
                novoEstoque.salvar();
            }
            
           dispose();
        }

        public void alterar()
        {
            VendaModelo modelo = new VendaModelo(getId(), getQtdEntrada(),getPrecoUni(),
            getPrecoTotal(),
            getProduto(),
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