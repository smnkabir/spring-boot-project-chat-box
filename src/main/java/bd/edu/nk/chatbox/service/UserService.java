package bd.edu.nk.chatbox.service;

import bd.edu.nk.chatbox.model.User;
import bd.edu.nk.chatbox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        List<User> userList = userRepository.findAll();
        return userList;
    }

    public User saveUser(User user){
        userRepository.save(user);
        return userRepository.findById(user.getName()).get();
    }

    public boolean isValid (User user){
        boolean bool;

        Optional<User> dbUser = userRepository.findById(user.getName());
        if(dbUser.isPresent() && dbUser.get().equals(user))
            bool = true;
        else
            bool = false;
        System.out.println(bool + "\n\n");
        return bool;
    }

    public User getUsersByName(String userName) {

        Optional<User> user = userRepository.findById(userName);
        if(user.isPresent())
            return user.get();
        else
            return null;

    }

    public boolean deleteByName(String userName) {
        Optional<User> user = userRepository.findById(userName);
        System.out.println(user);

        if( user.isPresent() )
            userRepository.deleteById(userName);
        else
            return false;
        return true;
    }
}
