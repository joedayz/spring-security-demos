package pe.joedayz.springsecuritywithdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author josediaz
 **/
@Controller
public class ContentController {

  @GetMapping("/")
  public String home() {
    return "home";
  }
  @GetMapping("/home")
  public String homeAlias() {
    return "home";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }
  @GetMapping("/admin/home")
  public String adminHome() {
    return "adminhome";
  }

  @GetMapping("/user/home")
  public String userHome() {
    return "userhome";
  }


}
