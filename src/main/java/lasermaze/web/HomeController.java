package lasermaze.web;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class HomeController {
    private static final Logger log = getLogger(HomeController.class);

    @GetMapping("/hello")
    public String home() {
        return "index.html";
    }
}
