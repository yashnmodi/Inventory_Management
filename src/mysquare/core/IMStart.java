package mysquare.core;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class IMStart {
	
	public static JFrame frame = new JFrame();
	public static JMenuBar mb = new JMenuBar();
	public static JMenu m1,m2,m3,m4;
	public static JMenuItem m2i1, m2i2, m2i3, m3i1;
	public static JScrollPane jScrollPane;


	IMStart(){
		m2 = new JMenu("View");
		m3 = new JMenu("Operations");
		//m4 = new JMenu("");
		m2i1 = new JMenuItem("Production");
		m2i2 = new JMenuItem("Dispatch");
		m2i3 = new JMenuItem("Stock");
		//m2i1 = new JMenuItem("Change Data Source");
		m3i1 = new JMenuItem("Modify products");
		m2.add(m2i1);
		m2.add(m2i2);
		m2.add(m2i3);
		m3.add(m3i1);
		//m3.add(m3i2);
		//m3.add(m3i3);
		//m4.add(m4i1);
		//m4.add(m4i2);
		mb.add(m2);
		mb.add(m3);
		//mb.add(m4);
	}

	public static void main(String[] args) {
        frame.setTitle("Raj Blow Plast");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(900, 500);
        frame.setJMenuBar(mb);

        try {
            new IMStart();
            jScrollPane = new JScrollPane(Stock.getStockView());
            frame.getContentPane().add(BorderLayout.CENTER, jScrollPane);
            frame.setVisible(true);
            //m2i1.addActionListener(new ButtonListener());

            m2i1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        frame.getContentPane().removeAll();
                        jScrollPane = new JScrollPane(Production.getProductionView());
                        frame.getContentPane().add(BorderLayout.CENTER, jScrollPane);
                        frame.getContentPane().add(BorderLayout.SOUTH, Production.getProductionPanel());
                        frame.getContentPane().doLayout();
                        frame.update(frame.getGraphics());
                        frame.setVisible(true);
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(frame,"Unable to retrieve Manufactured products.\nERROR:"+ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            m2i2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        frame.getContentPane().removeAll();
                        jScrollPane = new JScrollPane(Dispatch.getDispatchView());
                        frame.getContentPane().add(BorderLayout.CENTER, jScrollPane);
                        frame.getContentPane().add(BorderLayout.SOUTH, Dispatch.getDispatchPanel());
                        frame.getContentPane().doLayout();
                        frame.update(frame.getGraphics());
                        frame.setVisible(true);
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(frame,"Unable to retrieve Dispatched products.\nERROR:"+ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            m2i3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        frame.getContentPane().removeAll();
                        jScrollPane = new JScrollPane(Stock.getStockView());
                        frame.getContentPane().add(BorderLayout.CENTER, jScrollPane);
                        frame.getContentPane().doLayout();
                        frame.update(frame.getGraphics());
                        frame.setVisible(true);
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(frame,"Unable to retrieve Stock.\nERROR:"+ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            m3i1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        frame.getContentPane().removeAll();
                        jScrollPane = new JScrollPane(ModifyProducts.getPanel());
                        frame.getContentPane().add(BorderLayout.CENTER, jScrollPane);
                        frame.getContentPane().doLayout();
                        frame.update(frame.getGraphics());
                        frame.setVisible(true);
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(frame,"Unable to retrieve Stock.\nERROR:"+ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Sorry! Something went wrong.\nERROR:" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}