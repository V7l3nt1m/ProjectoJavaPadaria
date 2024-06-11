/*------------------------------------
Tema: Gestão de uma Padaria
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: EstoqueMateriaVisao.java
Data: 04.05.2024
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.util.*;

public class EstoqueMateriaVisao extends JFrame
{
    private PainelCentro painelCentro;
    private PainelCentro2 painelCentro2;
    private JTabbedPane tabPanel;


    public EstoqueMateriaVisao()
    {
        super("Material em Armazém");
        definirTema();
        getContentPane().add(painelCentro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(painelCentro2 = new PainelCentro2(), BorderLayout.CENTER);

        tabPanel = new JTabbedPane();
        tabPanel.addTab("Listagem", painelCentro);
        tabPanel.addTab("Pesquisa", painelCentro2);

        getContentPane().add(tabPanel, BorderLayout.NORTH);

        setSize(1000,420);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    class PainelCentro2 extends JPanel implements ActionListener
    {
        private JLabel pesqlbl;
        private JComboBox pesquisaCb;
        private JButton pesquisarBtn;

        public PainelCentro2()
        {
            add(pesqlbl = new JLabel("Pesquisa por Nome"));
            add(pesquisaCb = new JComboBox(EntradaFile.getAllNames()));
            add(pesquisarBtn = new JButton("Pesquisar"));
            pesquisarBtn.addActionListener(this);
        }

        public String getNome()
        {
            return String.valueOf(pesquisaCb.getSelectedItem());
        }

        public void actionPerformed(ActionEvent evt)
        {
            if(evt.getSource() == pesquisarBtn)
            {
                EntradaFile.pesquisarEntradaPorNome(getNome());
            }
        }
    }

    class PainelCentro extends JPanel
    {
        private String [] colunas = {"ID", "Ingrediente", "Unidade Medida", "Quantidade", "Custo/Unidade", "Custo Total", "Data de Entrada" , "Data de Validade"};
        private JScrollPane sp;
        private JTable tabelaMateria;

        public PainelCentro()
        {
            setLayout(new GridLayout(1,1));
            tabelaMateria = new JTable(EntradaFile.listarMaterialV(), colunas);
            sp = new JScrollPane(tabelaMateria);
            add(sp);
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
        new EstoqueMateriaVisao();
    }
}

//adicionar a parte de pesquisa
//adicionar a parte de pesquisa na parte norte, em baixo e mostrar num JOptionPane para nao complicar muito

//ou adicionar um tab para pesquisar e mostrar naquele tab com borderlayout
//se ele escrever a parte do custo total ele faz a divsão no custo unitario e vice-versa
//pesquisar evento no teclado *********** #######

//colocar bwede pesquisa por causa do exame!!!
//terminar ja o projecto para focar na parte das pesquisas que é a prova, Fazer pelo menos dois tipos de pesquisa
//colocar evento no teclado que quando ele apertar enter para entrar