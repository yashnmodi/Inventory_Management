package mysquare.core;

import org.bson.Document;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class IMStart {
	
	public static JFrame frame = new JFrame();
	public static JMenuBar mb = new JMenuBar();
	public static JMenu m2,m3,m4, m2i1, m2i2;
	public static JMenuItem m3i1, m4i1, m2i1si1, m2i1si2, m2i1si3, m2i2si1, m2i2si2, m2i2si3;
	public static JScrollPane jScrollPane;


	IMStart(){
		m2 = new JMenu(ApplicationConstants.MENUBAR[0]);
		m2.setMnemonic(KeyEvent.VK_V);
		m3 = new JMenu(ApplicationConstants.MENUBAR[1]);
		m3.setMnemonic(KeyEvent.VK_O);
		m4 = new JMenu(ApplicationConstants.MENUBAR[2]);
		m4.setMnemonic(KeyEvent.VK_H);
		m2i1 = new JMenu(ApplicationConstants.BOTTLES);
		m2i2 = new JMenu(ApplicationConstants.CAPS);
		m2i1si1 = new JMenuItem(ApplicationConstants.PRODUCTION);
		m2i1si2 = new JMenuItem(ApplicationConstants.DISPATCH);
		m2i1si3 = new JMenuItem(ApplicationConstants.STOCK);
        m2i2si1 = new JMenuItem(ApplicationConstants.PRODUCTION);
        m2i2si2 = new JMenuItem(ApplicationConstants.DISPATCH);
        m2i2si3 = new JMenuItem(ApplicationConstants.STOCK);
		m3i1 = new JMenuItem(ApplicationConstants.M3I1);
		m4i1 = new JMenuItem(ApplicationConstants.M4I1);

		m2i1.add(m2i1si1);
		m2i1.add(m2i1si2);
		m2i1.add(m2i1si3);
        m2i2.add(m2i2si1);
        m2i2.add(m2i2si2);
        m2i2.add(m2i2si3);
		m2.add(m2i1);
		m2.add(m2i2);
		m3.add(m3i1);
		m4.add(m4i1);
		mb.add(m2);
		mb.add(m3);
		mb.add(m4);
		m2i1.setOpaque(true);
		m2i2.setOpaque(true);
        m2i1si1.setBackground(new Color(239,214,186));
        m2i1si2.setBackground(new Color(239,214,186));
        m2i1si3.setBackground(new Color(239,214,186));
        m2i2si1.setBackground(new Color(239,214,186));
        m2i2si2.setBackground(new Color(239,214,186));
        m2i2si3.setBackground(new Color(239,214,186));
        m2i1.setBackground(new Color(239,214,186));
        m2i2.setBackground(new Color(239,214,186));
        m3i1.setBackground(new Color(239,214,186));
        m4i1.setBackground(new Color(239,214,186));
        mb.setBackground(new Color(239,176,137));
	}

	public static void main(String[] args) {

        frame.setTitle(ApplicationConstants.ORGANIZATION);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(900, 500);
        frame.setJMenuBar(mb);

        Document query1 = new Document(ApplicationConstants.COL_TYPE,ApplicationConstants.BOTTLES);
        Document query2 = new Document(ApplicationConstants.COL_TYPE,ApplicationConstants.CAPS);

        try {
            new IMStart();
            jScrollPane = new JScrollPane(Stock.getStockView(query1));
            frame.getContentPane().add(BorderLayout.CENTER, jScrollPane);
            frame.setVisible(true);

            m2i1si1.addActionListener(e -> {
                try {
                    frame.getContentPane().removeAll();
                    jScrollPane = new JScrollPane(Production.getProductionView(query1));
                    frame.getContentPane().add(BorderLayout.CENTER, jScrollPane);
                    frame.getContentPane().add(BorderLayout.SOUTH, Production.getProductionPanel(ApplicationConstants.BOTTLES));
                    frame.getContentPane().doLayout();
                    frame.update(frame.getGraphics());
                    frame.setVisible(true);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(frame,"Unable to retrieve Manufactured products.\nERROR:"+ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
            });

            m2i1si2.addActionListener(e -> {
                try {
                    frame.getContentPane().removeAll();
                    jScrollPane = new JScrollPane(Dispatch.getDispatchView(query1));
                    frame.getContentPane().add(BorderLayout.CENTER, jScrollPane);
                    frame.getContentPane().add(BorderLayout.SOUTH, Dispatch.getDispatchPanel(ApplicationConstants.BOTTLES));
                    frame.getContentPane().doLayout();
                    frame.update(frame.getGraphics());
                    frame.setVisible(true);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(frame,"Unable to retrieve Dispatched products.\nERROR:"+ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
            });

            m2i1si3.addActionListener(e -> {
                try {
                    frame.getContentPane().removeAll();
                    jScrollPane = new JScrollPane(Stock.getStockView(query1));
                    frame.getContentPane().add(BorderLayout.CENTER, jScrollPane);
                    frame.getContentPane().doLayout();
                    frame.update(frame.getGraphics());
                    frame.setVisible(true);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(frame,"Unable to retrieve Stock.\nERROR:"+ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
            });

            m2i2si1.addActionListener(e -> {
                try {
                    frame.getContentPane().removeAll();
                    jScrollPane = new JScrollPane(Production.getProductionView(query2));
                    frame.getContentPane().add(BorderLayout.CENTER, jScrollPane);
                    frame.getContentPane().add(BorderLayout.SOUTH, Production.getProductionPanel(ApplicationConstants.CAPS));
                    frame.getContentPane().doLayout();
                    frame.update(frame.getGraphics());
                    frame.setVisible(true);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(frame,"Unable to retrieve Manufactured products.\nERROR:"+ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
            });

            m2i2si2.addActionListener(e -> {
                try {
                    frame.getContentPane().removeAll();
                    jScrollPane = new JScrollPane(Dispatch.getDispatchView(query2));
                    frame.getContentPane().add(BorderLayout.CENTER, jScrollPane);
                    frame.getContentPane().add(BorderLayout.SOUTH, Dispatch.getDispatchPanel(ApplicationConstants.CAPS));
                    frame.getContentPane().doLayout();
                    frame.update(frame.getGraphics());
                    frame.setVisible(true);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(frame,"Unable to retrieve Dispatched products.\nERROR:"+ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
            });

            m2i2si3.addActionListener(e -> {
                try {
                    frame.getContentPane().removeAll();
                    jScrollPane = new JScrollPane(Stock.getStockView(query2));
                    frame.getContentPane().add(BorderLayout.CENTER, jScrollPane);
                    frame.getContentPane().doLayout();
                    frame.update(frame.getGraphics());
                    frame.setVisible(true);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(frame,"Unable to retrieve Stock.\nERROR:"+ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
            });

            m3i1.addActionListener(e -> {
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
            });

            m4i1.addActionListener(e -> JOptionPane.showMessageDialog(frame, ApplicationConstants.DEVELOPER, "About Software", JOptionPane.INFORMATION_MESSAGE));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Sorry! Something went wrong.\nERROR:" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}