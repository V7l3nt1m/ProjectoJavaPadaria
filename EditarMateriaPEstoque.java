/*------------------------------------
Tema: Gestão de uma Padaria
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: EditarMateriaPEstoque.java
Data: 20.06.2024
--------------------------------------*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.File;

public class EditarMateriaPEstoque extends JFrame
{
    private PainelNorte painelNorte;
    private PainelSul painelSul;
    private PainelCentro painelCentro;

    public EditarMateriaPEstoque(EstoqueModelo modelo)
    {
        super("Alterar Dados Estoque Materia Prima");
        definirTema();
        setLayout(new BorderLayout());

        getContentPane().add(painelNorte = new PainelNorte(), BorderLayout.NORTH);
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

    public class PainelCentro extends JPanel
    {
        private JComboBox unidadeMedida;
        private JTextField nivelAtualJTF, idJTF;

        private JComboBoxPersonal ingrediente, nivelMinimoJCB, ingrediente2;
        private JComboBoxTabela2_Tabela3 ingNivelMinimo, unidadeMat;

        private JLabel lblIng, lblUnid, lblNivelAtual,lblNivelMinimo, lblDataEnt;
        private JPanel painelData, painelData2;
        private JTextFieldData dataEntrada,dataValidade;

        public PainelCentro(EstoqueModelo modelo)
        { 
            setLayout(new GridLayout(7,2));  
            unidadeMat = new JComboBoxTabela2_Tabela3("MateriaPrima.tab","UnidadeMedida.tab");
            ingNivelMinimo = new JComboBoxTabela2_Tabela3("MateriaPrima.tab","NivelMinimo.tab");

            idJTF = new JTextField();
            EstoqueFile entradafile = new EstoqueFile();
			idJTF.setText( "" + modelo.getId());

            lblIng = new JLabel("Ingrediente");
            ingrediente = unidadeMat.getComboBoxFather();
            ingrediente.setSelectedItem(modelo.getIngrediente());

            lblUnid = new JLabel("Unidade de Medida");
            unidadeMedida = unidadeMat.getComboBoxSun();
            unidadeMedida.setSelectedItem(modelo.getUnidadeMedida());

            lblNivelAtual = new JLabel("Nivel Atual");
            nivelAtualJTF = new JTextField();
            nivelAtualJTF.setText(""+modelo.getNivelAtual());

            lblNivelMinimo = new JLabel("Nivel Minimo");
            ingrediente = ingNivelMinimo.getComboBoxFather();
            nivelMinimoJCB = ingNivelMinimo.getComboBoxSun();
            nivelMinimoJCB.setSelectedItem(modelo.getNivelMinimo());
            nivelMinimoJCB.setFocusable(false);

            lblDataEnt = new JLabel("Data de Entrada");

            painelData = new JPanel( new GridLayout(1, 1) );
            dataEntrada = new JTextFieldData("Data?");
            dataEntrada.getDTestField().setText(modelo.getDataEntradaEstoque());

            painelData.add( dataEntrada.getDTestField() );
			painelData.add( dataEntrada.getDButton() );

            add(lblIng);
            add(ingrediente);

            add(lblUnid);
            add(unidadeMedida);

            add(lblNivelAtual);
            add(nivelAtualJTF);

            add(lblNivelMinimo);
            add(nivelMinimoJCB);
  
            add(lblDataEnt);
            add(painelData);
        }
        

        public int getId()
        {
            return Integer.parseInt( idJTF.getText().trim());
        }

        public int getNivelAtual()
        {
            return Integer.parseInt( nivelAtualJTF.getText().trim() );
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

        public String getNivelMinimo()
        {
            return String.valueOf(nivelMinimoJCB.getSelectedItem());
        }

        public void setId(int id)
        {
            idJTF.setText(""+id);
        }

        public void setNivelAtual(int nivelEntrada)
        {
            nivelAtualJTF.setText("" + nivelEntrada);
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


        public void alterar()
        {
            EstoqueModelo novoEstoque = new EstoqueModelo(
                getId(), 
            (Integer.parseInt(getNivelMinimo())), 
                (int)getNivelAtual(), 
                0.0,
                0.0, 
                getIngrediente(), 
                getUnidadeMedida(), 
                getDataEntrada(), true);

                novoEstoque.editarDados();
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
                    painelCentro.alterar();
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