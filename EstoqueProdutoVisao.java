/*------------------------------------
Tema: Gestão de uma Padaria
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: EstoqueProdutoVisao.java
Data: 15.06.2024
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.util.*;

public class EstoqueProdutoVisao extends JFrame
{
    private PainelCentro painelCentro;


    private JTabbedPane tabPanel;


    public EstoqueProdutoVisao()
    {
        super("Material em Armazém");
        definirTema();
        getContentPane().add(painelCentro = new PainelCentro(), BorderLayout.CENTER);


        tabPanel = new JTabbedPane();
        tabPanel.addTab("Registro de Producao", painelCentro);


        getContentPane().add(tabPanel, BorderLayout.NORTH);

        setSize(1000,420);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    class PainelCentro extends JPanel implements MouseListener, ActionListener
    {
        private String [] colunas = {"ID", "Produto", "Quantidade", "Preço/Unidade", "Custo Total Producao", "Data de Producao"};
        private JScrollPane sp;
        private JTable tabelaProd;
        private JPopupMenu popMenu;
        private JMenuItem editar, eliminar;
        private Vector<String> dados = new Vector();

        public PainelCentro()
        {
            setLayout(new GridLayout(1,1));
            tabelaProd = new JTable(ProducaoFile.listarMaterialV(), colunas);
            sp = new JScrollPane(tabelaProd);
            add(sp);

            popMenu = new JPopupMenu();
            popMenu.add(editar = new JMenuItem("Editar"));
            popMenu.add(eliminar = new JMenuItem("Eliminar"));

            eliminar.addActionListener(this);
            editar.addActionListener(this);
            tabelaProd.addMouseListener(this);
        }

        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == editar)
            {
                int selectedRow = tabelaProd.getSelectedRow();
                String id = ""+tabelaProd.getValueAt(selectedRow,0);
                ProducaoModelo modelo;
                modelo = ProducaoFile.pesquisarEntradaPorId(id);
                dispose();
                new ProducaoVisao(true, modelo);
            }
            else
            {
                int resposta = JOptionPane.showConfirmDialog(null,"Deseja Eliminar os dados","Eliminar dados", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if(resposta == 1)
                {
                    JOptionPane.showMessageDialog(null, "Operacao cancelada", "Eliminar os dados", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    int selectedRow = tabelaProd.getSelectedRow();
                    String id = ""+tabelaProd.getValueAt(selectedRow,0);
                    ProducaoModelo modelo;
                    modelo = ProducaoFile.pesquisarEntradaPorId(""+id);
                    modelo.setStatus(false);
                    modelo.eliminar();
                    dispose();
                }
            }
            
        }

        public void mousePressed(MouseEvent evt)
        {
           showPopup(evt);
        }

        public void mouseReleased(MouseEvent evt)
        {
           showPopup(evt);
        }

        public void mouseExited(MouseEvent evt)
        {
           showPopup(evt);
        }

         public void mouseEntered(MouseEvent evt)
        {
           showPopup(evt);
        }

        public void mouseClicked(MouseEvent evt)
        {
           showPopup(evt);
        }


        private void showPopup(MouseEvent evt)
        {
            if(evt.isPopupTrigger() && evt.getComponent() instanceof JTable)
            {
                int row = tabelaProd.rowAtPoint(evt.getPoint());

                if(row >= 0 )
                {
                    tabelaProd.setRowSelectionInterval(row, row);
                    popMenu.show(evt.getComponent(), evt.getX(), evt.getY());
                }
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
        new EstoqueProdutoVisao();
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

//implementar um campo de hora para tornar as pesquisas mais directas, e fazer a proxima parte e avançar