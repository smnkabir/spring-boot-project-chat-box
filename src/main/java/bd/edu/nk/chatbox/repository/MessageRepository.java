package bd.edu.nk.chatbox.repository;

import bd.edu.nk.chatbox.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message,String> {
    Long deleteBySender(String sender);
}
