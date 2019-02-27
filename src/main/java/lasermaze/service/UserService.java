package lasermaze.service;

import lasermaze.UnSupportedFormatException;
import lasermaze.domain.User;
import lasermaze.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void create(User user) {
        Optional<User> mayUser = userRepository.findByUserId(user.getUserId());
        if(mayUser.isPresent()) {
            throw new UnSupportedFormatException("이미 존재하는 아이디가 있습니다.");
        }
        userRepository.save(user);
    }
}
