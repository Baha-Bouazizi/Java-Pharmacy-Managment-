package Baha;
import javax.swing.*;

import com.mysql.cj.xdevapi.Statement;

import PH_DAO.Connexion;

import java.util.Date;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class PharmacienInterface extends JFrame implements ActionListener {
	Connection conn = Connexion.getConnexion();
    // Liste des clients
   
    // Liste des médicaments
    //private JList<String> medList;
    // Boutons
    private JButton ordonnancesButton, rechercherButton, clientButton,medButton,reclientButton,remedButton;

    public PharmacienInterface() {
        // Configuration de la fenêtre principale
        super("Interface pharmacien");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1089, 776);
        setLocationRelativeTo(null);
        setResizable(false);
        // Configuration du layout
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        JPanel listPanel = new JPanel();
        listPanel.setLayout(null);
       // listPanel.add(medScrollPane);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(listPanel, BorderLayout.CENTER);
        
                // Création de la liste des médicaments
               // medList = new JList<>(new String[]{"Médicament 1", "Médicament 2", "Médicament 3", "Médicament 4", "Médicament 5"});
               // JScrollPane medScrollPane = new JScrollPane(medList);
              //  medScrollPane.setPreferredSize(new Dimension(300, 300));
        
                // Création des boutons
                ordonnancesButton = new JButton("Gérer les ordonnances");
                ordonnancesButton.setIcon(new ImageIcon(PharmacienInterface.class.getResource("/images/viewUser.png")));
                ordonnancesButton.setFont(new Font("Tahoma", Font.BOLD, 12));
                ordonnancesButton.setBounds(0, 428, 368, 59);
                listPanel.add(ordonnancesButton);
                rechercherButton = new JButton("Rechercher un médicament");
                rechercherButton.setIcon(new ImageIcon(PharmacienInterface.class.getResource("/images/bouton-rechercher-dans-le-dossier.png")));
                rechercherButton.setFont(new Font("Tahoma", Font.BOLD, 12));
                rechercherButton.setBounds(616, 575, 250, 50);
                listPanel.add(rechercherButton);
                clientButton=new JButton("Consulter les clients ");
                clientButton.setIcon(new ImageIcon(PharmacienInterface.class.getResource("/images/modifier.png")));
                clientButton.setFont(new Font("Tahoma", Font.BOLD, 12));
                clientButton.setBounds(0, 575, 368, 50);
                listPanel.add(clientButton);
                medButton=new JButton("Consulter les médicaments  ");
                medButton.setIcon(new ImageIcon(PharmacienInterface.class.getResource("/images/bouton-modifier.png")));
                medButton.setFont(new Font("Tahoma", Font.BOLD, 12));
                medButton.setBounds(0, 689, 368, 50);
                listPanel.add(medButton);
                
                JLabel lblNewLabel = new JLabel("New label");
                lblNewLabel.setIcon(new ImageIcon(PharmacienInterface.class.getResource("/images/baaaaa.png")));
                lblNewLabel.setBounds(0, 0, 1083, 738);
                listPanel.add(lblNewLabel);
                medButton.addActionListener(this);
                clientButton.addActionListener(this);
                rechercherButton.addActionListener(this);
                // Ajout des listeners aux boutons
                ordonnancesButton.addActionListener(this);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
        setVisible(true);
        JButton exitButton = new JButton("Quitter");
        exitButton.setBounds(501, 0, 125, 37);
        exitButton.setIcon(new ImageIcon(Login.class.getResource("/images/no.png")));
        exitButton.setBackground(new Color(255, 255, 255));
        exitButton.setFont(new Font("Tahoma", Font.BOLD, 13));
        

        // Ajout d'un ActionListener pour le bouton "Quitter"
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Réagir aux actions des boutons
        if (e.getSource() == ordonnancesButton) {
        	
        	    // Créer un dialogue avec les options Ajouter, Modifier, Supprimer
        	    Object[] options = {"Ajouter", "Modifier", "Supprimer"};
        	    int choix = JOptionPane.showOptionDialog(this, "Que voulez-vous faire ?", "Gestion des ordonnances", 
        	                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
        	    
        	    // Réagir en fonction du choix de l'utilisateur
        	    switch(choix) {
        	    case 0: {
        	        // Demander à l'utilisateur de saisir les informations de l'ordonnance
        	        String numOrdStr = JOptionPane.showInputDialog(this, "Entrez le numéro de l'ordonnance :");
        	        String clientIdStr = JOptionPane.showInputDialog(this, "Entrez l'ID du client :");
        	        String dateStr = JOptionPane.showInputDialog(this, "Entrez la date de l'ordonnance (format: YYYY-MM-DD) :");
        	        String remarque = JOptionPane.showInputDialog(this, "Entrez une remarque (facultatif) :");

        	        try {
        	            // Convertir les chaînes en entiers et en date
        	            int numOrd = Integer.parseInt(numOrdStr);
        	            int clientId = Integer.parseInt(clientIdStr);
        	            LocalDate date = LocalDate.parse(dateStr);
                        
        	            // Préparer la requête SQL INSERT
        	            String sql = "INSERT INTO ordonnances (numero_ordonnance, id_client, date, remarque) VALUES (?, ?, ?, ?)";
        	            PreparedStatement stmt = conn.prepareStatement(sql);
        	            stmt.setInt(1, numOrd);
        	            stmt.setInt(2, clientId);
        	            stmt.setObject(3, date);
        	            stmt.setString(4, remarque);

        	            // Exécuter la requête SQL
        	            int rowsAffected = stmt.executeUpdate();

        	            if (rowsAffected > 0) {
        	                JOptionPane.showMessageDialog(this, "Ordonnance ajoutée avec succès.");
        	            } else {
        	                JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout de l'ordonnance.", "Erreur", JOptionPane.ERROR_MESSAGE);
        	            }
        	        } catch (NumberFormatException ex) {
        	            JOptionPane.showMessageDialog(this, "Veuillez entrer des nombres valides pour le numéro d'ordonnance et l'ID du client.", "Erreur", JOptionPane.ERROR_MESSAGE);
        	        } catch (DateTimeParseException ex) {
        	            JOptionPane.showMessageDialog(this, "Veuillez entrer une date valide au format YYYY-MM-DD.", "Erreur", JOptionPane.ERROR_MESSAGE);
        	        } catch (SQLException ex) {
        	            ex.printStackTrace();
        	            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout de l'ordonnance : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        	        }

        	    
        	    }



        	        	   
        	        	

        	    
        	        
        	            
        	            break;
        	    case 1: {
        	        // Demander à l'utilisateur de saisir le numéro de l'ordonnance à modifier
        	        String numOrdStr = JOptionPane.showInputDialog(this, "Entrez le numéro de l'ordonnance à modifier :");

        	        try {
        	            // Convertir la chaîne en entier
        	            int numOrd = Integer.parseInt(numOrdStr);

        	            // Vérifier si l'ordonnance existe dans la base de données
        	            String sql = "SELECT * FROM ordonnances WHERE numero_ordonnance = ?";
        	            PreparedStatement stmt = conn.prepareStatement(sql);
        	            stmt.setInt(1, numOrd);
        	            ResultSet rs = stmt.executeQuery();

        	            if (rs.next()) {
        	                // Demander à l'utilisateur de saisir les nouvelles informations de l'ordonnance
        	                String dateStr = JOptionPane.showInputDialog(this, "Entrez la nouvelle date de l'ordonnance (format: YYYY-MM-DD) :");
        	                String remarque = JOptionPane.showInputDialog(this, "Entrez une nouvelle remarque (facultatif) :");

        	                try {
        	                    // Convertir la chaîne en date
        	                    LocalDate date = LocalDate.parse(dateStr);

        	                    // Préparer la requête SQL UPDATE
        	                    sql = "UPDATE ordonnances SET date = ?, remarque = ? WHERE numero_ordonnance = ?";
        	                    stmt = conn.prepareStatement(sql);
        	                    stmt.setObject(1, date);
        	                    stmt.setString(2, remarque);
        	                    stmt.setInt(3, numOrd);

        	                    // Exécuter la requête SQL
        	                    int rowsAffected = stmt.executeUpdate();

        	                    if (rowsAffected > 0) {
        	                        JOptionPane.showMessageDialog(this, "Ordonnance modifiée avec succès.");
        	                    } else {
        	                        JOptionPane.showMessageDialog(this, "Erreur lors de la modification de l'ordonnance.", "Erreur", JOptionPane.ERROR_MESSAGE);
        	                    }
        	                } catch (DateTimeParseException ex) {
        	                    JOptionPane.showMessageDialog(this, "Veuillez entrer une date valide au format YYYY-MM-DD.", "Erreur", JOptionPane.ERROR_MESSAGE);
        	                } catch (SQLException ex) {
        	                    ex.printStackTrace();
        	                    JOptionPane.showMessageDialog(this, "Erreur lors de la modification de l'ordonnance : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        	                }
        	            } else {
        	                JOptionPane.showMessageDialog(this, "Aucune ordonnance trouvée avec ce numéro.", "Erreur", JOptionPane.ERROR_MESSAGE);
        	            }
        	        } catch (NumberFormatException ex) {
        	            JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre valide pour le numéro d'ordonnance.", "Erreur", JOptionPane.ERROR_MESSAGE);
        	        } catch (SQLException ex) {
        	            ex.printStackTrace();
        	            JOptionPane.showMessageDialog(this, "Erreur lors de la recherche de l'ordonnance : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        	        }
        	    }

        	            
        	    case 2: {
        	        // Demander à l'utilisateur de saisir le numéro de l'ordonnance à supprimer
        	        String numOrdStr = JOptionPane.showInputDialog(this, "Entrez le numéro de l'ordonnance à supprimer :");

        	        try {
        	            // Convertir la chaîne en entier
        	            int numOrd = Integer.parseInt(numOrdStr);

        	            // Préparer la requête SQL DELETE
        	            String sql = "DELETE FROM ordonnances WHERE numero_ordonnance = ?";
        	            PreparedStatement stmt = conn.prepareStatement(sql);
        	            stmt.setInt(1, numOrd);

        	            // Exécuter la requête SQL
        	            int rowsAffected = stmt.executeUpdate();

        	            if (rowsAffected > 0) {
        	                JOptionPane.showMessageDialog(this, "Ordonnance supprimée avec succès.");
        	            } else {
        	                JOptionPane.showMessageDialog(this, "Aucune ordonnance trouvée avec ce numéro.", "Erreur", JOptionPane.ERROR_MESSAGE);
        	            }
        	        } catch (NumberFormatException ex) {
        	            JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre valide pour le numéro d'ordonnance.", "Erreur", JOptionPane.ERROR_MESSAGE);
        	        } catch (SQLException ex) {
        	            ex.printStackTrace();
        	            JOptionPane.showMessageDialog(this, "Erreur lors de la suppression de l'ordonnance : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        	        }
        	    }

        	            break;
        	
        	        default:
        	        {
        	        	
        	        }
        	    }
        	

        } else if (e.getSource() == rechercherButton) {
        
        	    // Chercher un médicament
        	    String medName = JOptionPane.showInputDialog(this, "Entrez le nom du médicament à rechercher :");
        	    if (medName != null && !medName.isEmpty()) {
        	        try {
        	            // Connect to the database

        	            // Prepare the SQL SELECT statement
        	            String sql = "SELECT * FROM medicaments WHERE nom=?";
        	            PreparedStatement stmt = conn.prepareStatement(sql);
        	            stmt.setString(1, medName);
        	            ResultSet rs = stmt.executeQuery();
        	            if (rs.next()) {
        	                int id = rs.getInt("id");
        	                int prix = rs.getInt("prix");
        	                int quantite = rs.getInt("quantite");
        	                String message = String.format("Nom : %s\nPrix : %d\nQuantité : %d", medName, prix, quantite);
        	                JOptionPane.showMessageDialog(this, message);
        	            } else {
        	                JOptionPane.showMessageDialog(this, "Le médicament n'a pas été trouvé.");
        	            }

        	            // Close the database connection
        	           // conn.close();
        	        } catch (SQLException ex) {
        	            ex.printStackTrace();
        	            JOptionPane.showMessageDialog(this, "Erreur lors de la recherche du médicament : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        	        }
        	    }
        	}
        else if (e.getSource() ==  clientButton)
        {
        	  // Créer un dialogue avec les options Ajouter, Modifier, Supprimer
    	    Object[] options = {"Ajouter", "Modifier", "Supprimer","consulter"};
    	    int choix = JOptionPane.showOptionDialog(this, "Que voulez-vous faire ?", "Gestion des clients", 
    	                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
    	    
    	    // Réagir en fonction du choix de l'utilisateur
    	    switch(choix) {
    	        case 0: {
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

    	        	   
    	        	

    	    
    	        
    	            
    	            break;
    	        case 1: {
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
    	    	           // conn.close();
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
    	        }
    	            
    	            break;
    	        case 2:   
    	        {
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
    	                  //  conn.close();
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
    	    
    	            break;
    	        case 3 :{
    	        	
    	        	try {
    	        	    String sql = "SELECT nom, adresse, telephone, credit FROM clients";
    	        	    PreparedStatement stmt = conn.prepareStatement(sql);
    	        	    ResultSet rs = stmt.executeQuery();

    	        	    String message = "Liste des clients :\n";
    	        	    while (rs.next()) {
    	        	        String nom = rs.getString("nom");
    	        	        String adresse = rs.getString("adresse");
    	        	        String telephone = rs.getString("telephone");
    	        	        double credit = rs.getDouble("credit");
    	        	        message += "Nom : " + nom + " | Adresse : " + adresse + " | Téléphone : " + telephone + " | Crédit : " + credit + "\n";
    	        	    }
    	        	    JOptionPane.showMessageDialog(this, message);
    	        	} catch (SQLException ex) {
    	        	    ex.printStackTrace();
    	        	    JOptionPane.showMessageDialog(this, "Erreur lors de la récupération de la liste des clients : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    	        	}

    	        	}

    	        
    	        default:
    	        {
    	        	
    	        }
    	    }	
        }
        else  if  (e.getSource() ==  medButton)
        {
        	 // Créer un dialogue avec les options Ajouter, Modifier, Supprimer
    	    Object[] options = {"Ajouter", "Modifier", "Supprimer","Consulter"};
    	    int choix = JOptionPane.showOptionDialog(this, "Que voulez-vous faire ?", "Gestion des médicaments et leur stock", 
    	                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
    	    
    	    // Réagir en fonction du choix de l'utilisateur
    	    switch(choix) {
    	        case 0: {
    	        	   String medName = JOptionPane.showInputDialog(this, "Entrez le nom du médicament à ajouter :");
    	         	   // String desm = JOptionPane.showInputDialog(this, "Entrez le description du médicament à ajouter :");
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
    	         	            //conn.close();
    	         	        } catch (SQLException ex) {
    	         	            ex.printStackTrace();
    	         	            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout du médicament : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    	         	        }
    	         	    }
    	        	}

    	        	   
    	        	

    	    
    	        
    	            
    	            break;
    	        case 1: {
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
    	    	         //   conn.close();
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
    	        }
    	            
    	            break;
    	        case 2:   
    	        {
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
    	      	           // conn.close();
    	      	        } catch (SQLException ex) {
    	      	            ex.printStackTrace();
    	      	            JOptionPane.showMessageDialog(this, "Erreur lors de la suppression du médicament : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    	      	        }
    	      	    }
    	        }
    	    
    	            break;
    	        case 3 :{
    	        	try {
    	        	    // Connect to the database

    	        	    // Prepare the SQL SELECT statement
    	        	    String sql = "SELECT nom,  prix FROM medicaments";
    	        	    PreparedStatement stmt = conn.prepareStatement(sql);

    	        	    // Execute the query and get the results
    	        	    ResultSet rs = stmt.executeQuery();

    	        	    // Build the message to display
    	        	    StringBuilder message = new StringBuilder();
    	        	    message.append("Liste des médicaments :\n\n");
    	        	    while (rs.next()) {
    	        	        String nom = rs.getString("nom");
    	        	      
    	        	        double prix = rs.getDouble("prix");

    	        	        message.append("Nom : ").append(nom).append("\n")
    	        	             
    	        	               .append("Prix : ").append(prix).append("€").append("\n\n");
    	        	    }

    	        	    // Display the message in a JOptionPane
    	        	    JOptionPane.showMessageDialog(this, message.toString(), "Liste des médicaments", JOptionPane.INFORMATION_MESSAGE);

    	        	    // Close the database connection
    	        	   // conn.close();
    	        	} catch (SQLException ex) {
    	        	    ex.printStackTrace();
    	        	    JOptionPane.showMessageDialog(this, "Erreur lors de la récupération de la liste des médicaments : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    	        	}

    	        }
    	        default:
    	        {
    	        	
    	        }
    	    }	
        }
        }
    }


