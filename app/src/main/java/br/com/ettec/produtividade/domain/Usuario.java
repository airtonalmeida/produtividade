package br.com.ettec.produtividade.domain;

/**
 * Created by usuario on 02/12/2016.
 */

public class Usuario {

    private int idPolicial;

    private int matricula;

    private int ativo;

    private String papel;

    private int idOpm;

    private String descricaoOpm;

    private String nomePolicial;

    private String grauHierarquico;


    public int getIdPolicial() {
        return idPolicial;
    }

    public void setIdPolicial(int idPolicial) {
        this.idPolicial = idPolicial;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public int getIdOpm() {
        return idOpm;
    }

    public void setIdOpm(int idOpm) {
        this.idOpm = idOpm;
    }

    public String getDescricaoOpm() {
        return descricaoOpm;
    }

    public void setDescricaoOpm(String descricaoOpm) {
        this.descricaoOpm = descricaoOpm;
    }

    public String getNomePolicial() {
        return nomePolicial;
    }

    public void setNomePolicial(String nomePolicial) {
        this.nomePolicial = nomePolicial;
    }

    public String getGrauHierarquico() {
        return grauHierarquico;
    }

    public void setGrauHierarquico(String grauHierarquico) {
        this.grauHierarquico = grauHierarquico;
    }


}
