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

    @PostMapping(value = "")
    public ResponseEntity<Message> saveMessage(@RequestBody Message message){
        messageService.saveMessage(message);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping(value = "{sender}")
    public ResponseEntity<String> deleteBySender(@PathVariable String sender){
        messageService.deleteBySender(sender);
        return ResponseEntity.ok("done");
    }
}
