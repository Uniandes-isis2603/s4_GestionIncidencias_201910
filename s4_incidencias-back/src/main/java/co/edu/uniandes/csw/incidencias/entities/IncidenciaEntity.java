/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author estudiante
 */


@Entity
public class IncidenciaEntity extends BaseEntity implements  Serializable{

    @Temporal(TemporalType.DATE)
    private Date fecha;
    private String descripcion; 
    private EmpleadoEntity empleado;
     private String estado;
    private Double incidencia;
    
    private CalificacionEntity calificacion;
    private EquipoComputoEntity equipoComputo;
    private TecnicoEntity  tecnico;
 
    private PrioridadEntity  prioridad;
    


    
    
    
    
    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
    
    public EmpleadoEntity getEmpleado(){
        return empleado;
    }
    public void settEmpleado(EmpleadoEntity empleado){
        this.empleado=empleado;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the incidencia
     */
    public Double getIncidencia() {
        return incidencia;
    }

    /**
     * @param incidencia the incidencia to set
     */
    public void setIncidencia(Double incidencia) {
        this.incidencia = incidencia;
    }

    /**
     * @return the calificacion
     */
    public CalificacionEntity getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(CalificacionEntity calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the equipoComputo
     */
    public EquipoComputoEntity getEquipoComputo() {
        return equipoComputo;
    }

    /**
     * @param equipoComputo the equipoComputo to set
     */
    public void setEquipoComputo(EquipoComputoEntity equipoComputo) {
        this.equipoComputo = equipoComputo;
    }

    /**
     * @return the tecnico
     */
    public TecnicoEntity getTecnico() {
        return tecnico;
    }

    /**
     * @param tecnico the tecnico to set
     */
    public void setTecnico(TecnicoEntity tecnico) {
        this.tecnico = tecnico;
    }

    /**
     * @return the prioridad
     */
    public PrioridadEntity getPrioridad() {
        return prioridad;
    }

    /**
     * @param prioridad the prioridad to set
     */
    public void setPrioridad(PrioridadEntity prioridad) {
        this.prioridad = prioridad;
    }
}
