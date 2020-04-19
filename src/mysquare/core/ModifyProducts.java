package mysquare.core;

import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.TilePane;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;

public class ModifyProducts {

    public static JPanel getPanel(){
        JPanel mainPanel = new JPanel(new GridLayout(2,0));

        JPanel panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(3, 3,
                3, 3, IMStart.c2),
                "Edit Catalogue", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, IMStart.f2));

        JComboBox<String> cb = new JComboBox<>(ApplicationConstants.CATALOGUE_ITMES);
        JTextField tf1 = new JTextField(30);
        tf1.setToolTipText("Provide unique name only.");
        JButton addBtn = new JButton("Add");
        JButton removeBtn = new JButton("Remove");
        panel1.add(cb);
        panel1.add(tf1);
        panel1.add(addBtn);
        panel1.add(removeBtn);
        addBtn.addActionListener(e -> {
            try {
                Db db = new Db();
                String type = null;
                for(int i=0;i<ApplicationConstants.CATALOGUE_ITMES.length;i++){
                    if (ApplicationConstants.CATALOGUE_ITMES[i].equals(cb.getSelectedItem().toString())){
                        type = ApplicationConstants.CATALOGUE_ITMES[i];
                        break;
                    }
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
                for(int i=0;i<ApplicationConstants.CATALOGUE_ITMES.length;i++){
                    if (ApplicationConstants.CATALOGUE_ITMES[i].equals(cb.getSelectedItem().toString())){
                        type = ApplicationConstants.CATALOGUE_ITMES[i];
                        break;
                    }
                }
                db.removeItem(type, tf1.getText());
                tf1.setText("");
            } catch (Exception err) {
                JOptionPane.showConfirmDialog(IMStart.frame, "Product not found.\nERROR:"+err.getMessage(),"WARNING",JOptionPane.WARNING_MESSAGE );
            }
        });

        JPanel panel2 = new JPanel();
        JComboBox<String> cb2 = new JComboBox<>(new String[]{ApplicationConstants.MANUFACTURED,ApplicationConstants.DISPATCHED});
        panel2.add(cb2);
//        UtilDateModel model1 = new UtilDateModel();
//        UtilDateModel model2 = new UtilDateModel();
//        JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
//        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
//        JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
//        JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);
        JButton btn2 = new JButton("Clear Data");
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String fromDate = Db.dateFormat.format(datePicker1.getModel().getValue());
//                String toDate = Db.dateFormat.format(datePicker2.getModel().getValue());
//                System.out.println(fromDate);
//                System.out.println(toDate);
                String table = cb2.getSelectedItem().toString();
                String passcode = JOptionPane.showInputDialog("Do you really want to clear data for "+table+"?\nTo proceed, please provide passcode:");
                if (null != passcode && "rbprbp".equalsIgnoreCase(passcode)){
                    Db db = new Db();
                    db.deleteHistory(table);
                }
            }
        });
//        panel2.add(datePicker1);
//        panel2.add(datePicker2);
        panel2.add(btn2);
        mainPanel.add(panel1);
        mainPanel.add(panel2);
//        mainPanel.setBackground(IMStart.c1);
        return mainPanel;
    }
}
/*DatePicker dp1 = new DatePicker();
        DatePicker dp2 = new DatePicker();
        dp1.setShowWeekNumbers(true);
        dp2.setShowWeekNumbers(true);
        dp1.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                LocalDate d = dp1.getValue();
//                fromDate.setText(d);
                System.out.println(d);
            }
        });
        // add button and label
//        tp1.getChildren().add(dp);
//        tp1.getChildren().add(fromDate);
        Scene s = new Scene(dp1);
        panel2.setScene(s);*/