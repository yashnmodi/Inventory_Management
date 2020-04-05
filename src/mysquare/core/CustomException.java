package mysquare.core;

import javax.swing.*;
import java.util.logging.Logger;

public class CustomException extends Exception {
    private static Logger logger = Logger.getLogger(CustomException.class.getName());
    public CustomException(){}

    public CustomException(String message){
        logger.severe(message);
    }

    public CustomException(JFrame frame, String message){
        logger.severe(message);
        JOptionPane.showMessageDialog(frame,message,"Unknown Error",JOptionPane.ERROR_MESSAGE);
    }

    public CustomException(JFrame frame, String message, String title){
        logger.severe(message);
        JOptionPane.showMessageDialog(frame,message,title,JOptionPane.ERROR_MESSAGE);
    }
}
