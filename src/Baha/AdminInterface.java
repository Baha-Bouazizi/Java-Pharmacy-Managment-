package Baha;


import javax.swing.*;

import PH_DAO.Connexion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminInterface extends JFrame implements ActionListener {
    Connection conn = Connexion.getConnexion();
    // Liste des médicaments
   //private JList<String> medList;
   // Liste des clients ;
  //  private JList<String> clientList;
    // Boutons
    private JButton addMedButton, editMedButton, deleteMedButton, addClientButton, editClientButton, deleteClientButton, closeButton;

    public AdminInterface() {
        // Configuration de la fenêtre principale
        super("Gestion des médicaments , des clients et des ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1079, 792);
        setLocationRelativeTo(null);
        setResizable(false);
        JPanel medPanel = new JPanel();
        medPanel.setBorder(BorderFactory.createTitledBorder("Médicaments"));
        JPanel clientPanel = new JPanel();
        clientPanel.setBorder(BorderFactory.createTitledBorder("Clients"));

        // Configuration du panel pour les boutons de sauvegarde et de fermeture
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));

        // Configuration du panel principal
        JPanel mainPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        mainPanel.add(medPanel);
        medPanel.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(359, 125, 10, 10);
        medPanel.add(panel);
        
                // Création de la liste des médicaments
           //   medList = new JList<>(new String[]{"Médicament 1", "Médicament 2", "Médicament 3", "Médicament 4", "Médicament 5"});
               // JScrollPane medScrollPane = new JScrollPane(medList);
              // medScrollPane.setPreferredSize(new Dimension(250, 200));
                // Création des boutons pour la gestion des médicaments
                addMedButton = new JButton("Ajouter");
                addMedButton.setIcon(new ImageIcon(AdminInterface.class.getResource("/images/addMedicine.png")));
                addMedButton.setFont(new Font("Tahoma", Font.BOLD, 12));
                addMedButton.setBounds(-11, 340, 439, 34);
                medPanel.add(addMedButton);
                editMedButton = new JButton("Modifier");
                editMedButton.setIcon(new ImageIcon(AdminInterface.class.getResource("/images/modifier.png")));
                editMedButton.setFont(new Font("Tahoma", Font.BOLD, 12));
                editMedButton.setBounds(426, 340, 304, 34);
                medPanel.add(editMedButton);
                deleteMedButton = new JButton("Supprimer");
                deleteMedButton.setIcon(new ImageIcon(AdminInterface.class.getResource("/images/supprimer.png")));
                deleteMedButton.setFont(new Font("Tahoma", Font.BOLD, 12));
                deleteMedButton.setBounds(728, 341, 327, 34);
                medPanel.add(deleteMedButton);
                
                JLabel lblNewLabel = new JLabel("New label");
                lblNewLabel.setIcon(new ImageIcon(AdminInterface.class.getResource("/images/aaaaaaaaaa.png")));
                lblNewLabel.setBounds(-1, 10, 1070, 468);
                medPanel.add(lblNewLabel);
                deleteMedButton.addActionListener(this);
                editMedButton.addActionListener(this);
                // Ajout des listeners aux boutons pour la gestion des médicaments
                addMedButton.addActionListener(this);
        mainPanel.add(clientPanel);
        clientPanel.setLayout(null);
        deleteClientButton = new JButton("Supprimer");
        deleteClientButton.setIcon(new ImageIcon(AdminInterface.class.getResource("/images/man(2).png")));
        deleteClientButton.setBounds(756, 309, 312, 32);
        clientPanel.add(deleteClientButton);
        editClientButton = new JButton("Modifier");
        editClientButton.setIcon(new ImageIcon(AdminInterface.class.getResource("/images/modifier.png")));
        editClientButton.setBounds(469, 309, 294, 32);
        clientPanel.add(editClientButton);
        // Création de la liste des clients 
     //  clientList = new JList<>(new String[]{"Client 1", "Client 2", "Client 3", "Client 4", "Client 5"});
      //  JScrollPane clientScrollPane = new JScrollPane(clientList);
       // clientScrollPane.setPreferredSize(new Dimension(250, 200));
        // Création des boutons pour la gestion des clients
        addClientButton = new JButton("Ajouter");
        addClientButton.setIcon(new ImageIcon(AdminInterface.class.getResource("/images/ajouter client.png")));
        addClientButton.setBounds(0, 309, 475, 32);
        clientPanel.add(addClientButton);
        closeButton = new JButton("Fermer");
        closeButton.setIcon(new ImageIcon(AdminInterface.class.getResource("/images/no.png")));
        closeButton.setBounds(0, 342, 1055, 32);
        clientPanel.add(closeButton);
        
        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setIcon(new ImageIcon(AdminInterface.class.getResource("/images/bahbah.png")));
        lblNewLabel_1.setBounds(0, 0, 1078, 341);
        clientPanel.add(lblNewLabel_1);
        closeButton.addActionListener(this);
        // Ajout des listeners aux boutons pour la gestion des clients
        addClientButton.addActionListener(this);
        editClientButton.addActionListener(this);
        deleteClientButton.addActionListener(this);

        // Ajout des panels au contenu de la fenêtre
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(mainPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addMedButton) {
     	    String medName = JOptionPane.showInputDialog(this, "Entrez le nom du médicament à ajouter :");
     	   //String desm = JOptionPane.showInputDialog(this, "Entrez le description du médicament à ajouter :");
     	    int prixm = Integer.parseInt(JOptionPane.showInputDialog(this, "Entrez le prix du médicament à ajouter :"));
     	    int qtitem = Integer.parseInt(JOptionPane.showInputDialog(this, "Entrez le quantite du médicament à ajouter :"));
     	    if (medName != null && !medName.isEmpty()) {
     	        try {
     	            // Connect to the database

     	            // Prepare the SQL INSERT statement
     	            String sql = "INSERT INTO medicaments (nom, prix, quantite) VALUES (?, ?, ?)";
     	            PreparedStatement stmt = conn.prepareStatement(sql);
     	            stmt.setString(1, medName);
     	           
     	            stmt.setInt(2, prixm);
     	            stmt.setInt(3, qtitem);

     	       
     	            stmt.executeUpdate();
     	            JOptionPane.showMessageDialog(this, "Médicament ajouté avec succès");
     	            	/*
     	            // Update the medication list in the UI
     	            DefaultListModel<String> model = (DefaultListModel<String>) medList.getModel();
     	            model.addElement(medName);*/

     	            // Close the database connection
     	           // conn.close();
     	        } catch (SQLException ex) {
     	            ex.printStackTrace();
     	            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout du médicament : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
     	        }
     	    }
        } else if (e.getSource() == editMedButton) {
            // Demander l'id du médicament à modifier
    	    int idMedicament = Integer.parseInt(JOptionPane.showInputDialog(this, "Entrez l'id du médicament à modifier :"));
    	    
    	    try {
    	       
    	        
    	        // Vérifier que le médicament existe dans la base de données
    	        String sqlCheck = "SELECT * FROM medicaments WHERE id = ?";
    	        PreparedStatement stmtCheck = conn.prepareStatement(sqlCheck);
    	        stmtCheck.setInt(1, idMedicament);
    	        ResultSet rs = stmtCheck.executeQuery();
    	        if (!rs.next()) {
    	            JOptionPane.showMessageDialog(this, "Le médicament avec l'id " + idMedicament + " n'existe pas.", "Erreur", JOptionPane.ERROR_MESSAGE);
    	           // conn.close();
    	            return;
    	        }
    	        
    	        // Demander les nouvelles informations sur le médicament
    	        String newMedName = JOptionPane.showInputDialog(this, "Entrez le nouveau nom du médicament :");
    	        int newPrix = Integer.parseInt(JOptionPane.showInputDialog(this, "Entrez le nouveau prix du médicament :"));
    	        int newQtite = Integer.parseInt(JOptionPane.showInputDialog(this, "Entrez la nouvelle quantité du médicament :"));
    	        
    	        // Préparer la requête SQL de modification
    	        String sqlUpdate = "UPDATE medicaments SET nom = ?, prix = ?, quantite = ? WHERE id = ?";
    	        PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);
    	        stmtUpdate.setString(1, newMedName);
    	        stmtUpdate.setInt(2, newPrix);
    	        stmtUpdate.setInt(3, newQtite);
    	        stmtUpdate.setInt(4, idMedicament);
    	        stmtUpdate.executeUpdate();
    	        
    	        // Afficher un message de confirmation
    	        JOptionPane.showMessageDialog(this, "Le médicament avec l'id " + idMedicament + " a été modifié avec succès.", "Modification réussie", JOptionPane.INFORMATION_MESSAGE);
    	        
    	        // Fermer la connexion à la base de données
    	       // conn.close();
    	    } catch (SQLException ex) {
    	        ex.printStackTrace();
    	        JOptionPane.showMessageDialog(this, "Erreur lors de la modification du médicament : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    	    }
        } else if (e.getSource() == deleteMedButton) {
        	 // Supprimer un médicament
     	   String inputId = JOptionPane.showInputDialog(this, "Entrez l'ID du médicament à supprimer :");
     	    if (inputId != null && !inputId.isEmpty()) {
     	        int id = Integer.parseInt(inputId);
     	        try {
     	            // Connect to the database

     	            // Prepare the SQL DELETE statement
     	            String sql = "DELETE FROM medicaments WHERE id = ?";
     	            PreparedStatement stmt = conn.prepareStatement(sql);
     	            stmt.setInt(1, id);

     	            int rowsAffected = stmt.executeUpdate();
     	            if (rowsAffected == 0) {
     	                JOptionPane.showMessageDialog(this, "Aucun médicament avec cet ID n'a été trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
     	            } else {
     	                JOptionPane.showMessageDialog(this, "Le médicament a été supprimé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
     	            }

     	            // Close the database connection
     	          //  conn.close();
     	        } catch (SQLException ex) {
     	            ex.printStackTrace();
     	            JOptionPane.showMessageDialog(this, "Erreur lors de la suppression du médicament : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
     	        }
     	    }
        } if (e.getSource() == addClientButton) {
            String clientName = JOptionPane.showInputDialog(this, "Entrez le nom du client à ajouter :");
            String clientAddress = JOptionPane.showInputDialog(this, "Entrez l'adresse du client à ajouter :");
            String clientPhone = JOptionPane.showInputDialog(this, "Entrez le numéro de téléphone du client à ajouter :");
            double clientCredit = Double.parseDouble(JOptionPane.showInputDialog(this, "Entrez le crédit du client à ajouter :"));
            if (clientName != null && !clientName.isEmpty()) {
                try {
                    // Connect to the database

                    // Prepare the SQL INSERT statement
                    String sql = "INSERT INTO clients (nom, adresse, telephone, credit) VALUES (?, ?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, clientName);
                    stmt.setString(2, clientAddress);
                    stmt.setString(3, clientPhone);
                    stmt.setDouble(4, clientCredit);

                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Client ajouté avec succès");

                    // Update the client list in the UI
                 //   DefaultListModel<String> model = (DefaultListModel<String>) clientList.getModel();
                  //  model.addElement(clientName);

                    // Close the database connection
                  //  conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout du client : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
 else if (e.getSource() == editClientButton) {
	 // Demander l'id du client à modifier
	    int idClient = Integer.parseInt(JOptionPane.showInputDialog(this, "Entrez l'id du client à modifier :"));

	    try {
	        // Vérifier que le client existe dans la base de données
	        String sqlCheck = "SELECT * FROM clients WHERE id = ?";
	        PreparedStatement stmtCheck = conn.prepareStatement(sqlCheck);
	        stmtCheck.setInt(1, idClient);
	        ResultSet rs = stmtCheck.executeQuery();
	        if (!rs.next()) {
	            JOptionPane.showMessageDialog(this, "Le client avec l'id " + idClient + " n'existe pas.", "Erreur", JOptionPane.ERROR_MESSAGE);
	         //   conn.close();
	            return;
	        }

	        // Demander les nouvelles informations sur le client
	        String newNom = JOptionPane.showInputDialog(this, "Entrez le nouveau nom du client :");
	        String newAdresse = JOptionPane.showInputDialog(this, "Entrez la nouvelle adresse du client :");
	        String newTelephone = JOptionPane.showInputDialog(this, "Entrez le nouveau numéro de téléphone du client :");
	        BigDecimal newCredit = new BigDecimal(JOptionPane.showInputDialog(this, "Entrez le nouveau crédit du client :"));

	        // Préparer la requête SQL de modification
	        String sqlUpdate = "UPDATE clients SET nom = ?, adresse = ?, telephone = ?, credit = ? WHERE id = ?";
	        PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);
	        stmtUpdate.setString(1, newNom);
	        stmtUpdate.setString(2, newAdresse);
	        stmtUpdate.setString(3, newTelephone);
	        stmtUpdate.setBigDecimal(4, newCredit);
	        stmtUpdate.setInt(5, idClient);
	        stmtUpdate.executeUpdate();

	        // Afficher un message de confirmation
	        JOptionPane.showMessageDialog(this, "Le client avec l'id " + idClient + " a été modifié avec succès.", "Modification réussie", JOptionPane.INFORMATION_MESSAGE);

	        // Fermer la connexion à la base de données
	      //  conn.close();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Erreur lors de la modification du client : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
	    }
        } else if (e.getSource() == deleteClientButton) {
        	 // Demander l'id du client à supprimer
            int idClient = Integer.parseInt(JOptionPane.showInputDialog(this, "Entrez l'id du client à supprimer :"));
            
            try {
                // Vérifier que le client existe dans la base de données
                String sqlCheck = "SELECT * FROM clients WHERE id = ?";
                PreparedStatement stmtCheck = conn.prepareStatement(sqlCheck);
                stmtCheck.setInt(1, idClient);
                ResultSet rs = stmtCheck.executeQuery();
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(this, "Le client avec l'id " + idClient + " n'existe pas.", "Erreur", JOptionPane.ERROR_MESSAGE);
                   // conn.close();
                    return;
                }
                
                // Préparer la requête SQL de suppression
                String sqlDelete = "DELETE FROM clients WHERE id = ?";
                PreparedStatement stmtDelete = conn.prepareStatement(sqlDelete);
                stmtDelete.setInt(1, idClient);
                stmtDelete.executeUpdate();
                
                // Afficher un message de confirmation
                JOptionPane.showMessageDialog(this, "Le client avec l'id " + idClient + " a été supprimé avec succès.", "Suppression réussie", JOptionPane.INFORMATION_MESSAGE);
                
                // Fermer la connexion à la base de données
              //  conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur lors de la suppression du client : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
      
            dispose();
        }
          
    }
    
   
    
}
