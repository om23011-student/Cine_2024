package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoSalaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoSala;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class FrmTipoSala extends AbstractFrmsHtml<TipoSala> implements Serializable {
    @Inject
    private TipoSalaBean tsBean;

    @Inject
    private FacesContext facesContext;

    private ESTADO_CRUD estado;
    private List<TipoSala> registros;
    private TipoSala registro;

    @PostConstruct
    public void init() {
        estado = ESTADO_CRUD.NINGUNO;
        registros = tsBean.findRange(0, 10000);
        registro = new TipoSala();
    }

    @Override
    public void btnNuevoHandler(ActionEvent actionEvent) {
        this.registro= new TipoSala();
        this.registro.setActivo(true);
        this.registro.setExpresionRegular(".");
        this.estado=ESTADO_CRUD.CREAR;
    }

    @Override
    public void btnGuardarHandler(ActionEvent event) {
        tsBean.create(registro);
        this.registro = null;
        this.registros = tsBean.findRange(0, 10000);
        this.estado = ESTADO_CRUD.NINGUNO;
        agregarMensaje("Guardado exitosamente", null, FacesMessage.SEVERITY_INFO);
    }

    @Override
    public void btnModificarHandler(ActionEvent event) {
        TipoSala actualizado = tsBean.update(registro);
        if (actualizado != null) {
            this.registro = null;
            this.estado = ESTADO_CRUD.NINGUNO;
            agregarMensaje("Registro modificado con éxito", null, FacesMessage.SEVERITY_INFO);
        } else {
            agregarMensaje("No se pudo modificar el registro", null, FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void btnEliminarHandler(ActionEvent event) {
        tsBean.delete(registro);
        this.registro = null;
        this.registros = tsBean.findRange(0, 10000);
        this.estado = ESTADO_CRUD.NINGUNO;
        agregarMensaje("Registro eliminado con éxito", null, FacesMessage.SEVERITY_INFO);
    }

    @Override
    public void btnSeleccionarRegistroHandler(Integer id) {
        if (id != null) {
            this.registro = this.registros.stream().filter(t -> t.getIdTipoSala().equals(id)).findFirst().orElse(null);
            this.estado = ESTADO_CRUD.MODIFICAR;
        } else {
            this.registro = null;
        }
    }

    @Override
    public ESTADO_CRUD getEstado() {
        return estado;
    }

    @Override
    public void setEstado(ESTADO_CRUD estado) {
        this.estado = estado;
    }

    @Override
    public TipoSala getRegistro() {
        return registro;
    }

    @Override
    public void setRegistro(TipoSala registro) {
        this.registro = registro;
    }

    @Override
    public List<TipoSala> getRegistros() {
        return registros;
    }

    @Override
    public void setRegistros(List<TipoSala> registros) {
        this.registros = registros;
    }
}
