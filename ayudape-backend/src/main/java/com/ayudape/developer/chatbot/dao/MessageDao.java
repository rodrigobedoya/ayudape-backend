package com.ayudape.developer.chatbot.dao;

import com.ayudape.developer.chatbot.model.Message;
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
import java.util.UUID;

@Repository
public class MessageDao implements Dao<Message> {

    @Autowired
    private SessionFactory sessionFactory;

    public MessageDao() {}

    @Override
    public Optional<Message> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Message> getAll() {
        try(Session session = sessionFactory.openSession())
        {
            CriteriaQuery<Message> allMessages = session.getCriteriaBuilder().createQuery(Message.class);
            allMessages.from(Message.class);
            List<Message> listMessages = session.createQuery(allMessages).getResultList();
            session.close();
            return listMessages;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Message> getFromUser(UUID user_uuid){
        try(Session session = sessionFactory.openSession())
        {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Message> allMessages = builder.createQuery(Message.class);
            Root<Message> message = allMessages.from(Message.class);
            allMessages.select(message).where(builder.equal(message.get("user_uuid"), user_uuid));
            List<Message> listMessages = session.createQuery(allMessages).getResultList();
            session.close();
            return listMessages;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


    @Override
    public boolean save(Message message) {
        try(Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            session.persist(message);
            tx.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Message message, String[] params) {
        return false;
    }

    @Override
    public boolean delete(Message message) {
        return false;
    }

}
