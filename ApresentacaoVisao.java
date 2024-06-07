/*------------------------------------
Tema: Gestão de uma Padaria
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: ApresentacaoVisao.java
Data: 07.06.2024
--------------------------------------*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;


public class ApresentacaoVisao extends JFrame
{
    private PainelCentro painelCentro;
    private PainelSul painelSul;

    public ApresentacaoVisao()
    {
        super("Tela de Boas Vindas");

        getContentPane().add(painelCentro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(painelSul = new PainelSul(), BorderLayout.SOUTH);

        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

     class PainelSul extends JPanel implements ActionListener
    {
        private JButton entrarJB, sairJB;

        public PainelSul()
        {
            add(entrarJB = new JButton("Entrar"));
            add(sairJB = new JButton("Sair"));

            activarBotao(false);
            entrarJB.addActionListener(this);
            sairJB.addActionListener(this);
        }

        public void actionPerformed(ActionEvent evt)
        {
            if(evt.getSource() == entrarJB)
            {   
               new LoginVisao();
               dispose();
            }
            else if (evt.getSource() == sairJB)
            {
                System.exit(0);
            }
        }

        public void activarBotao(boolean status)
        {
            entrarJB.setEnabled(status);
        }
    }

    class PainelCentro extends JPanel implements ActionListener
    {
        JTextArea areaPrincipal;
        JCheckBox concordarJCB;

        public PainelCentro()
        {
            setLayout(new GridLayout(2,1));
            add(new JScrollPane(areaPrincipal = new JTextArea(80,60)));
            areaPrincipal.setFocusable(false);
            //Colocar imagem!!!!
            areaPrincipal.setText("Bem vindo ao Sistema de Gestao de Padaria (SGP) \n\n" + "Este projeto tem o objetivo gerenciar as entradas\n" + "saidas, processo de producao e estoque de uma padaria\n\n" + 
            "Este projecto foi criado no ambito da cadeira de Fundamentos de Programação II\n" + 
            "No Curso de Engenharia Informática da UCAN. \n" + 
            "Se concorda com os termos e condições\n\n" +
            "Dados do Programador:\n" + "Nome: Valentim Loth Simão Prado\n" + "Nº Processo: 1000033031\n" + 
            "");
            add(concordarJCB = new JCheckBox("concordo com os termos de uso!"));

            concordarJCB.addActionListener(this);
        }

        public void actionPerformed(ActionEvent evt)
        {
            if(evt.getSource() == concordarJCB)
            {
                if(concordarJCB.isSelected())
                    painelSul.activarBotao(true);
                else
                    painelSul.activarBotao(false);
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
        new ApresentacaoVisao();
    }
}