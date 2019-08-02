package bd.edu.nk.chatbox.repository;

import bd.edu.nk.chatbox.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository <Message,String> {
}
