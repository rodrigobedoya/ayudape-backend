package com.ayudape.developer.chatbot.api;

import com.ayudape.developer.chatbot.model.User;
import com.ayudape.developer.chatbot.model.Message;
import com.ayudape.developer.chatbot.service.MessageService;
import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.List;


import java.util.UUID;

@RestController
public class MessageController {

    private final MessageService messageService;
    private final Assistant assistantService;

    @Autowired
    private MessageController(MessageService messageService) {
        IamOptions options = new IamOptions.Builder().apiKey("rS0J-FJmKIrLKk6yUoM6tBffDaCNnoCWQCG-DBOo-EvZ").build();
        this.messageService = messageService;
        this.assistantService = new Assistant("2019-06-04");
        this.assistantService.setIamCredentials(options);
        this.assistantService.setEndPoint("https://gateway.watsonplatform.net/assistant/api");
//        MessageInput input = new MessageInput.Builder()
//                .text("Hi")
//                .build();
//        CreateSessionOptions createSessionOptions = new CreateSessionOptions.Builder("d03aae8d-1f0c-4867-841a-7dd29f5d7ae9").build();
//        SessionResponse session = assistantService.createSession(createSessionOptions).execute().getResult();
//        String sessionId = session.getSessionId();
//        MessageOptions messageOptions = new MessageOptions.Builder()
//                .assistantId("d03aae8d-1f0c-4867-841a-7dd29f5d7ae9")
//                .sessionId(sessionId)
//                .input(input)
//                .build();
//        MessageResponse messageResponse = this.assistantService.message(messageOptions).execute().getResult();
//
//        System.out.println(messageResponse);
    }

    @PostMapping(
            value="/ask-question",
            consumes="application/json",
            produces="application/json")
    MessageResponse askQuestion(@RequestBody Message message) {
        MessageInput input = new MessageInput.Builder()
                .text(message.getText())
                .build();
        CreateSessionOptions createSessionOptions = new CreateSessionOptions.Builder("d03aae8d-1f0c-4867-841a-7dd29f5d7ae9").build();
        SessionResponse session = assistantService.createSession(createSessionOptions).execute().getResult();
        String sessionId = session.getSessionId();
        MessageOptions messageOptions = new MessageOptions.Builder()
                .assistantId("d03aae8d-1f0c-4867-841a-7dd29f5d7ae9")
                .sessionId(sessionId)
                .input(input)
                .build();
        return this.assistantService.message(messageOptions).execute().getResult();
    }

    @RequestMapping("/saveM")
    public boolean save() {
        UUID a = UUID.randomUUID();
        Message message = new Message(a,"hola",Boolean.FALSE,"");
        List<Message> test = messageService.getFromUser(a);
        //List<Message> test = messageService.getAll();
        System.out.println(test.size());
        for(int i = 0; i < test.size();i++)
        {
            System.out.println(test.get(i).getText());
        }
        return messageService.save(message);
    }
/*
    @ResponseBody
    @RequestMapping("/top/pages/")
    public List<Message> getAllPosts(@PageableDefault(value=10, page=0) Pageable pageable) throws ServletException {
        Page page = messageService.findAll(pageable);
        return page.getContent();
    }
*/
}
