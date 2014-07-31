package com.gk.rwsendpoints.dao.api;

import com.gk.rwsendpoints.entities.UserInfoEntity;
import org.apache.commons.lang3.tuple.Pair;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;

import java.util.List;

/**
 * Created by greg korenevsky on 7/28/14.
 */
public interface GenericDAOAPI<T> {

    public List<T> findAll();
    public List<T> findByCriteria(final Criterion... criterion);
    public List<T> findByCriteria(final List<Criterion> criterion);
    public Query createQuery(String queryString);
    public SQLQuery createSQLQuery(String queryString);
}
