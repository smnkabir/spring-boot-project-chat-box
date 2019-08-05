package bd.edu.nk.chatbox.service;

import bd.edu.nk.chatbox.model.Message;
import bd.edu.nk.chatbox.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
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
        messageList = messageRepository.findAll();
//        messageList.forEach(System.out::println);
        return messageList;
    }

    public Message saveMessage(Message message){
        messageRepository.save(message);
        return message;
    }

    public boolean deleteBySender(String sender) {
        Optional <List<Message>> messageList = Optional.ofNullable(messageRepository.findAllBySender(sender));

        if(messageList.isPresent())
            messageRepository.deleteBySender(sender);
        else
            return false;
        return true;
    }

    public List<Message> getBySender(String sernder) {
        List<Message> messageList = messageRepository.findAllBySender(sernder);
        return messageList;
    }
}
