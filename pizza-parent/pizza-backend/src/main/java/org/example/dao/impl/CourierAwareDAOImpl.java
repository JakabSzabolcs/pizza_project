package org.example.dao.impl;

import org.example.dao.CourierAwareDAO;
import org.example.entity.AbstractEntity;

import javax.persistence.TypedQuery;
import java.util.List;

public abstract class CourierAwareDAOImpl<T extends AbstractEntity> extends CoreDAOImpl<T> implements CourierAwareDAO<T> {

    @Override
    public List<T> findByCourierID(Long courierId) {
        TypedQuery<T> query = getEntityManager().createQuery("select n from " + getManagedClass().getSimpleName() + " n where n.courier.id=:id", getManagedClass());
        query.setParameter("id", courierId);
        return query.getResultList();
    }


}
