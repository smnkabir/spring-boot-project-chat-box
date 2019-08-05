package bd.edu.nk.chatbox.controller;

import bd.edu.nk.chatbox.model.Message;
import bd.edu.nk.chatbox.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/messages")
public class MessageController {
    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Message>> getMessages(){
        List<Message> messagesList = messageService.getMessages();
        return ResponseEntity.ok(messagesList);
    }

    @GetMapping(value = "{sender}")
    public ResponseEntity<List<Message>> getBySender(@PathVariable String sender){
        List<Message> messagesList = messageService.getBySender(sender);
        return ResponseEntity.ok(messagesList);
    }

    @PostMapping(value = "")
    public ResponseEntity<Message> saveMessage(@RequestBody Message message){
        messageService.saveMessage(message);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping(value = "{sender}")
    public ResponseEntity<String> deleteMessage(@PathVariable String sender){
        boolean bool = messageService.deleteBySender(sender);
        System.out.println("delete on process");
        if(bool)
            return ResponseEntity.ok(sender);
        return ResponseEntity.notFound().build();
    }
}
