package com.ayudape.developer.chatbot.service;

import com.ayudape.developer.chatbot.dao.MessageDao;
import com.ayudape.developer.chatbot.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MessageService {

    private final MessageDao messageDao;

    @Autowired
    private MessageService(MessageDao messageDao) { this.messageDao = messageDao; }

    public boolean save(Message message) {
        return messageDao.save(message);
    }

    public List<Message> getAll() { return messageDao.getAll(); }

    public List<Message> getFromUser(UUID user_uuid) {return messageDao.getFromUser(user_uuid);}
}
