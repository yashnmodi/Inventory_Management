package mysquare.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyProducts {

    public static JPanel getPanel(){
        JPanel panel = new JPanel();
        JTextField product = new JTextField(40);
        product.setToolTipText("Enter unique product");
        product.setBounds(15, 25, 250, 20);
        JTextField colour = new JTextField(40);
        colour.setToolTipText("Enter unique colour");
        colour.setBounds(15, 60, 250, 20);
        JTextField weight = new JTextField(40);
        weight.setToolTipText("Enter unique weight");
        weight.setBounds(15, 95, 250, 20);
        JButton addProduct = new JButton("Add Product");
        addProduct.setBounds(280, 25, 120, 20);
        JButton addColour = new JButton("Add Colour");
        addColour.setBounds(280, 60, 120, 20);
        JButton addWeight = new JButton("Add Weight");
        addWeight.setBounds(280, 95, 120, 20);
        JButton rmvProduct = new JButton("Remove Product");
        rmvProduct.setBounds(410, 25, 120, 20);
        JButton rmvColour = new JButton("Remove Colour");
        rmvColour.setBounds(410, 60, 120, 20);
        JButton rmvWeight = new JButton("Remove Weight");
        rmvWeight.setBounds(410, 95, 120, 20);
        panel.setLayout(null);
        panel.add(product);
        panel.add(colour);
        panel.add(weight);
        panel.add(addProduct);
        panel.add(rmvProduct);
        panel.add(addColour);
        panel.add(rmvColour);
        panel.add(addWeight);
        panel.add(rmvWeight);

        addProduct.addActionListener(e -> {
            try {
                Db db = new Db();
                db.addItem("bottles",product.getText());
                product.setText("");
            } catch (Exception err) {
                JOptionPane.showConfirmDialog(IMStart.frame, "Product already added.\nERROR:"+err.getMessage(),"WARNING",JOptionPane.WARNING_MESSAGE );
            }
        });
        addColour.addActionListener(e -> {
            try {
                Db db = new Db();
                db.addItem("colours",colour.getText());
                colour.setText("");
            } catch (Exception err) {
                JOptionPane.showConfirmDialog(IMStart.frame, "Colour already added.\nERROR:"+err.getMessage(),"WARNING",JOptionPane.WARNING_MESSAGE );
            }
        });
        addWeight.addActionListener(e -> {
            try {
                Db db = new Db();
                db.addItem("weights",weight.getText());
                weight.setText("");
            } catch (Exception err) {
                JOptionPane.showConfirmDialog(IMStart.frame, "Weight already added.\nERROR:"+err.getMessage(),"WARNING",JOptionPane.WARNING_MESSAGE );
            }
        });
        rmvProduct.addActionListener(e -> {
            try {
                Db db = new Db();
                db.removeItem("bottles",product.getText());
                product.setText("");
            } catch (Exception err) {
                JOptionPane.showConfirmDialog(IMStart.frame, "Product not found.\nERROR:"+err.getMessage(),"WARNING",JOptionPane.WARNING_MESSAGE );
            }
        });
        rmvColour.addActionListener(e -> {
            try {
                Db db = new Db();
                db.removeItem("colours",colour.getText());
                colour.setText("");
            } catch (Exception err) {
                JOptionPane.showConfirmDialog(IMStart.frame, "Colour not found.\nERROR:"+err.getMessage(),"WARNING",JOptionPane.WARNING_MESSAGE );
            }
        });
        rmvWeight.addActionListener(e -> {
            try {
                Db db = new Db();
                db.removeItem("weights",weight.getText());
                weight.setText("");
            } catch (Exception err) {
                JOptionPane.showConfirmDialog(IMStart.frame, "Weight not found.\nERROR:"+err.getMessage(),"WARNING",JOptionPane.WARNING_MESSAGE );
            }
        });
        panel.setBackground(new Color(239,214,186));
        return panel;
    }
}