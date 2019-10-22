package mysquare.core;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class IMStart {
	
	static JFrame frame = new JFrame();
	private static JMenuBar mb = new JMenuBar();
	static JPanel mp = new JPanel();
	private static JScrollPane scrollPane;
	private static DefaultTableModel model;
	static JTextField product = new JTextField(25);
	static JTextField colour = new JTextField(25);
	static JTextField weight = new JTextField(25);

	public static void getCenterPanel() {
		model = new DefaultTableModel();
		JTable table = new JTable( model );
	    scrollPane = new JScrollPane(table);
	    scrollPane.setHorizontalScrollBarPolicy(
	    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    scrollPane.setVerticalScrollBarPolicy(
	    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
	    
	    model.addColumn("PRODUCT");
	    model.addColumn("COLOUR");
	    model.addColumn("WEIGHT");
	    model.addColumn("QUANTITY");
	}
	
	public static void changePanel(JPanel panel) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(mb, BorderLayout.NORTH);
		frame.getContentPane().add(scrollPane,BorderLayout.CENTER);
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		frame.getContentPane().doLayout();
		frame.update(frame.getGraphics());
		frame.setVisible(true);
	}
	
	public static void getBottomPanel() {
		
	}	
	
	public static void main(String[] args) { 
		frame.setTitle("IMS: Raj Blow Plast");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900,500);
		

		JMenu m2 = new JMenu("Settings");
		JMenu m3 = new JMenu("Edit");
		JMenu m4 = new JMenu("History");
	    JMenuItem m2i1 = new JMenuItem("Change Data Source");
        JMenuItem m3i1 = new JMenuItem("Update Products List");
        JMenuItem m3i2 = new JMenuItem("Update Colours List");
        JMenuItem m3i3 = new JMenuItem("Update Weights List");
        JMenuItem m4i1 = new JMenuItem("Manufactured");
        JMenuItem m4i2 = new JMenuItem("Sold");
        m2.add(m2i1);
        m3.add(m3i1);
        m3.add(m3i2);
        m3.add(m3i3);
        m4.add(m4i1);
        m4.add(m4i2);
        mb.add(m2);
        mb.add(m3);
        mb.add(m4);
        //JButton addP = new JButton("Add Product");
        //JButton addC = new JButton("Add Colour");
        //JButton addW = new JButton("Add Weight");
        
		m2i1.addActionListener(new ButtonListener());
		
        m3i1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JPanel panel = new JPanel();
				JButton home = new JButton("Home");
				JLabel lab1 = new JLabel("Enter Product: ");		
				JButton addP = new JButton("Add Product");
				panel.add(home);
				panel.add(lab1);
				panel.add(product);
				panel.add(addP);
				changePanel(panel);
				home.addActionListener(new ButtonListener());
				addP.addActionListener(new ButtonListener());
			}
		});
        
        m3i2.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JPanel panel = new JPanel();
				JButton home = new JButton("Home");
				JLabel lab2 = new JLabel("Enter Colour: ");
				JButton addC = new JButton("Add Colour");
				panel.add(home);
				panel.add(lab2);
				panel.add(colour);
				panel.add(addC);
				changePanel(panel);
				home.addActionListener(new ButtonListener());
				addC.addActionListener(new ButtonListener());
			}
		});
        
        m3i3.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {

				JPanel panel = new JPanel();
				JButton home = new JButton("Home");
				JLabel lab3 = new JLabel("Enter Weight: ");
				JButton addW = new JButton("Add Weight");
				panel.add(home);
				panel.add(lab3);
				panel.add(weight);
				panel.add(addW);
				changePanel(panel);
				home.addActionListener(new ButtonListener());
				addW.addActionListener(new ButtonListener());
			}
		});
	    
		getCenterPanel();
		
	    JComboBox<String> cb1 = new JComboBox<String>(Utility.getProductList());
	    JComboBox<String> cb2 = new JComboBox<String>(Utility.getColourList());
	    JComboBox<String> cb3 = new JComboBox<String>(Utility.getWeightList());
	       
	    JLabel lab1 = new JLabel("Product"); 
	    mp.add(lab1);
	    mp.add(cb1);
		 
	    JLabel lab2 = new JLabel("Colour");
	    mp.add(lab2);
	    mp.add(cb2);
		
	    JLabel lab3 = new JLabel("Weight");  
	    mp.add(lab3);
		mp.add(cb3); // Components Added using Flow Layout
	    
	    JLabel lab4 = new JLabel("Quantity");
	    JTextField tf1 = new JTextField(5);
	    mp.add(lab4);
	    mp.add(tf1);
	    
	    JButton addBtn = new JButton("Add");
	    JButton sellBtn = new JButton("Sell");
	    mp.add(addBtn);
	    mp.add(sellBtn);
	    
	  //Adding Components to the frame.
	    frame.getContentPane().add(BorderLayout.SOUTH, mp);
	    frame.getContentPane().add(BorderLayout.NORTH, mb);
	    frame.getContentPane().add(BorderLayout.CENTER, scrollPane);
	    frame.setVisible(true);
	       
	    ResultSet data = null;
	 
	    addBtn.addActionListener(new ActionListener() {	
	    	@Override
	    	public void actionPerformed(ActionEvent arg0) {
	    		String product = cb1.getSelectedItem().toString();
	    		String colour = cb2.getSelectedItem().toString();
	    		String weight = cb3.getSelectedItem().toString();
	    		int qty = Integer.parseInt(tf1.getText());
	    		try {
	    			ResultSet dataNew = Db.addProduct(product, colour, weight, qty);
	    			model.setRowCount(0);
	    			while(dataNew.next()) {
	    				model.addRow(new Object[]{dataNew.getString("pname"), dataNew.getString("pclr"), dataNew.getString("pwt"), dataNew.getString("pqt")});
	    			}
	    		} catch (Exception e) {
	    			JOptionPane.showMessageDialog(frame,"Unable to add product.\nERROR:"+e.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
	    		}
	    	}
	    });
	    
	    sellBtn.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent arg0) {
	    		String product = cb1.getSelectedItem().toString();
	    		String colour = cb2.getSelectedItem().toString();
				String weight = cb3.getSelectedItem().toString();
				int qty = Integer.parseInt(tf1.getText());
				try {
					ResultSet dataNew = Db.sellProduct(product, colour, weight, qty);
					model.setRowCount(0);
					while(dataNew.next()) {
						model.addRow(new Object[]{dataNew.getString("pname"), dataNew.getString("pclr"), dataNew.getString("pwt"), dataNew.getString("pqt")});
				       }
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame,"Unable to add product.\nERROR:"+e.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
	          
	    try {
	    	data = Db.fetchData();
	    	while(data.next()) {
	    		model.addRow(new Object[]{data.getString("pname"), data.getString("pclr"), data.getString("pwt"), data.getString("pqt")});  
	    	}
	    } catch (Exception e) {
	    	JOptionPane.showMessageDialog(frame,"Unable to load data.\nERROR:"+e.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE); 
	    }
	}
}

class ButtonListener implements ActionListener{
	ButtonListener(){
	}
	
	public void actionPerformed(ActionEvent e) {
		String btnCode = e.getActionCommand().toString();
		if(btnCode.equals("Change Data Source")) {
			String str = JOptionPane.showInputDialog("Enter New Data Source:");
			//Utility.setSource(str);
			System.out.print(str);
		}
		if(btnCode.equals("Home")) {
			IMStart.changePanel(IMStart.mp);
		}
		if(btnCode.equals("Add Product")) {
			try {
				Db.updateList("product_list", IMStart.product.getText().toString());
			} catch (Exception err) {
				JOptionPane.showConfirmDialog(IMStart.frame, "Product already added.\nERROR:"+err.getMessage(),"WARNING",JOptionPane.WARNING_MESSAGE );
			}
		}
		if(btnCode.equals("Add Colour")) {
			try {
				Db.updateList("colour_list", IMStart.colour.getText().toString());
			} catch (Exception err) {
				JOptionPane.showConfirmDialog(IMStart.frame, "Colour already added.\nERROR:"+err.getMessage(),"WARNING",JOptionPane.WARNING_MESSAGE );
			}
		}
		if(btnCode.equals("Add Weight")) {
			try {
				Db.updateList("weight_list", IMStart.weight.getText().toString());
			} catch (Exception err) {
				JOptionPane.showConfirmDialog(IMStart.frame, "Weight already added.\nERROR:"+err.getMessage(),"WARNING",JOptionPane.WARNING_MESSAGE );
			}
		}
	}
}