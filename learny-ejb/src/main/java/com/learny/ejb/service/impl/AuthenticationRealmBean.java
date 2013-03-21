package com.learny.ejb.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.learny.ejb.dao.local.UserDaoLocal;
import com.learny.ejb.service.local.AthenticationRealmLocal;
import com.learny.persistence.entity.User;


@Stateless
public class AuthenticationRealmBean extends AuthorizingRealm implements AthenticationRealmLocal {

    private final static Logger LOGGER = LogManager.getLogger(AuthenticationRealmBean.class);

    @Inject
    private UserDaoLocal userDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Set<String> roles = new HashSet<String>();
        Collection<User> principalsList = principalCollection.byType(User.class);

        if (principalsList.isEmpty()) {
            throw new AuthorizationException("Empty principals list!");
        }

        for (User userPrincipal : principalsList) {
            roles.add(userPrincipal.getRole().name());
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        info.setRoles(roles);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {

        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;

        User user = userDao.findByEmail(upToken.getUsername());

        if (user == null) {
            LOGGER.info("Not found user with username: " + upToken.getUsername());
            throw new AuthenticationException("Login name [" + upToken.getUsername() + "] not found!");
        }

        LOGGER.info("Found user with username: " + upToken.getUsername());

        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());

    }

}
