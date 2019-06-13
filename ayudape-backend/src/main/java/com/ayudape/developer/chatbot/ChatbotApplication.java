package com.ayudape.developer.chatbot;

import com.ayudape.developer.chatbot.model.User;
import com.ayudape.developer.chatbot.model.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatbotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatbotApplication.class, args);
		//User user = new User("Reynaldo", "Rojas");
		//Message message = new Message(user.getUuid(),"hola",Boolean.FALSE,"");

	}
}