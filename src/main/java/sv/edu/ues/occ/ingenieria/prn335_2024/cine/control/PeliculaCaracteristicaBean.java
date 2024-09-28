package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.PeliculaCaracteristica;

import java.io.Serializable;

@Stateless
@LocalBean
public class PeliculaCaracteristicaBean extends AbstractDataPersist<PeliculaCaracteristica> implements Serializable {
@PersistenceContext(unitName = "CinePU")
EntityManager em;

    public PeliculaCaracteristicaBean() {
        super(PeliculaCaracteristica.class);
    }


    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
