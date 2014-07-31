package com.gk.rwsendpoints.services.impl;

import com.gk.rwsendpoints.dao.api.UserInfoAccessDAOAPI;
import com.gk.rwsendpoints.dto.UserInfo;
import com.gk.rwsendpoints.entities.UserInfoEntity;
import com.gk.rwsendpoints.services.api.UserInfoAccessServiceAPI;
import org.apache.commons.lang3.tuple.Pair;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by greg korenevsky on 7/28/14.
 */
//@Service
@Named
public class UserInfoAccessServiceImpl implements UserInfoAccessServiceAPI {

    UserInfoAccessDAOAPI userInfoAccessDAO;

    DozerBeanMapper dozerMapper;

    public UserInfoAccessDAOAPI getUserInfoAccessDAO() {
        return userInfoAccessDAO;
    }

    @Inject
    public void setUserInfoAccessDAO(UserInfoAccessDAOAPI userInfoAccessDAO) {
        this.userInfoAccessDAO = userInfoAccessDAO;
    }

    public DozerBeanMapper getDozerMapper() {
        return dozerMapper;
    }

    @Inject
    public void setDozerMapper(DozerBeanMapper dozerMapper) {
        this.dozerMapper = dozerMapper;
    }


    @Override
    @Transactional
    public List<UserInfo> getUsers(String attributeName, String attributeValue) {

        final Pair<String, String> nameAndValue = Pair.of(attributeName ,attributeValue);

        List<Pair<String, String>> nameAndValueList =  new ArrayList<Pair<String, String>>() {
            { add(nameAndValue); }
        };

        List<UserInfoEntity> userInfoEntities =
                userInfoAccessDAO.findByAttributeValues(nameAndValueList);

        List<UserInfo> result = new ArrayList<UserInfo>(userInfoEntities.size());

        for (UserInfoEntity userInfoEntity: userInfoEntities) {
            UserInfo userInfo = dozerMapper.map(userInfoEntity, UserInfo.class);
            result.add(userInfo);
        }

        return result;
    }

    @Override
    @Transactional
    public List<UserInfo> getAllUsers() {

        List<UserInfoEntity> userInfoEntities =
                userInfoAccessDAO.findAll();

        List<UserInfo> result = new ArrayList<UserInfo>(userInfoEntities.size());

        for (UserInfoEntity userInfoEntity: userInfoEntities) {
            UserInfo userInfo = dozerMapper.map(userInfoEntity, UserInfo.class);
            result.add(userInfo);
        }

        return result;
    }

//    @Override
//    public List<UserInfo> getUsers(String attributeName, String attributeValue, String groupByAttributeName) {
//
//        return null;
//    }
}
