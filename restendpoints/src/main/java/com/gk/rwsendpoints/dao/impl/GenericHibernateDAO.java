package com.gk.rwsendpoints.dao.impl;

/**
 * Created by greg korenevsky on 7/28/14.
 */

import com.gk.rwsendpoints.dao.api.GenericDAOAPI;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

public abstract class GenericHibernateDAO<T> implements GenericDAOAPI<T> {

    private SessionFactory sessionFactory;

    public GenericHibernateDAO() {}

    /**
     * Obtain the actual type for which this generic type is instantiated
     *
     * @return class object
     */
    @SuppressWarnings("unchecked")
    protected Class<T> getEntityBeanType() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    /**
     * Set the session factory to be used.
     *
     * @param sessionFactory The session factory to be used by this DAO.
     */
    @Inject
    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Returns the associated session factory.
     *
     * @return a session factory
     */
    protected SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            throw new IllegalStateException("SessionFactory value has not been injected");
        }

        return sessionFactory;
    }

    /**
     * Returns the current session.
     *
     * @return a session
     */
    protected Session getSession() {
        Session session = getSessionFactory().getCurrentSession();
        return session;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findAll() {
        return findByCriteria();
    }

    /**
     * {@inheritDoc}
     */
    protected Criteria createCriteria() {
        return getSession().createCriteria(getEntityBeanType());
    }

    /**
     * Find all entities that match the specified criteria.
     *
     * @param criterion Defines one or many criterion objects that should restrict the search domain.
     * @return All class-based entities that match the criteria.
     * @throws org.hibernate.HibernateException
     */
    @Transactional
    public List<T> findByCriteria(final Criterion... criterion) {

        return findByCriteria(Arrays.asList(criterion));
    }

    /**
     * Find all entities that match the specified criteria.
     *
     * @param criterionList - a list of criterion objects that should restrict the search domain.
     * @return All class-based entities that match the criteria.
     * @throws org.hibernate.HibernateException
     */
    @SuppressWarnings("unchecked")
    @Transactional
    public List<T> findByCriteria(final List<Criterion> criterionList) {

        Criteria criteria = createCriteria();

        for (Criterion c : criterionList) {
            criteria.add(c);
        }

        return criteria.list();
    }

    /**
     * Create  query object suitable for issuing Hibernate queries
     *
     * @param queryString
     * @return  Hibernate Query object
     */
    @Override
    public Query createQuery(String queryString) {
        return getSession().createQuery(queryString);
    }

    /**
     *  Create  query object suitable for issuing DB native SQL queries
     * @param queryString
     * @return
     */
    @Override
    public SQLQuery createSQLQuery(String queryString) {
        return getSession().createSQLQuery(queryString);
    }

}


