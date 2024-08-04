/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaapplication5;
import java.awt.HeadlessException;
import javaapplication5.Employe;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author James
 */
public class FrontEnd extends javax.swing.JFrame {

    /**
     * Creates new form FrontEnd
     */
    
    private static String sendGetRequest(String url) throws IOException {
        HttpURLConnection connection = null;
        StringBuilder response = new StringBuilder();

        try {
            URL apiUrl = new URL(url);
            connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return response.toString();
    }
    
    private void updateTable(List<Employe> employes) {
        DefaultTableModel model = new DefaultTableModel(new String[]{"NumEmp", "Nom", "salaire","Observation"}, 0);
        for (Employe employe : employes) {
            model.addRow(new Object[]{employe.getId(), employe.getName(), employe.getSalaire(), employe.getObservation()});
        }
        TableEmp.setModel(model);
    }
    
     private void refreshData() {
        try {
          
            List<Employe> employes = showData();
            updateTable(employes);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to retrieve data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
     
     
     
     private List<Employe> showData() throws IOException{
     
     String url = "http://localhost:8083/JAX-RS_API/api/users/list/";
        String jsonResponse = sendGetRequest(url);
        System.out.println(jsonResponse);
        Gson gson = new Gson();
        List<Employe> employes = gson.fromJson(jsonResponse, new TypeToken<List<Employe>>(){}.getType());
        
        updateTable(employes);
        for (Employe employe : employes) {
            System.out.println(employe); // Afficher chaque employé
        }
        
        return employes;
     }
     
     private String GetValSalary() throws IOException{
         
        String url = "http://localhost:8083/JAX-RS_API/api/users/salary";
        String jsonResponse = sendGetRequest(url);
        System.out.println(jsonResponse);
        String strAvecRetourLigne = jsonResponse.replace("Salaire", "\nSalaire");
        System.out.println(strAvecRetourLigne);
        
        return strAvecRetourLigne;
     }
    
     
     
     private void RefreshSalary() throws IOException{ 
     GetValSalary();
     TextValSal.setText(GetValSalary());
     }
     
     
    public FrontEnd() throws IOException {
        initComponents();
        
      showData();
      TextValSal.setText(GetValSalary());
      RefreshSalary();
      TableEmp.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){
            int selectedRow = TableEmp.getSelectedRow();

            if(selectedRow != -1) {
                Object numEmpObj = TableEmp.getValueAt(selectedRow, 0);
                Object nameObj = TableEmp.getValueAt(selectedRow, 1);
                Object salaireObj = TableEmp.getValueAt(selectedRow, 2);

                if(numEmpObj instanceof Integer && nameObj instanceof String && salaireObj instanceof Integer) {
                    int numEmpVal = (int) numEmpObj;
                    String name = (String) nameObj;
                    int salaireVal = (int) salaireObj;

                    TextFieldid.setText(String.valueOf(numEmpVal));
                    TextFieldName.setText(name);
                    TextFieldSalaire.setText(String.valueOf(salaireVal));
                }
            }
        }
            }
      });
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TextFieldid = new javax.swing.JTextField();
        TextFieldSalaire = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ButtonDetail = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        TextFieldName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableEmp = new javax.swing.JTable();
        ButtonAdd = new javax.swing.JButton();
        ButtonUpdate = new javax.swing.JButton();
        ButtonDelete = new javax.swing.JButton();
        TextValSal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TextFieldid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldidActionPerformed(evt);
            }
        });
        getContentPane().add(TextFieldid, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 150, 30));
        getContentPane().add(TextFieldSalaire, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 150, 30));

        jLabel1.setText("Salaire");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 40, 30));

        jLabel2.setText("N° Employee ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 70, 30));

        ButtonDetail.setText("Details");
        ButtonDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDetailActionPerformed(evt);
            }
        });
        getContentPane().add(ButtonDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 90, 30));

        jLabel3.setText("Nom");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 40, -1));

        TextFieldName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldNameActionPerformed(evt);
            }
        });
        getContentPane().add(TextFieldName, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 150, 30));

        TableEmp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Num_Emp", "Nom", "Salaire", "Observation"
            }
        ));
        jScrollPane1.setViewportView(TableEmp);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 67, -1, 330));

        ButtonAdd.setText("Ajouter");
        ButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAddActionPerformed(evt);
            }
        });
        getContentPane().add(ButtonAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, -1, 30));

        ButtonUpdate.setText("Modifier");
        ButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(ButtonUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, -1, 30));

        ButtonDelete.setText("Supprimer");
        ButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(ButtonDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 90, 30));

        TextValSal.setBackground(new java.awt.Color(255, 51, 102));
        getContentPane().add(TextValSal, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 440, 730, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TextFieldidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldidActionPerformed

    private void ButtonDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDetailActionPerformed
               
    int id = Integer.parseInt(TextFieldid.getText());
        int salaire = Integer.parseInt(TextFieldSalaire.getText());

        try {
            // Construire l'URL avec les paramètres appropriés
            String urlString = "http://localhost:8083/JAX-RS_API/api/users/list/" + id;
            String jsonResponse = sendGetRequest(urlString);
                System.out.println(jsonResponse);
                Gson gson = new Gson();
                /*List<Employe> employes = gson.fromJson(jsonResponse, new TypeToken<List<Employe>>(){}.getType());
                */
                
                Employe employe = gson.fromJson(jsonResponse, Employe.class);

                


                StringBuilder message = new StringBuilder();
            message.append("Detail employés :\n\n");
        
      
        

            // Envoyer la requête
            
            if (employe instanceof Object) {

                
                    message.append("ID : ").append(employe.getId()).append("\n");
                    message.append("Nom : ").append(employe.getName()).append("\n");
                    message.append("Salaire : ").append(employe.getSalaire()).append("\n\n");
                    message.append("Observation : ").append(employe.getObservation()).append("\n\n");

                
                    JOptionPane.showMessageDialog(null, message.toString(), "Liste des Employés", JOptionPane.INFORMATION_MESSAGE);
                    TextFieldid.setText("");
                    TextFieldName.setText("");
                    TextFieldSalaire.setText(String.valueOf(""));
                    RefreshSalary();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to Delete ");
            }

           
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
       

    }//GEN-LAST:event_ButtonDetailActionPerformed

    private void TextFieldNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldNameActionPerformed

    private void ButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAddActionPerformed
        String name = TextFieldName.getText();
        int salaire = Integer.parseInt(TextFieldSalaire.getText());

        try {
            // Construire l'URL avec les paramètres appropriés
            String urlString = "http://localhost:8083/JAX-RS_API/api/users/insert?name=" + name + "&salaire=" + salaire;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Envoyer la requête
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(null, "Employe added successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update user. HTTP error code: " + responseCode);
            }

            conn.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
        try {
            RefreshSalary();
        } catch (IOException ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshData();
                    TextFieldid.setText("");
                    TextFieldName.setText("");
                    TextFieldSalaire.setText(String.valueOf(""));
        
    
    }//GEN-LAST:event_ButtonAddActionPerformed

    private void ButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonUpdateActionPerformed
        int id = Integer.parseInt(TextFieldid.getText());
        int salaire = Integer.parseInt(TextFieldSalaire.getText());
        String name = TextFieldName.getText();
        
        try {
            // Construire l'URL avec les paramètres appropriés
            String urlString = "http://localhost:8083/JAX-RS_API/api/users/update/"+id+"?name=" + name + "&salaire=" + salaire  ;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Envoyer la requête
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(null, "Employee updated successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update user. HTTP error code: " + responseCode);
            }

            conn.disconnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
        
        refreshData();
        TextFieldid.setText("");
                    TextFieldName.setText("");
                    TextFieldSalaire.setText(String.valueOf(""));
        try {
            RefreshSalary();
        } catch (IOException ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ButtonUpdateActionPerformed

    private void ButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDeleteActionPerformed
        int id = Integer.parseInt(TextFieldid.getText());
        int salaire = Integer.parseInt(TextFieldSalaire.getText());

        try {
            // Construire l'URL avec les paramètres appropriés
            String urlString = "http://localhost:8083/JAX-RS_API/api/users/delete/" + id;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Envoyer la requête
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(null, "Employe Deleted successfully");
                refreshData();
                    TextFieldid.setText("");
                    TextFieldName.setText("");
                    TextFieldSalaire.setText(String.valueOf(""));
                RefreshSalary();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to Delete user. HTTP error code: " + responseCode);
            }

            conn.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_ButtonDeleteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrontEnd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrontEnd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrontEnd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrontEnd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrontEnd().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAdd;
    private javax.swing.JButton ButtonDelete;
    private javax.swing.JButton ButtonDetail;
    private javax.swing.JButton ButtonUpdate;
    private javax.swing.JTable TableEmp;
    private javax.swing.JTextField TextFieldName;
    private javax.swing.JTextField TextFieldSalaire;
    private javax.swing.JTextField TextFieldid;
    private javax.swing.JLabel TextValSal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
