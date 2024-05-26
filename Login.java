import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Login extends JFrame implements ActionListener
{
    private JLabel logo, welcomelbl;
    private JButton submitBtn;
    private JTextField usernameTxF;
    private JPasswordField passwdPF;
    private ImageIcon iconImg,resizeIcon;
    private Image image,newImage;
    private JPanel leftPanel;
    private JPanel rigthPanel;


    public Login()
    {  
        super("Sistema de Gestão de Padaria - SGP");
        setLayout(new BorderLayout());
        addPainelOeste();
        addPainelEste();        
    
        //adicionando os elementos
        setSize(800,400);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

    }

    private void addPainelOeste()
    {
        leftPanel = new JPanel();

        iconImg = new ImageIcon("imagens/logo.jpg");
        //redimensiona a imagem
        image = iconImg.getImage();
        newImage = image.getScaledInstance(400,400,Image.SCALE_SMOOTH);
        resizeIcon = new ImageIcon(newImage);

        //cria os elementos
        logo = new JLabel();
        logo.setIcon(resizeIcon);
        
        leftPanel.add(logo);
        //adicionando os elementos
        getContentPane().add(leftPanel, BorderLayout.WEST);
    }

    private void addPainelEste()
    {
        rigthPanel = new JPanel();
        rigthPanel.setLayout(new GridLayout(1,4));
        
        welcomelbl = new JLabel("Bem-Vindo");
        usernameTxF = new JTextField("Nome de Usuário");
        submitBtn = new JButton("Iniciar Sessão");
        passwdPF = new JPasswordField("Palavra-Passe");

        //estilizando
        welcomelbl.setFont(new Font("Arial", Font.PLAIN,40));
        
        //redimensionando
        

        rigthPanel.add(welcomelbl);
        rigthPanel.add(usernameTxF);
        rigthPanel.add(passwdPF);
        rigthPanel.add(submitBtn);

        getContentPane().add(rigthPanel, BorderLayout.EAST);
    
    }

    


    public void actionPerformed(ActionEvent evt)
    {

    }
    public static void main(String args[])
    {
        new Login();
    }
}