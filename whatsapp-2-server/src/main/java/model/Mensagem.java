package model;

import java.util.Date;
import utils.DateUtils;

/**
 * @author Leonardo e Ruan
 */
public class Mensagem {
    
    private String id;
    
    private Usuario usuario;
    private String mensagem;
    private Date dataHora;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return this.id + ";"  + this.mensagem + ";" + DateUtils.dateToString(this.dataHora);
    }
    
}