package com.gk.rwsendpoints.dao.impl;

import com.gk.rwsendpoints.dao.api.UserInfoAccessDAOAPI;
import com.gk.rwsendpoints.entities.UserInfoEntity;
import org.apache.commons.lang3.tuple.Pair;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by greg korenevsky on 7/28/14.
 */
//@Repository
@Named
public class UserInfoAccessDAOImpl extends GenericHibernateDAO<UserInfoEntity>
        implements UserInfoAccessDAOAPI {

    @Override
    public List<UserInfoEntity> findByAttributeValues(List<Pair<String, String>> attributeNameAndValueList) {

        List<UserInfoEntity> result = null;

        if (attributeNameAndValueList == null || attributeNameAndValueList.isEmpty()) {
            result = findAll();
        } else {
            List<Criterion> criterionList = new ArrayList<Criterion>(attributeNameAndValueList.size());

            for (Pair<String, ?> nameAndValue : attributeNameAndValueList) {
                criterionList.add(Restrictions.eq(nameAndValue.getKey(), nameAndValue.getValue()));
            }

            result = findByCriteria(criterionList);
        }

        return result;
    }
}
