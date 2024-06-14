/*------------------------------------
Tema: Gestão de uma Padaria
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: EditarEntrada.java
Data: 11.06.2024
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class EditarEntradaVisao extends JFrame
{	
	
	PainelCentro centro;
	PainelSul sul;
	
	public EditarEntradaVisao()
	{
		super("Pesquisar para Editar Dados");
		getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
		getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);
	
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	class PainelCentro extends JPanel
	{
		JComboBox nomesJCB;
		
		public PainelCentro()
		{
			setLayout( new GridLayout(1, 2));

			add( new JLabel("Escolha o Nome do Ingrediente") );
			add( nomesJCB = new JComboBox(EntradaFile.getAllNames()));
						
		}
		

		public String getNome()
        {
            return String.valueOf(nomesJCB.getSelectedItem());
        }
	}
	
	class PainelSul extends JPanel implements ActionListener
	{
		JButton pesquisarJB, cancelarJB;
		
		public PainelSul()
		{
			add(pesquisarJB = new JButton("Pesquisar/Editar"));
			add(cancelarJB = new JButton("Cancelar") );
			
			pesquisarJB.addActionListener(this);
			cancelarJB.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent evt)
		{
			if (evt.getSource() == pesquisarJB)
			{
				EntradaModelo modelo;
				modelo = EntradaFile.pesquisarEntradaPorNomeM(centro.getNome());
				new EditarEntradaVisao(true, modelo);
			}
			else
				dispose();
		}
	}
    
}

/*
Cadastrar de antemao os ingredientes
depois quando o usuario cadastrar uma compra de ingredientes, apenas actualizar os valores de quantidade e nivelAtual
E mais facil ir no registro de compras e criar um popup menu, com duas opcoes: eliminar e editar e passar os dados num actionlistener!!
estudar bem o sizeoff
*/