package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tipo_pelicula")
public class TipoPelicula implements Serializable {
    @Id
    @Column(name = "id_tipo_pelicula", nullable = false)
    private Integer idTipoPelicula;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "idTipoPelicula")
    private List<PeliculaCaracteristica> PeliculaCaracteristica;

    @Size(max = 155)
    @Column(name = "nombre", length = 155)
    private String nombre;

    @Column(name = "activo")
    private Boolean activo;

    @Lob
    @Column(name = "comentarios")
    private String comentarios;

    @Lob
    @Column(name = "expresion_regular")
    private String expresionRegular;

    public TipoPelicula(Integer idTipoPelicula) {
        this.idTipoPelicula = idTipoPelicula;
    }

    public TipoPelicula() {
    }

    public Integer getIdTipoPelicula() {
        return idTipoPelicula;
    }

    public void setIdTipoPelicula(Integer idTipoPelicula) {
        this.idTipoPelicula = idTipoPelicula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getExpresionRegular() {
        return expresionRegular;
    }

    public void setExpresionRegular(String expresionRegular) {
        this.expresionRegular = expresionRegular;
    }

    public List<sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.PeliculaCaracteristica> getPeliculaCaracteristica() {
        return PeliculaCaracteristica;
    }

    public void setPeliculaCaracteristica(List<sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.PeliculaCaracteristica> peliculaCaracteristica) {
        PeliculaCaracteristica = peliculaCaracteristica;
    }
}