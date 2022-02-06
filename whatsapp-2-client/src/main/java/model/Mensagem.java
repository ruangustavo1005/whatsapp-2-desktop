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
        return this.id;
    }

    public Mensagem setId(String id) {
        this.id = id;
        return this;
    }

    public Usuario getUsuario() {
        if (this.usuario == null) {
            this.usuario = new Usuario();
        }
        return this.usuario;
    }

    public Mensagem setUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public String getMensagem() {
        return this.mensagem;
    }

    public Mensagem setMensagem(String mensagem) {
        this.mensagem = mensagem;
        return this;
    }

    public Date getDataHora() {
        return this.dataHora;
    }

    public String getDataHoraAsString() {
        return DateUtils.dateHourToString(this.dataHora);
    }

    public Mensagem setDataHora(Date dataHora) {
        this.dataHora = dataHora;
        return this;
    }
    
    public Mensagem setDataHora(String dataHora) {
        this.dataHora = DateUtils.stringToDateHour(dataHora);
        return this;
    }

    @Override
    public String toString() {
        return "["
                .concat(this.getDataHoraAsString())
                .concat("] ")
                .concat(this.getUsuario().getNome())
                .concat(":\n")
                .concat(this.getMensagem());
    }
    
}
