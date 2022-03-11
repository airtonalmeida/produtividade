package br.com.ettec.produtividade.domain;

/**
 * Created by usuario on 18/01/2017.
 */

public class Turno {

    private Integer id; ////primary key da tabela do sqlite

    private Integer codigo;

    private String descricao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
