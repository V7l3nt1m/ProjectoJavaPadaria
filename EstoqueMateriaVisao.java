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

public class EstoqueMateriaVisao extends JFrame
{
    private PainelCentro painelCentro;

    public EstoqueMateriaVisao()
    {
        super("Material em Armazém");
        definirTema();
        getContentPane().add(painelCentro = new PainelCentro(), BorderLayout.CENTER);
        setSize(1000,420);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private class PainelCentro extends JPanel
    {
        private String [] colunas = {"ID", "Nome", "Unidade Medida", "Quantidade", "Custo/Unidade", "Custo Total", "Data de Entrada" , "Data de Validade"};
        private String [][] dados = {{"1", "v", "kg", "10", "10", "20", "20/02/2004", "20/02/2004"},
        {"1", "v", "kg", "10", "10", "20", "20/02/2004", "20/02/2004"},
        {"1", "v", "kg", "10", "10", "20", "20/02/2004", "20/02/2004"},
        {"1", "v", "kg", "10", "10", "20", "20/02/2004", "20/02/2004"}};
        private JScrollPane sp;

        private JTable tabelaMateria;

        public PainelCentro()
        {
            setLayout(new GridLayout(1,1));
            tabelaMateria = new JTable(dados, colunas);
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