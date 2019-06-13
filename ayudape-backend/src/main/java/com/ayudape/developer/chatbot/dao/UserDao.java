package com.ayudape.developer.chatbot.dao;

import com.ayudape.developer.chatbot.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class UserDao implements Dao<User> {

    @Autowired
    private SessionFactory sessionFactory;

    public UserDao() {}

    @Override
    public Optional<User> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    public User getFromPhone(String phone){
        try(Session session = sessionFactory.openSession())
        {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> userQuery= builder.createQuery(User.class);
            Root<User> user = userQuery.from(User.class);
            userQuery.select(user).where(builder.equal(user.get("phone"), phone));
            List<User> userResult = session.createQuery(userQuery).getResultList();
            session.close();
            return userResult.get(0);
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public boolean save(User user) {
        try(Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(User user, String[] params) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }
}
