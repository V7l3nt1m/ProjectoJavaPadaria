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
import javax.swing.UIManager.*;


public class MenuPrincipal extends JFrame
{  
    private PainelSuperior pSuper;
    private PainelCentro pCentro;

    public MenuPrincipal()
    {
        super("Menu Principal");
        definirTema();
        //separador
        setLayout(new BorderLayout(1,3));
        
        getContentPane().add(pSuper = new PainelSuperior(), BorderLayout.NORTH);
        getContentPane().add(pCentro = new PainelCentro(), BorderLayout.CENTER);
        


        setSize(1350, 900);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class PainelSuperior extends JPanel
    {
        private JButton btnOperacoes,btnEstoque,btnProducao,btnTabelas,btnAjuda,btnSair;
        private JLabel logo, nomeUser, lblOper, lblEstoq, lblProdu, lblTable, lblAjuda, lblSair;
        private ImageIcon imagemLogo, operLogo, sairImg, ajudaImg, estoqueImg,tableImg, produImg;
        private ManipuladorEventos manipulador;
        private Color customColor;
        private JPanel operJp;

        public PainelSuperior()
        {         
            Color customColor = Color.decode("#F5F5F5");
            setBackground(customColor);   
            setBorder(BorderFactory.createEmptyBorder(0,0,-60,1));
            setLayout(new GridLayout(2,20));
            
            lblOper = new JLabel("Operações");
            lblOper.setHorizontalAlignment(SwingConstants.CENTER);
            lblOper.setVerticalAlignment(SwingConstants.TOP);
            operLogo = new ImageIcon("imagens/cadastro.png");
            btnOperacoes = new JButton(operLogo);
            btnOperacoes.setBackground(Color.WHITE);

            lblTable = new JLabel("Tabelas");
            lblTable.setHorizontalAlignment(SwingConstants.CENTER);
            lblTable.setVerticalAlignment(SwingConstants.TOP);
            tableImg = new ImageIcon("imagens/table.png");
            btnTabelas = new JButton(tableImg);
            btnTabelas.setBackground(Color.WHITE);

            lblAjuda = new JLabel("Ajuda");
            lblAjuda.setHorizontalAlignment(SwingConstants.CENTER);
            lblAjuda.setVerticalAlignment(SwingConstants.TOP);
            ajudaImg = new ImageIcon("imagens/ajuda.png");
            btnAjuda = new JButton(ajudaImg);
            btnAjuda.setBackground(Color.WHITE);

            lblProdu = new JLabel("Producao");
            lblProdu.setHorizontalAlignment(SwingConstants.CENTER);
            lblProdu.setVerticalAlignment(SwingConstants.TOP);
            produImg = new ImageIcon("imagens/product.png");
            btnProducao = new JButton(produImg);
            btnProducao.setBackground(Color.WHITE);


            lblEstoq = new JLabel("Estoque");
            lblEstoq.setHorizontalAlignment(SwingConstants.CENTER);
            lblEstoq.setVerticalAlignment(SwingConstants.TOP);
            estoqueImg = new ImageIcon("imagens/estoque.png");
            btnEstoque = new JButton(estoqueImg);
            btnEstoque.setBackground(Color.WHITE);

            lblSair = new JLabel("Sair");
            lblSair.setHorizontalAlignment(SwingConstants.CENTER);
            lblSair.setVerticalAlignment(SwingConstants.TOP);
            sairImg = new ImageIcon("imagens/desligar.png");
            btnSair = new JButton(sairImg);
            btnSair.setBackground(Color.WHITE);
            btnSair.setPreferredSize(new Dimension(50,50));

            add(btnOperacoes);
            add(btnEstoque);
            add(btnProducao);
            add(btnTabelas);
            add(btnAjuda);
            add(btnSair);
            for(int i = 0; i<10;i++)
                add(new JLabel());

            add(lblOper);
            add(lblEstoq);
            add(lblProdu);
            add(lblTable);
            add(lblAjuda);
            add(lblSair);

             for(int i = 0; i<10;i++)
                add(new JLabel());
            
          
            manipulador = new ManipuladorEventos();

            btnSair.addActionListener(manipulador);
        }

         private class ManipuladorEventos implements ActionListener
        {
            public void actionPerformed(ActionEvent evt)
            {
                    
                if(evt.getSource() == btnSair)
                {
                    int resultado = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja sair?","Saindo",JOptionPane.YES_NO_OPTION);
                    if(resultado == 0)
                    {
                        dispose();
                    }
                }       
            }
        }

    }

    private class PainelCentro extends JPanel
    {
        private JLabel fundo;
        private ImageIcon logo,logoRedim;
        private Image redimLogo,image; 

        public PainelCentro()
        {
            setBackground(Color.WHITE);
            logo = new ImageIcon("imagens/logo.png");
            image = logo.getImage();            
            
            redimLogo = image.getScaledInstance(1000,800, Image.SCALE_SMOOTH);
            logoRedim = new ImageIcon(redimLogo);
            fundo = new JLabel(logoRedim);

            add(fundo);
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
        new MenuPrincipal();
    }

}