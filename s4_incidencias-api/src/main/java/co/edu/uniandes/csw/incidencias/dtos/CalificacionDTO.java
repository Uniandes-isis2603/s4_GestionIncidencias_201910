/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;

/**
 *
 * @author estudiante
 */
public class CalificacionDTO {
    private Integer numeroEst;
    private String descripcion;

    /**
     * @return the numeroEst
     */
    public Integer getNumeroEst() {
        return numeroEst;
    }

    /**
     * @param numeroEst the numeroEst to set
     */
    public void setNumeroEst(Integer numeroEst) {
        this.numeroEst = numeroEst;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
