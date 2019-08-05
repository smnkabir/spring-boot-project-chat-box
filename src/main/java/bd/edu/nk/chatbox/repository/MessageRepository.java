package bd.edu.nk.chatbox.repository;

import bd.edu.nk.chatbox.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,String> {
    List<Message> deleteBySender(String sender);
    List<Message> findAllBySender(String sender);
}
