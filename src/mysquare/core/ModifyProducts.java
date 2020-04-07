package mysquare.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyProducts {

    public static JPanel getPanel(){
        JPanel panel = new JPanel();
        JComboBox<String> cb = new JComboBox<>(ApplicationConstants.CATALOGUE_ITMES);
        JLabel lab1 = new JLabel("Please select an item to add or remove:");
        lab1.setBackground(Color.BLACK);
//        lab1.setLocation(20,200);
        cb.setBounds(30,60,100,20);
        JTextField tf1 = new JTextField(40);
        tf1.setToolTipText("Provide unique name only.");
        tf1.setBounds(30, 90, 250, 20);
        JButton addBtn = new JButton("Add");
        addBtn.setBounds(30, 120, 90, 20);
        JButton removeBtn = new JButton("Remove");
        removeBtn.setBounds(130, 120, 90, 20);
        panel.setLayout(null);
        panel.add(lab1);
        panel.add(cb);
        panel.add(tf1);
        panel.add(addBtn);
        panel.add(removeBtn);

        addBtn.addActionListener(e -> {
            try {
                Db db = new Db();
                String type = null;
                if(ApplicationConstants.BOTTLES.equals(cb.getSelectedItem().toString())){
                    type = ApplicationConstants.BOTTLES;
                } else if (ApplicationConstants.CAPS.equals(cb.getSelectedItem().toString())){
                    type = ApplicationConstants.CAPS;
                } else if (ApplicationConstants.COL_COLOURS.equals(cb.getSelectedItem().toString())){
                    type = ApplicationConstants.COL_COLOURS;
                } else if (ApplicationConstants.COL_WEIGHTS.equals(cb.getSelectedItem().toString())){
                    type = ApplicationConstants.COL_WEIGHTS;
                }
                db.addItem(type, tf1.getText());
                tf1.setText("");
            } catch (Exception err) {
                JOptionPane.showConfirmDialog(IMStart.frame, "Product already added.\nERROR:"+err.getMessage(),"WARNING",JOptionPane.WARNING_MESSAGE );
            }
        });

        removeBtn.addActionListener(e -> {
            try {
                Db db = new Db();
                String type = null;
                if(ApplicationConstants.BOTTLES.equals(cb.getSelectedItem().toString())){
                    type = ApplicationConstants.BOTTLES;
                } else if (ApplicationConstants.CAPS.equals(cb.getSelectedItem().toString())){
                    type = ApplicationConstants.CAPS;
                } else if (ApplicationConstants.COL_COLOURS.equals(cb.getSelectedItem().toString())){
                    type = ApplicationConstants.COL_COLOURS;
                } else if (ApplicationConstants.COL_WEIGHTS.equals(cb.getSelectedItem().toString())){
                    type = ApplicationConstants.COL_WEIGHTS;
                }
                db.removeItem(type, tf1.getText());
                tf1.setText("");
            } catch (Exception err) {
                JOptionPane.showConfirmDialog(IMStart.frame, "Product not found.\nERROR:"+err.getMessage(),"WARNING",JOptionPane.WARNING_MESSAGE );
            }
        });
        panel.setBackground(new Color(239,214,186));
        return panel;
    }
}