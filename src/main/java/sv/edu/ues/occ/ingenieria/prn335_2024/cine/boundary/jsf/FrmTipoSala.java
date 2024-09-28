package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoSalaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoSala;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class FrmTipoSala implements Serializable {
@Inject
TipoSalaBean tsBean;
List<TipoSala> registros;
@PostConstruct
public void init() {
registros= tsBean.findRange(0,10000);
}

TipoSala registro;


public void btnSeleccionarRegistroMandler(ActionEvent ae) {
    System.out.println("Selecciono algo");
}

    public List<TipoSala> getRegistros() {
        return registros;
    }

    public void setRegistros(List<TipoSala> registros) {
        this.registros = registros;
    }

   public Integer getSeleccionado(){
        if (registro==null){
            return null;

        }
        return registro.getIdTipoSala();
   }
public void setSeleccionado(Integer seleccionado){
      this.registro = this.registros.stream().filter(r->r.getIdTipoSala()==seleccionado).collect(Collectors.toList()).getFirst();
}

    public TipoSala getRegistro() {
        return registro;
    }

    public void setRegistro(TipoSala registro) {
        this.registro = registro;
    }
}


