package br.senai.sp.model;

import java.io.Serializable;

public class Medico implements Serializable {
    private int idMedico;
    private String nomeMedico;

    public int getIdMedico() {
        return idMedico;
    }
    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }
    public String getNomeMedico() {
        return nomeMedico;
    }
    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    @Override
    public String toString() {
        return nomeMedico;
    }
}
