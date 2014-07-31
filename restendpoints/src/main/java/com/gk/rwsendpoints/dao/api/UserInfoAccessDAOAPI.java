package com.gk.rwsendpoints.dao.api;

/**
 * Created by greg korenevsky on 7/28/14.
 */

import com.gk.rwsendpoints.entities.UserInfoEntity;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * Created by greg korenevsky on 7/28/14.
 */
public interface UserInfoAccessDAOAPI extends GenericDAOAPI<UserInfoEntity> {

    public List<UserInfoEntity> findByAttributeValues(List<Pair<String, String>> attributeNameAndValueList);
}