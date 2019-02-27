package lasermaze.web;

import lasermaze.UnSupportedFormatException;
import lasermaze.domain.User;
import lasermaze.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/join")
    public String joinForm() {
        return "user/join";
    }

    @PostMapping("/join")
    public String joinRequest(@Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new UnSupportedFormatException("잘못된 형식으로 입력하였습니다");
        }
        userService.create(user);
        return "redirect:/";
    }
}
