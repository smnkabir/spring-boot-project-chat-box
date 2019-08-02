package bd.edu.nk.chatbox.repository;

import bd.edu.nk.chatbox.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,String > {
     List<User> findAllByName(String name);
}
