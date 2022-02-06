package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.TelaServer;

public class ControllerApp {
    
    private ControllerServer server;

    public ControllerApp() {
        this.server = new ControllerServer();
    }
    
    public void showView() {
        this.getInstanceView().setVisible(true);
        this.addActionListeners();
    }
    
    public TelaServer getInstanceView() {
        return TelaServer.getInstance();
    }
    
    private void addActionListeners() {
        this.addActionStart();
        this.addActionStop();
        this.addActionClearLogs();
    }
    
    private void addActionStart() {
        this.getInstanceView().getStartButton().addActionListener((e) -> {
            int port = Integer.parseInt(this.getInstanceView().getPortValue());
            try {
                this.server.initConnection(port);
                this.getInstanceView().getStartButton().setEnabled(false);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this.getInstanceView(), ex.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(TelaServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    private void addActionStop() {
        this.getInstanceView().getStopButton().addActionListener((e) -> {
            
        });
    }
    
    private void addActionClearLogs() {
        this.getInstanceView().getClearButton().addActionListener((e) -> {
            getInstanceView().clearLogs();
        });
    }
    
}