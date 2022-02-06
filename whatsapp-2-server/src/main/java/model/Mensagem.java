package model;

import java.util.Date;
import utils.DateUtils;

/**
 * @author Leonardo e Ruan
 */
public class Mensagem {
    
    private Usuario usuario;
    private String mensagem;
    private Date dataHora;

    public Mensagem(Usuario usuario, String mensagem, Date dataHora) {
        this.usuario = usuario;
        this.mensagem = mensagem;
        this.dataHora = dataHora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {
        return this.usuario.getUsername() + ";" + this.mensagem + ";" + DateUtils.dateToString(this.dataHora);
    }
    
}