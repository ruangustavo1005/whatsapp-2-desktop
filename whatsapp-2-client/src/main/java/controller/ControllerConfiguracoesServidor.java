package controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;
import utils.ConfigUtils;
import utils.Connection;
import view.ViewConfiguracoesServidor;

/**
 * @author Leonardo & Ruan
 */
public class ControllerConfiguracoesServidor extends ControllerBase<ViewConfiguracoesServidor> {

    @Override
    protected ViewConfiguracoesServidor getInstanceView() {
        return new ViewConfiguracoesServidor();
    }

    @Override
    public void abreTela() {
        this.setIPPortFromConfig();
        super.abreTela();
    }

    private void setIPPortFromConfig() {
        try {
            String config = ConfigUtils.getConfig();
            
            if (config != null && !config.isEmpty()) {
                String[] configs = config.split(":");
                this.getView().getTxtIP().setText(configs[0]);
                this.getView().getTxtPort().setText(configs[1]);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this.getView(), "Não foi possível ler o arquivo de configurações", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void addActionListeners(ViewConfiguracoesServidor view) {
        this.addActionListenerAplicar(view);
        this.addActionListenerTestar(view);
    }
    
    private void addActionListenerAplicar(ViewConfiguracoesServidor view) {
        this.getView().getBtnAplicar().addActionListener((ActionEvent actionEvent) -> {
            try {
                String ip = this.getView().getTxtIP().getText();
                String port = this.getView().getTxtPort().getText();
                
                ConfigUtils.setConfig(ip.concat(":").concat(port));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this.getView(), "Não foi possível ler o arquivo de configurações", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    
    private void addActionListenerTestar(ViewConfiguracoesServidor view) {
        this.getView().getBtnTestar().addActionListener((ActionEvent actionEvent) -> {
            int timeout = 5000;
            try {
                try (Socket socket = (new Connection(timeout)).getInstanceSocket()) {
                    JOptionPane.showMessageDialog(this.getView(), "Conexão OK", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this.getView(), "Conexão recusada (expirado tempo de espera de " + timeout + " milissegundo(s))", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

}