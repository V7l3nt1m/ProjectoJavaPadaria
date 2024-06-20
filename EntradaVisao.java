/*------------------------------------
Tema: Gestão de uma Padaria
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: EntradaVisão.java
Data: 29.05.2024
--------------------------------------*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.File;

public class EntradaVisao extends JFrame
{
    private PainelNorte painelNorte;
    private PainelSul painelSul;
    private PainelCentro painelCentro;
    boolean editar;

    public EntradaVisao(boolean alterar, EntradaModelo modelo)
    {
        super("Registrar Entrada");
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
        private JComboBox unidadeMedida;
        private JTextField qtdEntrada, custoUnit, custoTotal, idJTF, idEstoque;

        private JComboBoxPersonal ingrediente, nivelMinimoJCB;
        private JComboBoxTabela2_Tabela3 ingNivelMinimo, unidadeMat;

        private JLabel lblIng, lblUnid, lblQtdEn, lblDataEnt, lblDataVal, lblCustou, lblCustoTot;
        private JPanel painelData, painelData2;
        private JTextFieldData dataEntrada,dataValidade;

        public PainelCentro()
        {   
            setLayout(new GridLayout(7,2));  
            ingNivelMinimo = new JComboBoxTabela2_Tabela3("MateriaPrima.tab","NivelMinimo.tab");
            unidadeMat = new JComboBoxTabela2_Tabela3("MateriaPrima.tab","UnidadeMedida.tab");

            lblIng = new JLabel("Ingrediente");
            ingrediente = ingNivelMinimo.getComboBoxFather();
            nivelMinimoJCB = ingNivelMinimo.getComboBoxSun();

			idJTF = new JTextField();
            EntradaFile entradafile = new EntradaFile();
			idJTF.setText( "" + entradafile.getProximoCodigo() );

            idEstoque = new JTextField();
            EstoqueFile entradafile2 = new EstoqueFile();
			idEstoque.setText( "" + entradafile2.getProximoCodigo() );


            lblUnid = new JLabel("Unidade de Medida");
            ingrediente = unidadeMat.getComboBoxFather();
            unidadeMedida = unidadeMat.getComboBoxSun();

            lblQtdEn = new JLabel("Quantidade");
            qtdEntrada = new JTextField();

            lblDataEnt = new JLabel("Data de Entrada");

            painelData = new JPanel( new GridLayout(1, 1) );
            dataEntrada = new JTextFieldData("Data?");
            painelData.add( dataEntrada.getDTestField() );
			painelData.add( dataEntrada.getDButton() );

            lblDataVal = new JLabel("Data de Validade");

            painelData2 = new JPanel( new GridLayout(1, 1) );
            dataValidade = new JTextFieldData("Data?");
            painelData2.add( dataValidade.getDTestField() );
			painelData2.add( dataValidade.getDButton() );

            lblCustou = new JLabel("Custo/unidade");
            custoUnit = new JTextField("0");

            lblCustoTot = new JLabel("Custo Total");
            custoTotal = new JTextField("0");
            
            add(lblIng);
            add(ingrediente);

            add(lblUnid);
            add(unidadeMedida);

            add(lblQtdEn);
            add(qtdEntrada);

            add(lblDataEnt);
            add(painelData);

            add(lblDataVal);
            add(painelData2);

            add(lblCustou);
            add(custoUnit);

            add(lblCustoTot);
            add(custoTotal);

            qtdEntrada.addKeyListener(this);
            custoUnit.addKeyListener(this);
            custoTotal.addKeyListener(this);

        }

        public PainelCentro(EntradaModelo modelo)
        { 
            setLayout(new GridLayout(7,2));  
            lblIng = new JLabel("Ingrediente");
            unidadeMat = new JComboBoxTabela2_Tabela3("MateriaPrima.tab","UnidadeMedida.tab");
            ingrediente = unidadeMat.getComboBoxFather();
            ingrediente.setSelectedItem(modelo.getIngrediente());

			idJTF = new JTextField();
            EntradaFile entradafile = new EntradaFile();
			idJTF.setText( "" + modelo.getId());

            lblUnid = new JLabel("Unidade de Medida");
            unidadeMedida = unidadeMat.getComboBoxSun();
            unidadeMedida.setSelectedItem(modelo.getUnidadeMedida());

            lblQtdEn = new JLabel("Quantidade");
            qtdEntrada = new JTextField();
            qtdEntrada.setText(""+modelo.getQtdEntrada());

            lblDataEnt = new JLabel("Data de Entrada");

            painelData = new JPanel( new GridLayout(1, 1) );
            dataEntrada = new JTextFieldData("Data?");
            dataEntrada.getDTestField().setText(modelo.getDataEntrada());

            painelData.add( dataEntrada.getDTestField() );
			painelData.add( dataEntrada.getDButton() );

            lblDataVal = new JLabel("Data de Validade");

            painelData2 = new JPanel( new GridLayout(1, 1) );
            dataValidade = new JTextFieldData("Data?");
            dataValidade.getDTestField().setText(modelo.getDataValidade());

            painelData2.add( dataValidade.getDTestField() );
			painelData2.add( dataValidade.getDButton() );

            lblCustou = new JLabel("Custo/unidade");
            custoUnit = new JTextField("0");
            custoUnit.setText(""+modelo.getCustoUnit());

            lblCustoTot = new JLabel("Custo Total");
            custoTotal = new JTextField("0");
            custoTotal.setText(""+modelo.getCustoTotal());
            
            add(lblIng);
            add(ingrediente);

            add(lblUnid);
            add(unidadeMedida);

            add(lblQtdEn);
            add(qtdEntrada);

            add(lblDataEnt);
            add(painelData);

            add(lblDataVal);
            add(painelData2);

            add(lblCustou);
            add(custoUnit);

            add(lblCustoTot);
            add(custoTotal);

            qtdEntrada.addKeyListener(this);
            custoUnit.addKeyListener(this);
            custoTotal.addKeyListener(this);

        }

        public void keyTyped(KeyEvent evt)
        {
           
        }

        public void keyPressed(KeyEvent evt)
        {
           
        }
        public void keyReleased(KeyEvent evt)
        {
            
            if((evt.getSource() == custoUnit || evt.getSource() == qtdEntrada) && !qtdEntrada.getText().isEmpty() && getCustoUnit() != 0.0 && getQtdEntrada() != 0.0)
            {
               custoTotal.setEnabled(false); 
               custoTotal.setText(String.valueOf(getQtdEntrada() * getCustoUnit()));
            }
            else if(evt.getSource() == custoTotal && !qtdEntrada.getText().isEmpty() && getCustoTotal() != 0.0)
            {
                custoUnit.setEnabled(false);
                custoUnit.setText(String.valueOf(getCustoTotal() / getQtdEntrada()));
            }
            else
            {
                custoUnit.setEnabled(true); 
                custoTotal.setEnabled(true); 

            }
            
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

        public String getIngrediente()
        {
            return String.valueOf(ingrediente.getSelectedItem());
        }

        public String getUnidadeMedida()
        {
            return String.valueOf(unidadeMedida.getSelectedItem());

        }

        public String getDataEntrada()
        {
            return dataEntrada.getDTestField().getText();
        }

        public String getDataValidade()
        {
            return dataValidade.getDTestField().getText();
        }

        public Double getCustoUnit()
        {
            return Double.parseDouble(custoUnit.getText().trim());
        }

        public Double getCustoTotal()
        {
            return Double.parseDouble(custoTotal.getText().trim());
        }

        public String getNivelMinimo()
        {
            return String.valueOf(nivelMinimoJCB.getSelectedItem());
        }

        public void setEstoqueId(int id)
        {
            idEstoque.setText(""+id);
        }

        public void setId(int id)
        {
            idJTF.setText(""+id);
        }

        public void setQtdEntrada(int qtdEntra)
        {
            qtdEntrada.setText("" + qtdEntra);
        }

        public void setIngrediente(String ingre)
        {
            ingrediente.setSelectedItem(ingre);
        }

        public void setUnidadeMedida(String unidade)
        {
            unidadeMedida.setSelectedItem(unidade);
        }

        public void setDataEntrada(String dataEntra)
        {
            dataEntrada.getDTestField().setText(dataEntra);
        }

        public void setDataValidade(String dataVal)
        {
            dataValidade.getDTestField().setText(dataVal);
        }

        public void setCustoUnit(double custoUnita)
        {
            custoUnit.setText("" + custoUnita);
        }

        public void setCustoTotal(double custoTot)
        {
            custoTotal.setText("" + custoTot);
        }

        

        public void salvar()
        {    
            
            EntradaModelo modelo = new EntradaModelo(getId(), getQtdEntrada(),getCustoUnit(),
            getCustoTotal(),
            getIngrediente(),
            getUnidadeMedida(),
            getDataEntrada(),
            getDataValidade(), true);
            
            modelo.salvar();
            
            EstoqueModelo dados = EstoqueFile.pesquisarIngredienteEstoque(getIngrediente());
            File arquivoEstoque = new File("EstoqueFile.dat");

            
           
           if((arquivoEstoque.exists()) && (dados.getIngrediente()).equals(getIngrediente()))
            {
                int novoNivel = dados.getNivelAtual() + getQtdEntrada();
                dados.setNivelAtual(novoNivel);
                dados.setDataEntradaEstoque(getDataEntrada());
                JOptionPane.showMessageDialog(null,""+dados.toString());
                dados.actualizar();
            }
            else if((arquivoEstoque.exists()) && ! (dados.getIngrediente()).equals(getIngrediente()))
            {
                 EstoqueModelo novoEstoque = new EstoqueModelo(
                getId2(), 
            (Integer.parseInt(getNivelMinimo())), 
                (int)getQtdEntrada(), 
                0.0,
                0.0, 
                getIngrediente(), 
                getUnidadeMedida(), 
                getDataEntrada(), true);

                novoEstoque.salvar();
            }
            else
            {
                EstoqueModelo novoEstoque = new EstoqueModelo(
                getId2(), 
            (Integer.parseInt(getNivelMinimo())), 
                (int)getQtdEntrada(), 
                0.0,
                0.0, 
                getIngrediente(), 
                getUnidadeMedida(), 
                getDataEntrada(), true);

                novoEstoque.salvar();
            }
            
           dispose();
        }

        public void alterar()
        {
            EntradaModelo modelo = new EntradaModelo(getId(), getQtdEntrada(),getCustoUnit(),
            getCustoTotal(),
            getIngrediente(),
            getUnidadeMedida(),
            getDataEntrada(),
            getDataValidade(),true);

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