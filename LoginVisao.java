/*------------------------------------
Tema: Gestão de uma Padaria
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: LoginVisao.java
Data: 25.05.2024
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.UIManager.*;

public class LoginVisao extends JFrame implements ActionListener
{
    //criando os componentes
    private JLabel logo, welcomelbl,usernamelbl,passwordlbl,iconUser,iconPass;
    private JButton submitBtn;
    private JTextField usernameTxF;
    private JPasswordField passwdPF;
    private ImageIcon iconImg,userIco,passIco;

    public LoginVisao()
    {  
        super("Sistema de Gestão de Padaria - SGP");
        definirTema();
        setLayout(null);
        setResizable(false);
        instanciarObj();
        setSize(890,420);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    public void instanciarObj()
    {
        Color customColor = Color.decode("#B83416");

        logo = new JLabel();
        iconImg = new ImageIcon("imagens/logo.jpg");
        logo.setIcon(iconImg);
        logo.setBounds(0,-100,800,600);
        add(logo);

    
        welcomelbl = new JLabel("Bem-Vindo");
        welcomelbl.setFont(new Font("Monospaced", Font.BOLD,40));
        welcomelbl.setBounds(565,50,400,30);
        welcomelbl.setForeground(customColor);
        add(welcomelbl);

        iconUser = new JLabel();
        userIco = new ImageIcon("imagens/user.png");
        iconUser.setIcon(userIco);
        iconUser.setBounds(538,95,100,100);
        usernamelbl = new JLabel("Nome do Usuário");
        usernamelbl.setFont(new Font("Monospaced", Font.PLAIN,14));
        usernamelbl.setBounds(577,100,192,30);

        usernameTxF = new JTextField();
        usernameTxF.setBounds(571,130,200,30);
        usernameTxF.setBorder(BorderFactory.createLineBorder(customColor,3));

        add(iconUser);
        add(usernamelbl);
        add(usernameTxF);

        iconPass = new JLabel();
        passIco = new ImageIcon("imagens/pass.png");
        iconPass.setIcon(passIco);
        iconPass.setBounds(538,160,100,100);
        passwordlbl = new JLabel("Palavra-Passe");
        passwordlbl.setBounds(577,170,200,30);

        passwdPF = new JPasswordField();
        passwdPF.setBounds(571,200,200,30);
        passwdPF.setFont(new Font("Monospaced", Font.PLAIN,14));
        passwdPF.setBorder(BorderFactory.createLineBorder(customColor,3));

        add(iconPass);
        add(passwordlbl);
        add(passwdPF);

        submitBtn = new JButton("Iniciar Sessão");
        submitBtn.setBounds(571,245,200,30);

        submitBtn.setBackground(customColor);
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFont(new Font("Monospaced", Font.BOLD, 12));

        add(submitBtn);

        submitBtn.addActionListener(this); 
    }

    private String usernameTxF()
    {
        return usernameTxF.getText();
    }

    private String passwdPF()
    {
        return passwdPF.getText();
    }


    public void actionPerformed(ActionEvent evt)
    {
        if(evt.getSource() == submitBtn)
        {
            if(usernameTxF().equals("Valentim") && passwdPF().equals("admin"))
            {
                JOptionPane.showMessageDialog(null, "Bem-vindo: " + usernameTxF(), "Iniciar Sessão", JOptionPane.INFORMATION_MESSAGE);
                new MenuPrincipal();
                dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Nome de Usuário e/ou Palavra-Passe Incorrectos", "Iniciar Sessão", JOptionPane.ERROR_MESSAGE);
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
        new LoginVisao();
    }
}