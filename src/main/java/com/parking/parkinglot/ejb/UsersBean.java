package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.UserDto;

import com.parking.parkinglot.entities.User;
import com.parking.parkinglot.entities.UserGroup;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UsersBean {
    @Inject
    PasswordBean passwordBean;
    private static final Logger LOG = Logger.getLogger(UsersBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;

    public List<UserDto> findAllUsers()
    {
        LOG.info("findAllUsers");
        try {
            TypedQuery<User> typedQuery=entityManager.createQuery("SELECT u FROM User u",User.class);
            List<User> users=typedQuery.getResultList();
            return copyUsersToDto(users);

        }catch (Exception ex){
            throw new EJBException(ex);
        }

    }
    List<UserDto> copyUsersToDto(List<User> users) {
        List<UserDto> userList = new ArrayList<UserDto>();
        for (User user : users) {
            userList.add(new UserDto(user.getId(),user.getUserName(),user.getEmail()));
        }
        return userList;
    }
    public void createUser(String username, String email, String password, Collection<String> groups) {
        LOG.info("createUser");
        User newUser = new User();
        newUser.setUserName(username);
        newUser.setEmail(email);
        newUser.setPassword(passwordBean.convertToSha256(password));
        entityManager.persist(newUser);
        assignGroupsToUser(username, groups);
    }
    private void assignGroupsToUser(String username, Collection<String> groups) {
        LOG.info("assignGroupsToUser");
        for (String group : groups) {
            UserGroup userGroup = new UserGroup();
            userGroup.setUserName(username);
            userGroup.setUserGroup(group);
            entityManager.persist(userGroup);
        }
    }
    public  Collection<String> findUsernamesByUserIds(Collection<Long> userIds){
        List<String> usernames=
                entityManager.createQuery("SELECT u.userName FROM User u WHERE u.id IN :userIds",String.class)
                        .setParameter("userIds", userIds)
                        .getResultList();
    return usernames;
    }

}
