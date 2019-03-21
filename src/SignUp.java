
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author SUBZERO FT. Miraz Hossain (miraz4300@gmail.com)
 */
public class SignUp extends javax.swing.JFrame {
    //private JTextField nameTF;
	//private JTextField emailTF;
	//private JTextField usernameTF;
	//private JPasswordField passField;
	//private JTextField ageTF;
	//private JTextField contactTF;
	//private JTextField heightTF;
	//private JRadioButton rdbtnMale,rdbtnFemale;
	//private JComboBox JobTitleCB;
        //private JCheckBox chckbxIAcceptAll;
        
        Connection conn = null;
    PreparedStatement pst = null;
	ResultSet rs = null;
        String v ="";

    /**
     * Creates new form Home
     */
    public SignUp() {
        initComponents();
        conn = SQLConnection.ConnecrDb();
        centerize();
    }
    
    
    
    public void centerize() {
		Dimension screenSize, frameSize;
		int x, y;
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frameSize = getSize();
		x = (screenSize.width - frameSize.width) / 2;
		y = (screenSize.height - frameSize.height) / 2;
		setLocation(x, y);
	}
    
    
    
    private void register() {
		
		try {
			String query = "INSERT INTO Employee(E_Name,E_Email,E_Username,E_Password,E_Age,E_Contact,E_Gender,E_Height,E_JobTitle ) VALUES(?,?,?,?,?,?,?,?,?)";
			pst = (PreparedStatement) conn.prepareStatement(query);
			//pst.setString(1, idTF.getText());
			pst.setString(1, nameTF.getText());
			pst.setString(2, emailTF.getText());
			pst.setString(3, usernameTF.getText());
			pst.setString(4, passField.getText());
			pst.setString(5, ageTF.getText());
			pst.setString(6, contactTF.getText());
			
			if(rdbtnMale.isSelected()) {
				 v = rdbtnMale.getText().toString();
			}
			else if(rdbtnFemale.isSelected()) {
				 v = rdbtnMale.getText().toString();
			}else {
				JOptionPane.showMessageDialog(null, "Select Gender");
			}
			
			pst.setString(7, String.valueOf(v));
			pst.setString(8, heightTF.getText());
			pst.setString(9, JobTitleCB.getSelectedItem().toString());
			
			pst.execute();
			pst.close();
			JOptionPane.showMessageDialog(null, "Successfully signed up");
			T2Instance fj = new T2Instance();
			fj.setVisible(true);
			dispose();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jPanel1 = new javax.swing.JPanel();
        nameTF = new javax.swing.JTextField();
        emailTF = new javax.swing.JTextField();
        usernameTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        passField = new javax.swing.JPasswordField();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        btnRegister = new javax.swing.JButton();
        ageTF = new javax.swing.JTextField();
        contactTF = new javax.swing.JTextField();
        heightTF = new javax.swing.JTextField();
        rdbtnFemale = new javax.swing.JRadioButton();
        rdbtnMale = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        JobTitleCB = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        chckbxIAcceptAll = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        kGradientPanel1.setkBorderRadius(0);
        kGradientPanel1.setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nameTF.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        nameTF.setForeground(new java.awt.Color(102, 102, 102));
        nameTF.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        jPanel1.add(nameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 94, 380, -1));

        emailTF.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        emailTF.setForeground(new java.awt.Color(102, 102, 102));
        emailTF.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        jPanel1.add(emailTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 147, 380, -1));

        usernameTF.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        usernameTF.setForeground(new java.awt.Color(102, 102, 102));
        usernameTF.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        jPanel1.add(usernameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 205, 380, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("<html> Sign Up <br> For AWS account </html>");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 11, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Name");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 69, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Email");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 127, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("Username");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 180, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setText("Password");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 238, -1, -1));

        passField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        passField.setForeground(new java.awt.Color(102, 102, 102));
        passField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        jPanel1.add(passField, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 263, 380, -1));

        kGradientPanel2.setLayout(null);

        btnRegister.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRegister.setText("REGISTER");
        btnRegister.setOpaque(false);
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });
        kGradientPanel2.add(btnRegister);
        btnRegister.setBounds(120, 10, 160, 25);

        jPanel1.add(kGradientPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 621, -1, 44));

        ageTF.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ageTF.setForeground(new java.awt.Color(102, 102, 102));
        ageTF.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        jPanel1.add(ageTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 318, 380, -1));

        contactTF.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        contactTF.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        contactTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactTFActionPerformed(evt);
            }
        });
        jPanel1.add(contactTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 376, 380, -1));

        heightTF.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        heightTF.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        jPanel1.add(heightTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 474, 380, -1));

        buttonGroup1.add(rdbtnFemale);
        rdbtnFemale.setFont(new java.awt.Font("Microsoft JhengHei Light", 0, 11)); // NOI18N
        rdbtnFemale.setText("Female");
        jPanel1.add(rdbtnFemale, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 415, -1, -1));

        buttonGroup1.add(rdbtnMale);
        rdbtnMale.setFont(new java.awt.Font("Microsoft JhengHei Light", 0, 11)); // NOI18N
        rdbtnMale.setText("Male");
        jPanel1.add(rdbtnMale, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 415, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 153, 153));
        jLabel12.setText("Age");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 298, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 153, 153));
        jLabel13.setText("Contact");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 351, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(153, 153, 153));
        jLabel14.setText("Gender");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 417, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(153, 153, 153));
        jLabel15.setText("Height");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 449, -1, -1));

        JobTitleCB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JobTitleCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elastic Computing", "Elastic Storage", "Sagemaker", "Internet Of Things", "VPC", "Asian-Region Infrustructer" }));
        JobTitleCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JobTitleCBActionPerformed(evt);
            }
        });
        jPanel1.add(JobTitleCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 532, 380, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(153, 153, 153));
        jLabel16.setText("JobTitle");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 507, -1, -1));

        chckbxIAcceptAll.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        chckbxIAcceptAll.setText("        Read Terms & Conditions");
        chckbxIAcceptAll.setOpaque(false);
        chckbxIAcceptAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chckbxIAcceptAllActionPerformed(evt);
            }
        });
        jPanel1.add(chckbxIAcceptAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 580, 380, 20));

        kGradientPanel1.add(jPanel1);
        jPanel1.setBounds(740, 30, 520, 690);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("<html>AWS is a secure cloud services platform <br>offering compute power, database storage, content delivery and other functionality <br> to help businesses scale and grow ");
        kGradientPanel1.add(jLabel1);
        jLabel1.setBounds(130, 220, 370, 150);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 153, 255));
        jLabel4.setText("MANAGEMENT SOFTWARE");
        kGradientPanel1.add(jLabel4);
        jLabel4.setBounds(130, 170, 240, 40);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/grap.png"))); // NOI18N
        kGradientPanel1.add(jLabel9);
        jLabel9.setBounds(110, 410, 360, 200);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("T2microData");
        kGradientPanel1.add(jLabel11);
        jLabel11.setBounds(120, 50, 200, 32);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_fidget_spinner_filled_50px.png"))); // NOI18N
        kGradientPanel1.add(jLabel2);
        jLabel2.setBounds(60, 40, 50, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1288, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void contactTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contactTFActionPerformed

    private void JobTitleCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JobTitleCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JobTitleCBActionPerformed

    private void chckbxIAcceptAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chckbxIAcceptAllActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chckbxIAcceptAllActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        // TODO add your handling code here:
        if(chckbxIAcceptAll.isSelected()) {
					register();
				}else {
					JOptionPane.showMessageDialog(null, "Read Terms & Conditions");
				}
    }//GEN-LAST:event_btnRegisterActionPerformed

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
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignUp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JobTitleCB;
    private javax.swing.JTextField ageTF;
    private javax.swing.JButton btnRegister;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chckbxIAcceptAll;
    private javax.swing.JTextField contactTF;
    private javax.swing.JTextField emailTF;
    private javax.swing.JTextField heightTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel2;
    private javax.swing.JTextField nameTF;
    private javax.swing.JPasswordField passField;
    private javax.swing.JRadioButton rdbtnFemale;
    private javax.swing.JRadioButton rdbtnMale;
    private javax.swing.JTextField usernameTF;
    // End of variables declaration//GEN-END:variables
}