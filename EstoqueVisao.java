/*------------------------------------
Tema: Gestão de uma Padaria
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: EstoqueVisao.java
Data: 04.05.2024
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.util.*;

public class EstoqueVisao extends JFrame
{
    private PainelCentro painelCentro;
    private PainelCentro2 painelCentro2;
    private PainelCentro3 painelCentro3;
    private PainelCentro4 painelCentro4;

    private JTabbedPane tabPanel;


    public EstoqueVisao()
    {
        super("Material em Armazém");
        definirTema();
        getContentPane().add(painelCentro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(painelCentro2 = new PainelCentro2(), BorderLayout.CENTER);
        getContentPane().add(painelCentro3 = new PainelCentro3(), BorderLayout.CENTER);
        getContentPane().add(painelCentro4 = new PainelCentro4(), BorderLayout.CENTER);


        tabPanel = new JTabbedPane();
        tabPanel.addTab("Registro de compras", painelCentro);
        tabPanel.addTab("Estoque", painelCentro2);
        tabPanel.addTab("Pesquisa Registro de compra", painelCentro3);
        tabPanel.addTab("Pesquisa Estoque Materia", painelCentro4);



        getContentPane().add(tabPanel, BorderLayout.NORTH);

        setSize(1000,420);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    class PainelCentro extends JPanel implements MouseListener, ActionListener
    {
        private String [] colunas = {"ID", "Ingrediente", "Unidade Medida", "Quantidade", "Custo/Unidade", "Custo Total", "Data de Entrada" , "Data de Validade"};
        private JScrollPane sp;
        private JTable tabelaMateria;
        private JPopupMenu popMenu;
        private JMenuItem editar, eliminar;
        private Vector<String> dados = new Vector();



        public PainelCentro()
        {
            setLayout(new GridLayout(1,1));
            tabelaMateria = new JTable(EntradaFile.listarMaterialV(), colunas);
            sp = new JScrollPane(tabelaMateria);
            add(sp);

            popMenu = new JPopupMenu();
            popMenu.add(editar = new JMenuItem("Editar"));
            popMenu.add(eliminar = new JMenuItem("Eliminar"));

            eliminar.addActionListener(this);
            editar.addActionListener(this);
            tabelaMateria.addMouseListener(this);
        }

        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == editar)
            {
                int selectedRow = tabelaMateria.getSelectedRow();
                String id = ""+tabelaMateria.getValueAt(selectedRow,0);
                EntradaModelo modelo;
                modelo = EntradaFile.pesquisarEntradaPorId(id);
                dispose();
                new EntradaVisao(true, modelo);
            }
            else
            {
                int resposta = JOptionPane.showConfirmDialog(null,"Deseja Eliminar os dados","Eliminar dados", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if(resposta == 2 || resposta == -1)
                {
                    JOptionPane.showMessageDialog(null, "Operacao cancelada", "Eliminar os dados", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    int selectedRow = tabelaMateria.getSelectedRow();
                    String id = ""+tabelaMateria.getValueAt(selectedRow,0);
                    EntradaModelo modelo;
                    modelo = EntradaFile.pesquisarEntradaPorId(""+id);
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
                int row = tabelaMateria.rowAtPoint(evt.getPoint());

                if(row >= 0 )
                {
                    tabelaMateria.setRowSelectionInterval(row, row);
                    popMenu.show(evt.getComponent(), evt.getX(), evt.getY());
                }
            }
        }  
    }

     class PainelCentro2 extends JPanel implements MouseListener, ActionListener 
    {
        private String [] colunas = {"ID", "Ingrediente/Produto", "Unidade Medida", "Nivel Minimo", "NivelAtual", "Ultima Reposicao"};
        private JScrollPane sp;
        private JTable tabelaMateriaEstoque;
        private JPopupMenu popMenu;
        private JMenuItem editar, eliminar;
        private Vector<String> dados = new Vector();
        

        public PainelCentro2()
        {
            setLayout(new GridLayout(1,1));
            tabelaMateriaEstoque = new JTable(EstoqueFile.estoqueMatriz(), colunas);
            add(sp = new JScrollPane(tabelaMateriaEstoque));

            popMenu = new JPopupMenu();
            popMenu.add(editar = new JMenuItem("Editar"));
            popMenu.add(eliminar = new JMenuItem("Eliminar"));

            eliminar.addActionListener(this);
            editar.addActionListener(this);
            tabelaMateriaEstoque.addMouseListener(this);
        }

        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == editar)
            {
                int selectedRow = tabelaMateriaEstoque.getSelectedRow();
                String id = ""+tabelaMateriaEstoque.getValueAt(selectedRow,0);
                EstoqueModelo modelo;
                modelo = EstoqueFile.pesquisarEntradaPorId(id);
                dispose();
                new EditarMateriaPEstoque(modelo);
            }
            else
            {
                int resposta = JOptionPane.showConfirmDialog(null,"Deseja Eliminar os dados","Eliminar dados", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if(resposta == 2 || resposta == -1)
                {
                    JOptionPane.showMessageDialog(null, "Operacao cancelada", "Eliminar os dados", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    int selectedRow = tabelaMateriaEstoque.getSelectedRow();
                    String id = ""+tabelaMateriaEstoque.getValueAt(selectedRow,0);
                    EstoqueModelo modelo;
                    modelo = EstoqueFile.pesquisarEntradaPorId(""+id);
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
                int row = tabelaMateriaEstoque.rowAtPoint(evt.getPoint());

                if(row >= 0 )
                {
                    tabelaMateriaEstoque.setRowSelectionInterval(row, row);
                    popMenu.show(evt.getComponent(), evt.getX(), evt.getY());
                }
            }
        }
        
    }

    

    class PainelCentro3 extends JPanel implements ActionListener
    {
        private JLabel pesqlbl;
        private JComboBox nomeProd;
        private JButton pesquisarBtn;

        public PainelCentro3()
        {
            add(pesqlbl = new JLabel("Pesquisa por:"));
            add(nomeProd = new JComboBox(EntradaFile.getAllNames()));
            add(pesquisarBtn = new JButton("Pesquisar"));
            pesquisarBtn.addActionListener(this);
        }

        public String getNome()
        {
            return String.valueOf(nomeProd.getSelectedItem());
        }

        public void actionPerformed(ActionEvent evt)
        {
            if(evt.getSource() == pesquisarBtn)
            {
                EntradaFile.pesquisarEntradaPorNome(getNome());
            }
        }
    }

    class PainelCentro4 extends JPanel implements ActionListener
    {
        private JLabel pesqlbl;
        private JComboBox nomeProd;
        private JButton pesquisarBtn;

        public PainelCentro4()
        {
            add(pesqlbl = new JLabel("Pesquisa por:"));
            add(nomeProd = new JComboBox(EstoqueFile.getAllIngredientes()));
            add(pesquisarBtn = new JButton("Pesquisar"));
            pesquisarBtn.addActionListener(this);
        }

        public String getNome()
        {
            return String.valueOf(nomeProd.getSelectedItem());
        }

        public void actionPerformed(ActionEvent evt)
        {
            if(evt.getSource() == pesquisarBtn)
            {
                EstoqueFile.pesquisarIngredienteEstoqueV(getNome());
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
        new EstoqueVisao();
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