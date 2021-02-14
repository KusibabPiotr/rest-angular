package pl.javastart.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "products.html";
    }
    @GetMapping("/processClients")
    public String getClients(){
        return "clients.html";
    }
}
