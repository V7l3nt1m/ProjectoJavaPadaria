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

public class EntradaVisao extends JFrame
{
    private PainelNorte painelNorte;
    private PainelSul painelSul;
    private PainelCentro painelCentro;

    public EntradaVisao()
    {
        super("Registrar Entrada");
        definirTema();
        setLayout(new BorderLayout());

        getContentPane().add(painelNorte = new PainelNorte(), BorderLayout.NORTH);
        getContentPane().add(painelCentro = new PainelCentro(), BorderLayout.CENTER);
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
        private JComboBox ingrediente, unidadeMedida;
        private JTextField qtdEntrada, custoUnit, custoTotal, idJTF;
        private JButton dataEntradaBtn, dataValidadeBtn;
        private JLabel lblIng, lblUnid, lblQtdEn, lblDataEnt, lblDataVal, lblCustou, lblCustoTot;
        private JPanel painelData, painelData2;
        private JTextFieldData dataEntrada,dataValidade;

        public PainelCentro()
        {          
            setLayout(new GridLayout(7,2));  
            lblIng = new JLabel("Ingrediente");
            ingrediente = UInterfaceBox.createJComboBoxPersonalTab2("MateriaPrima.tab");

			idJTF = new JTextField();
            EntradaFile entradafile = new EntradaFile();
			idJTF.setText( "" + entradafile.getProximoCodigo() );


            lblUnid = new JLabel("Unidade de Medida");
            unidadeMedida = UInterfaceBox.createJComboBoxPersonalTab2("UnidadeMedida.tab");

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

//setters
/*
        public int setQtdEntrada(int qtdEntrada)
        {
            qtdEntrada.setText(qtdEntrada);
        }

        public String setIngrediente(String ingrediente)
        {
            ingrediente.setSelectedItem(ingrediente);
        }

        public String setUnidadeMedida(String unidade)
        {
            unidadeMedida.setSelectedItem(unidade);
        }

        public String setDataEntrada(String dataEntra)
        {
            // dataEntrada.setText
            return dataEntrada.getDTestField().getText();
        }

        public String setDataValidade()
        {
            //dataValidade.setText();
            return dataValidade.getDTestField().getText();
        }

        public Double setCustoUnit(double custoUnita)
        {
            custoUnit.setText(custoUnit);
        }

        public Double setCustoTotal(double custoTot)
        {
            custoTotal.setText(custoTot);
        }
        */

        public void salvar()
        {
            EntradaModelo modelo = new EntradaModelo(getId(), getQtdEntrada(),getCustoUnit(),
            getCustoTotal(),
            getIngrediente(),
            getUnidadeMedida(),
            getDataEntrada(),
            getDataValidade());
            modelo.salvar();
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
                if(evt.getSource() == cancelarJB)
                {
                    dispose();
                }
                else if(evt.getSource() == salvarJB)
                {
                    painelCentro.salvar();
                }
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

    public static void main(String args[])
    {
        Vector_Tabelas.inic();
        new EntradaVisao();
    }
}