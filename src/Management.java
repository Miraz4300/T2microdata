
import java.awt.Dimension;
import java.awt.Toolkit;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SUBZERO FT. Miraz Hossain (miraz4300@gmail.com)
 */
public class Management extends javax.swing.JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
	ResultSet rs = null;
        private String v = "";
        private int E_ID = 0;
        private String combo = "";

    /**
     * Creates new form Management
     */
    public Management() {
        initComponents();
        conn = SQLConnection.ConnecrDb();
        loadTable();
        centerize();
        loadCB();
        loadCBClick();
        loadList();
        date();
        clock();
        T2Instance fj = new T2Instance();
		JOptionPane.showMessageDialog(null, "Welcome "+ fj.WelcomeName);
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
    
    
 
     
     
      private void date() {
		Thread clock = new Thread() {
			public void run() {
				try {
					for (;;) {
						Calendar cal = new GregorianCalendar();
						SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
						java.util.Date date = cal.getTime();
						String timeString = formatter.format(date);
						dateLabel.setText(timeString);
                                                //clockLabel1.setText(timeString);
						sleep(1000);
					}
                                        
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		clock.start();
	}
     
     
     
    
      
      
      
      private void clock() {
		Thread clock = new Thread() {
			public void run() {
				try {
					for (;;) {
						Calendar cal = new GregorianCalendar();
						SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss a");
						java.util.Date date = cal.getTime();
						String timeString = formatter.format(date);
						timeLabel.setText(timeString);
                                                //clockLabel1.setText(timeString);
						sleep(1000);
					}
                                        
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		clock.start();
	}
      
      
      
      
      
      
      
    
    private void loadTable() {
		try {
			String query = "SELECT * FROM Employee";
			pst = (PreparedStatement) conn.prepareStatement(query);
			rs = pst.executeQuery();
			dataTable.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

    
    
    
    
    private void addData() {
		
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
			JOptionPane.showMessageDialog(null, "New Data Added!!");
			
			resetEverything();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
    
    
    
    
    
    
    
    private void loadToField() {
		try {
			int row = dataTable.getSelectedRow();
			String ID = (dataTable.getModel().getValueAt(row, 10)).toString();
			String query = "Select * FROM Employee WHERE E_ID = '"+ID+"' ";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				E_ID = rs.getInt("E_ID");
				
				nameTF.setText(rs.getString("E_Name"));
				emailTF.setText(rs.getString("E_Email"));
				usernameTF.setText(rs.getString("E_username"));
				passField.setText(rs.getString("E_Password"));
				ageTF.setText(rs.getString("E_Age"));
				contactTF.setText(rs.getString("E_Contact"));
				
				v = rs.getString("E_Gender");
				
				if(v.equals("Male")) {
					rdbtnMale.setSelected(true);
					rdbtnFemale.setSelected(false);
				}else if(v.equals("Female")) {
					rdbtnFemale.setSelected(true);
					rdbtnMale.setSelected(false);
				}else {
					System.out.println("");
				}
				
				
				heightTF.setText(rs.getString("E_Height"));
				
				combo = rs.getString("E_JobTitle");
				if(combo.equals("Elastic Computing")) {
					JobTitleCB.setSelectedItem("Elastic Computing");
				}else if(combo.equals("Elastic Storage")) {
					JobTitleCB.setSelectedItem("Elastic Storage");
				}else if(combo.equals("Sagemaker")) {
					JobTitleCB.setSelectedItem("Sagemaker");
					
				}else if(combo.equals("Internet Of Things")) {
					JobTitleCB.setSelectedItem("Internet Of Things");
					
				}else if(combo.equals("VPC")) {
					JobTitleCB.setSelectedItem("VPC");
					
				}else if(combo.equals("Asian-Region Infrustructer")) {
					JobTitleCB.setSelectedItem("Asian-Region Infrustructer");
				}
				else {
					System.out.println("");
				}
				
				
				
			}
			pst.close();
			rs.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
    
    
    
    
   private void updateData() {
		try {
			String query = "UPDATE Employee SET E_Name = '"+nameTF.getText()+"',E_Email = '"+emailTF.getText()+"',E_Username = '"+usernameTF.getText()+"',E_Password = '"+passField.getText()+"',E_Age = '"+ageTF.getText()+"',E_Contact = '"+contactTF.getText()+"',E_Gender = '"+v+"',E_Height = '"+heightTF.getText()+"',E_JobTitle = '"+JobTitleCB.getSelectedItem().toString()+"' WHERE E_ID = '"+E_ID+"'";
			pst = conn.prepareStatement(query);
			pst.execute();
			
			JOptionPane.showMessageDialog(null, "Data Updated Successfully");
			pst.close();
			resetEverything();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
   }
    
    
    
    
    
   
   private void deleteData() {
		try {
			int action = JOptionPane.showConfirmDialog(null,"Are you sure to DELETE??","Delete",JOptionPane.YES_NO_CANCEL_OPTION);
			if (action == 0 ) {
				String query = "DELETE FROM Employee WHERE E_ID = '"+E_ID+"'";
				pst = conn.prepareStatement(query);
				pst.execute();
				JOptionPane.showMessageDialog(null, "Deleted Successfully");
				pst.close();
				resetEverything();
                                
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
   
   
   
   
   
   private void searchData() {
		try {
			String selection = (String) searchCB.getSelectedItem();

			String query = "SELECT E_Name,E_Email,E_Username,E_Password,E_Age,E_Contact,E_Gender,E_Height,E_JobTitle,E_ID FROM Employee WHERE "
					+ selection + " LIKE '" + searchTF.getText() + "%'";

			pst = conn.prepareStatement(query);
			// pst.setString(1, searchTF.getText());
			rs = pst.executeQuery();
			dataTable.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
   
   
   
   
   private void resetEverything() {
		nameTF.setText(null);
		emailTF.setText(null);
		usernameTF.setText(null);
		passField.setText(null);
		contactTF.setText(null);
		buttonGroup1.clearSelection();
		heightTF.setText(null);
		JobTitleCB.setSelectedItem("-");
		searchCB.setSelectedItem("-");
		searchTF.setText(null);
		ageTF.setText(null);
		contactTF.setText(null);
		loadTable();

	}
   
   
   
   
   
   private void loadCB() {
		try {

			String query = "SELECT E_Name FROM Employee";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				dataCB.addItem(rs.getString("E_Name"));

			}
			pst.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
   
   
   
   
   
   
   private void loadCBClick() {

		try {

			String query = "Select * FROM Employee WHERE E_Name= ?";
			pst = conn.prepareStatement(query);

			pst.setString(1, (String) dataCB.getSelectedItem());
			
			rs = pst.executeQuery();
			while(rs.next()) {
				E_ID = rs.getInt("E_ID");
				
				nameTF.setText(rs.getString("E_Name"));
				emailTF.setText(rs.getString("E_Email"));
				usernameTF.setText(rs.getString("E_username"));
				passField.setText(rs.getString("E_Password"));
				ageTF.setText(rs.getString("E_Age"));
				contactTF.setText(rs.getString("E_Contact"));
				
				v = rs.getString("E_Gender");
				
				if(v.equals("Male")) {
					rdbtnMale.setSelected(true);
					rdbtnFemale.setSelected(false);
				}else if(v.equals("Female")) {
					rdbtnFemale.setSelected(true);
					rdbtnMale.setSelected(false);
				}else {
					System.out.println("");
				}
				
				
				heightTF.setText(rs.getString("E_Height"));
				
				combo = rs.getString("E_JobTitle");
				if(combo.equals("Elastic Computing")) {
					JobTitleCB.setSelectedItem("Elastic Computing");
				}else if(combo.equals("Elastic Storage")) {
					JobTitleCB.setSelectedItem("Elastic Storage");
				}else if(combo.equals("Sagemaker")) {
					JobTitleCB.setSelectedItem("Sagemaker");
					
				}else if(combo.equals("Internet Of Things")) {
					JobTitleCB.setSelectedItem("Internet Of Things");
					
				}else if(combo.equals("VPC")) {
					JobTitleCB.setSelectedItem("VPC");
					
				}else if(combo.equals("Asian-Region Infrustructer")) {
					JobTitleCB.setSelectedItem("Asian-Region Infrustructer");
				}
				else {
					System.out.println("");
				}
				
				
				
			}
			pst.close();
			rs.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	
	}
   
   
   
   
   private void loadList() {

		try {
			DefaultListModel dlm = new DefaultListModel();

			String query = "SELECT E_JobTitle FROM Employee";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {

				dlm.addElement(rs.getString("E_JobTitle"));

			}
			JobTitleList.setModel(dlm);
			pst.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
   
   
   
   
   
   
   
   private void loadListClick() {
		try {

			String query = "Select * FROM Employee WHERE E_JobTitle= ?";
			pst = conn.prepareStatement(query);

			pst.setString(1, (String) JobTitleList.getSelectedValue());
			
			rs = pst.executeQuery();
			while(rs.next()) {
				E_ID = rs.getInt("E_ID");
				
				nameTF.setText(rs.getString("E_Name"));
				emailTF.setText(rs.getString("E_Email"));
				usernameTF.setText(rs.getString("E_username"));
				passField.setText(rs.getString("E_Password"));
				ageTF.setText(rs.getString("E_Age"));
				contactTF.setText(rs.getString("E_Contact"));
				
				v = rs.getString("E_Gender");
				
				if(v.equals("Male")) {
					rdbtnMale.setSelected(true);
					rdbtnFemale.setSelected(false);
				}else if(v.equals("Female")) {
					rdbtnFemale.setSelected(true);
					rdbtnMale.setSelected(false);
				}else {
					System.out.println("");
				}
				
				
				heightTF.setText(rs.getString("E_Height"));
				
				combo = rs.getString("E_JobTitle");
				if(combo.equals("Elastic Computing")) {
					JobTitleCB.setSelectedItem("Elastic Computing");
				}else if(combo.equals("Elastic Storage")) {
					JobTitleCB.setSelectedItem("Elastic Storage");
				}else if(combo.equals("Sagemaker")) {
					JobTitleCB.setSelectedItem("Sagemaker");
					
				}else if(combo.equals("Internet Of Things")) {
					JobTitleCB.setSelectedItem("Internet Of Things");
					
				}else if(combo.equals("VPC")) {
					JobTitleCB.setSelectedItem("VPC");
					
				}else if(combo.equals("Asian-Region Infrustructer")) {
					JobTitleCB.setSelectedItem("Asian-Region Infrustructer");
				}
				else {
					System.out.println("");
				}
				
				
				
			}
			pst.close();
			rs.close();
			
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnNewButton = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        nameTF = new javax.swing.JTextField();
        emailTF = new javax.swing.JTextField();
        passField = new javax.swing.JTextField();
        heightTF = new javax.swing.JTextField();
        contactTF = new javax.swing.JTextField();
        usernameTF = new javax.swing.JTextField();
        ageTF = new javax.swing.JTextField();
        JobTitleCB = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        rdbtnFemale = new javax.swing.JRadioButton();
        rdbtnMale = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        searchCB = new javax.swing.JComboBox<>();
        searchTF = new javax.swing.JTextField();
        dataCB = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        JobTitleList = new javax.swing.JList<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        dateLabel = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(65, 67, 69));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_maintenance_25px.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 22, -1, -1));

        btnAdd.setBackground(new java.awt.Color(204, 204, 204));
        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.setOpaque(false);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel1.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 284, 71, -1));

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.setOpaque(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 325, 71, -1));

        btnNewButton.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        btnNewButton.setText("Delete");
        btnNewButton.setOpaque(false);
        btnNewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewButtonActionPerformed(evt);
            }
        });
        jPanel1.add(btnNewButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 366, 71, -1));

        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        btnReset.setText("Reset");
        btnReset.setOpaque(false);
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        jPanel1.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 656, 71, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 90, 690));

        jPanel2.setBackground(new java.awt.Color(0, 150, 136));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("MANAGEMENT");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 0, -1, -1));

        btnLogout.setBackground(new java.awt.Color(204, 204, 204));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 0, 51));
        btnLogout.setText("LogOut");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jPanel2.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 0, 80, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 30));

        jPanel3.setBackground(new java.awt.Color(84, 110, 122));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dataTable.setBackground(new java.awt.Color(204, 204, 204));
        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        dataTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dataTable);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 511, 1240, 168));
        jPanel3.add(nameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 280, -1));

        emailTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTFActionPerformed(evt);
            }
        });
        jPanel3.add(emailTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 280, -1));
        jPanel3.add(passField, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 280, -1));
        jPanel3.add(heightTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, 280, -1));
        jPanel3.add(contactTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 390, 280, -1));
        jPanel3.add(usernameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 280, -1));
        jPanel3.add(ageTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 280, -1));

        JobTitleCB.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        JobTitleCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elastic Computing", "Elastic Storage", "Sagemaker", "Internet Of Things", "VPC", "Asian-Region Infrustructer" }));
        jPanel3.add(JobTitleCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 430, 280, -1));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, 40, 430));

        jLabel3.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Name");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, -1, -1));

        jLabel4.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Email");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, -1, -1));

        jLabel5.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Username");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, -1, -1));

        jLabel6.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Password");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, -1, -1));

        jLabel7.setFont(new java.awt.Font("Microsoft JhengHei Light", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Age");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, -1, -1));

        jLabel8.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Height");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, -1, -1));

        jLabel9.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Contact");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, -1, -1));

        jLabel10.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Job");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 430, -1, -1));

        rdbtnFemale.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdbtnFemale);
        rdbtnFemale.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 11)); // NOI18N
        rdbtnFemale.setForeground(new java.awt.Color(255, 255, 255));
        rdbtnFemale.setText("Female");
        rdbtnFemale.setOpaque(false);
        rdbtnFemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnFemaleActionPerformed(evt);
            }
        });
        jPanel3.add(rdbtnFemale, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 310, -1, -1));

        buttonGroup1.add(rdbtnMale);
        rdbtnMale.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 11)); // NOI18N
        rdbtnMale.setForeground(new java.awt.Color(255, 255, 255));
        rdbtnMale.setText("Male");
        rdbtnMale.setOpaque(false);
        jPanel3.add(rdbtnMale, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 310, 60, -1));

        jLabel11.setFont(new java.awt.Font("Microsoft JhengHei Light", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Gender");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 260, -1, -1));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 280, 50, 20));

        searchCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "E_Name", "E_Email", "E_Username", "E_Age", "E_Contact", "E_Gender", "E_Height", "E_JobTitle" }));
        jPanel3.add(searchCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 280, 100, -1));

        searchTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTFKeyReleased(evt);
            }
        });
        jPanel3.add(searchTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 280, 150, -1));

        dataCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Name" }));
        dataCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataCBActionPerformed(evt);
            }
        });
        jPanel3.add(dataCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 240, 260, -1));

        JobTitleList.setBackground(new java.awt.Color(204, 204, 204));
        JobTitleList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JobTitleListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JobTitleList);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 130, 160, 320));

        jLabel12.setFont(new java.awt.Font("Deluce Free", 1, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("INFORMATION");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, -1, -1));

        jLabel13.setFont(new java.awt.Font("Deluce Free", 1, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("QUERY");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 30, -1, -1));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 190, 20));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 70, 80, 20));

        dateLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        dateLabel.setForeground(new java.awt.Color(255, 255, 255));
        dateLabel.setText("current date");
        jPanel3.add(dateLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 490, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Current Date:");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, -1, -1));

        timeLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        timeLabel.setForeground(new java.awt.Color(255, 255, 255));
        timeLabel.setText("current time");
        jPanel3.add(timeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 490, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Current Time:");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 490, -1, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 1260, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void emailTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTFActionPerformed

    private void rdbtnFemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnFemaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbtnFemaleActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        addData();
    }//GEN-LAST:event_btnAddActionPerformed

    private void dataTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataTableMouseClicked
        // TODO add your handling code here:
        loadToField();
    }//GEN-LAST:event_dataTableMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        updateData();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnNewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewButtonActionPerformed
        // TODO add your handling code here:
        deleteData();
    }//GEN-LAST:event_btnNewButtonActionPerformed

    private void searchTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTFKeyReleased
        // TODO add your handling code here:
        searchData();
    }//GEN-LAST:event_searchTFKeyReleased

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        resetEverything();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        T2Instance fj = new T2Instance();
				fj.setVisible(true);
				dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void dataCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataCBActionPerformed
        // TODO add your handling code here:
        loadCBClick();
    }//GEN-LAST:event_dataCBActionPerformed

    private void JobTitleListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JobTitleListMouseClicked
        // TODO add your handling code here:
        loadListClick();
    }//GEN-LAST:event_JobTitleListMouseClicked

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
            java.util.logging.Logger.getLogger(Management.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Management.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Management.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Management.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Management().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JobTitleCB;
    private javax.swing.JList<String> JobTitleList;
    private javax.swing.JTextField ageTF;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnNewButton;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField contactTF;
    private javax.swing.JComboBox<String> dataCB;
    private javax.swing.JTable dataTable;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JTextField emailTF;
    private javax.swing.JTextField heightTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField nameTF;
    private javax.swing.JTextField passField;
    private javax.swing.JRadioButton rdbtnFemale;
    private javax.swing.JRadioButton rdbtnMale;
    private javax.swing.JComboBox<String> searchCB;
    private javax.swing.JTextField searchTF;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JTextField usernameTF;
    // End of variables declaration//GEN-END:variables
}
