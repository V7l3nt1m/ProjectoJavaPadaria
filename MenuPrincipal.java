/*------------------------------------
Tema: Gestão de uma Padaria
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: MenuPrincipal.java
Data: 25.05.2024
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MenuPrincipal extends JFrame implements ActionListener
{
    private JMenuBar menuBar;
    private JMenu ficheiroMenu;
    private JMenuItem novaMatPItem, editarMatPItem, eliminarMatPItem, sairMatPItem;


    public MenuPrincipal()
    {
        super("Menu Principal");
        instanciarObj();
        setJMenuBar(menuBar);
        setSize(800, 600);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       

    }

    public void instanciarObj()
    {
        menuBar = new JMenuBar();
        ficheiroMenu = new JMenu("Ficheiro");
        novaMatPItem = new JMenuItem("Nova Materia Prima");
        editarMatPItem = new JMenuItem("Editar Materia Prima");
        eliminarMatPItem = new JMenuItem("Eliminar Materia Prima");
        sairMatPItem = new JMenuItem("Sair");

        ficheiroMenu.add(novaMatPItem);
        ficheiroMenu.add(editarMatPItem);
        ficheiroMenu.add(eliminarMatPItem);
        ficheiroMenu.addSeparator();
        ficheiroMenu.add(sairMatPItem);

        menuBar.add(ficheiroMenu);

        sairMatPItem.addActionListener(this);
    }


    public void actionPerformed(ActionEvent evt)
	{
		if(evt.getSource() == sairMatPItem)
        {
           int resultado = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja sair?","Saindo",JOptionPane.YES_NO_OPTION);
           if(resultado == 0)
           {
                dispose();
           }
        }
	}


    public static void main(String args[])
    {
        new MenuPrincipal();
    }

}