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

        //setSize(1600,300);
        pack();
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

    public class PainelCentro extends JPanel
    {
        private JComboBox ingrediente, unidadeMedida;
        private JTextField qtdEntrada, dataEntrada, dataValidade, custoUnit, custoTotal;
        private JButton dataEntradaBtn, dataValidadeBtn;
        private JLabel lblIng, lblUnid, lblQtdEn, lblDataEnt, lblDataVal, lblCustou, lblCustoTot;

        public PainelCentro()
        {          
            setLayout(new GridLayout(5,2));  
            lblIng = new JLabel("Ingrediente");
            ingrediente = UInterfaceBox.createJComboBoxPersonalTab2("MateriaPrima.tab");

            lblUnid = new JLabel("Unidade de Medida");
            unidadeMedida = UInterfaceBox.createJComboBoxPersonalTab2("UnidadeMedida.tab");

            lblQtdEn = new JLabel("Quantidade");
            qtdEntrada = new JTextField();

            lblCustou = new JLabel("Custo/unidade");
            custoUnit = new JTextField();

            lblCustoTot = new JLabel("Custo Total");
            custoTotal = new JTextField();
            
            add(lblIng);
            add(ingrediente);

            add(lblUnid);
            add(unidadeMedida);

            add(lblQtdEn);
            add(qtdEntrada);

            add(lblCustou);
            add(custoUnit);

            add(lblCustoTot);
            add(custoTotal);

        }
    }

    public class PainelSul extends JPanel implements ActionListener
    {

        private JButton salvarJB, cancelarJB;
		
		public PainelSul()
		{
			add( salvarJB = new JButton("Salvar") );
			add( cancelarJB = new JButton("Cancelar") );

            cancelarJB.addActionListener(this);
		}

            public void actionPerformed(ActionEvent evt)
            {
                if(evt.getSource() == cancelarJB)
                {
                    dispose();
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