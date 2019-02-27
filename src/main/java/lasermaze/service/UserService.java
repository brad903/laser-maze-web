package lasermaze.service;

import lasermaze.domain.User;
import lasermaze.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public void create(User user) {
        userRepository.save(user);
    }
}
