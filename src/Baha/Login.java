package Baha;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;


public class Login extends JFrame implements ActionListener {

    // Elements de la fenêtre
    private JLabel titleLabel, usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel lblNewLabel;
    private JPasswordField passwordField_1;
    private JPasswordField passwordField_2;
  

    public Login() {
    	
        // Configuration de la fenêtre de login
        super("Authentification");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1277, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        // Création des éléments de la fenêtre
        titleLabel = new JLabel("Pharmacie - Connexion       \r\n                                                                                               réalisée par Bouazizi Baha", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        usernameLabel = new JLabel("Nom d'utilisateur :", JLabel.RIGHT);
        usernameLabel.setBounds(24, 238, 212, 64);
        usernameLabel.setFont(new Font("Bahnschrift", Font.BOLD | Font.ITALIC, 15));
        passwordLabel = new JLabel("Mot de passe :", JLabel.RIGHT);
        passwordLabel.setBounds(68, 280, 138, 72);
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        usernameField = new JTextField();
        usernameField.setBackground(new Color(255, 255, 255));
        usernameField.setBounds(285, 254, 265, 31);
        passwordField = new JPasswordField();
        passwordField.setBounds(285, 295, 265, 31);
        

        // Configuration du layout
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        loginButton = new JButton("Se connecter");
        loginButton.setBounds(285, 380, 265, 37);
        loginButton.setBackground(new Color(255, 255, 255));
        loginButton.setFont(new Font("Tahoma", Font.BOLD, 13));
        loginButton.setIcon(new ImageIcon(Login.class.getResource("/images/logout.png")));
        formPanel.add(loginButton);

       
        lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(-11, -44, 1285, 768);
        lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/images/Blue Modern Login Page Desktop Prototype.png")));
        formPanel.add(lblNewLabel);
        
        passwordField_1 = new JPasswordField();
        passwordField_1.setBounds(285, 309, 265, 31);
        formPanel.add(passwordField_1);
        
        passwordField_2 = new JPasswordField();
        passwordField_2.setBackground(new Color(255, 255, 255));
        passwordField_2.setBounds(285, 315, 265, 31);
        formPanel.add(passwordField_2);
        
                // Ajout des listeners
                loginButton.addActionListener(this);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
        setVisible(true);
        
    }

    
  

  
    public void actionPerformed(ActionEvent e) {
        // Réagir aux actions des champs et du bouton
        if (e.getSource() == loginButton) {
            String user = usernameField.getText();
            char[] password = passwordField.getPassword();

            try {
                // Vérifier si les identifiants sont valides
                if ((user.equals("pharmacien") || user.equals("admin")) && Arrays.equals(password, "password".toCharArray())) {
                    // Si les identifiants sont valides, ouvrir l'interface correspondante
                    if (user.equals("pharmacien")) {
                        new PharmacienInterface();
                    } else {
                        new AdminInterface();
                    }
                    // Fermer la fenêtre de login
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Nom d'utilisateur ou mot de passe incorrect.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }

            // Effacer le champ du mot de passe
            passwordField.setText("");
        }
    }







    
}


