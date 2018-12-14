package br.senai.sp.model;

import java.io.Serializable;

public class Especialidade implements Serializable {
    private int idEspecialidade;
    private String nomeEspecialidade;

    public int getIdEspecialidade() {
        return idEspecialidade;
    }
    public void setIdEspecialidade(int idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }
    public String getNomeEspecialidade() {
        return nomeEspecialidade;
    }
    public void setNomeEspecialidade(String nomeEspecialidade) {
        this.nomeEspecialidade = nomeEspecialidade;
    }
    @Override
    public String toString() {
        return nomeEspecialidade;
    }
}
