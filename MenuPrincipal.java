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

    public MenuPrincipal()
    {
        super("Menu Principal");
        instanciarObj();

        setSize(1350, 900);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void instanciarObj()
    {
       
    }


    public void actionPerformed(ActionEvent evt)
	{
        /*
		if(evt.getSource() == sairMatPItem)
        {
           int resultado = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja sair?","Saindo",JOptionPane.YES_NO_OPTION);
           if(resultado == 0)
           {
                dispose();
           }
        }
        */
	}


    public static void main(String args[])
    {
        new MenuPrincipal();
    }

}