package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public abstract class AbstractDataPersist <T>{
    public abstract EntityManager getEntityManager ();
    Class TipoDatos;

    public AbstractDataPersist(Class TipoDatos)  {
        this.TipoDatos = TipoDatos;
    }

    /**
     * Almacena un registro en el repositorio
     * @param entity Registro a almacenar
     * @throws IllegalArgumentException Si no puede acceder al repositorio, u ocurre un error al insertar
     * @throws IllegalStateException Si el registro es nulo.
     */
    public void create(T entity) throws IllegalArgumentException,IllegalStateException {
      EntityManager em = null;
      if(entity == null){
          throw new IllegalArgumentException("La entidad a persistir no puede ser nula");
      }
      try{
          em = getEntityManager();

          if(em==null){
              throw new IllegalStateException("Error al acceder al repositorio");
          }
          em.persist(entity);
      }catch(Exception e){
          throw new IllegalStateException("Error al acceder al repositorio",e);

      }




    }

    /**
     *Busca un registro en el repositorio por su identificador unico.
     * @param id Identificador unico buscado.
     * @return Nulo si no se encuentra o el registro encontrado.
     * @throws IllegalArgumentException Si el id es nulo.
     * @throws IllegalStateException Si no se puede acceder al repositorio
     */
    public T findById(final Object id) throws IllegalArgumentException,IllegalStateException {
        EntityManager em= null;
        if(id == null){
            throw new IllegalArgumentException("Parametro no valido: idTiposala");
        }
        try{
            em=getEntityManager();
            if(em== null){
                throw new IllegalStateException("Error al acceder al repositorio");
            }
        }catch (Exception ex){
            throw new IllegalStateException("Error al acceder al repositorio",ex);
        }
        return (T) em.find(TipoDatos,id);
    }
public List<T> findRange(int first,int max) throws IllegalArgumentException,IllegalStateException {
    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    CriteriaQuery q = cb.createQuery(TipoDatos);
    Root<T> raiz = q.from(TipoDatos);
    CriteriaQuery cq = q.select(raiz);
    TypedQuery query = getEntityManager().createQuery(cq);
    query.setFirstResult(first);
    query.setMaxResults(max);
    return query.getResultList();
}
}