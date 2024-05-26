/*------------------------------------
Tema: Gestão de uma Padaria
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: Login.java
Data: 25.05.2024
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.UIManager.*;

public class Login extends JFrame implements ActionListener
{
    private JLabel logo, welcomelbl,usernamelbl,passwordlbl,iconUser,iconPass;
    private JButton submitBtn;
    private JTextField usernameTxF;
    private JPasswordField passwdPF;
    private ImageIcon iconImg,userIco,passIco;
    private JPanel leftPanel;
    private JPanel rigthPanel;


    public Login()
    {  
        super("Sistema de Gestão de Padaria - SGP");
        definirTema();
        setLayout(null);
        instanciarObj();
    
        setSize(890,420);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void instanciarObj()
    {
        logo = new JLabel();
        iconImg = new ImageIcon("imagens/logo.jpg");
        logo.setIcon(iconImg);
        logo.setBounds(0,-100,800,600);
        add(logo);

    
        welcomelbl = new JLabel("Bem-Vindo");
        welcomelbl.setFont(new Font("Monospaced", Font.BOLD,40));
        welcomelbl.setBounds(571,50,400,30);
        add(welcomelbl);

        iconUser = new JLabel();
        userIco = new ImageIcon("imagens/user.png");
        iconUser.setIcon(userIco);
        iconUser.setBounds(538,95,100,100);
        usernamelbl = new JLabel("Nome do Usuário");
        usernamelbl.setBounds(577,100,192,30);
        usernameTxF = new JTextField();
        usernameTxF.setBounds(571,130,200,30);
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
        add(iconPass);
        add(passwordlbl);
        add(passwdPF);

        submitBtn = new JButton("Iniciar Sessão");
        submitBtn.setBounds(571,245,200,30);
        add(submitBtn);


        setResizable(false);
    }


    public void actionPerformed(ActionEvent evt)
    {

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
        new Login();
    }
}