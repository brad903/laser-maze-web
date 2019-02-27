package lasermaze.service;

import lasermaze.UnAuthenticationException;
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

    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId).orElseThrow(UnAuthenticationException::new);
    }

    public void create(User user) {
        Optional<User> mayUser = userRepository.findByUserId(user.getUserId());
        if(mayUser.isPresent()) {
            throw new UnSupportedFormatException("이미 존재하는 아이디가 있습니다.");
        }
        userRepository.save(user);
    }

    public User login(String userId, String password) {
        User mayUser = findByUserId(userId);
        if(!mayUser.matchPassword(password)) {
            throw new UnAuthenticationException("잘못된 패스워드 입력하셨습니다");
        }

        return mayUser;
    }
}
