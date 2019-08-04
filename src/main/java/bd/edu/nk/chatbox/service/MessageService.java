package bd.edu.nk.chatbox.service;

import bd.edu.nk.chatbox.model.Message;
import bd.edu.nk.chatbox.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public MessageService() {

    }

    public List<Message> getMessages(){
        List<Message> messageList = new ArrayList<>();
        messageRepository.findAll().forEach(messageList::add);
//        messageList.forEach(System.out::println);
        return messageList;
    }

    public Message saveMessage(Message message){
        messageRepository.save(message);
        return message;
    }

    public void deleteBySender(String sender) {
        messageRepository.deleteBySender(sender);
    }
}
