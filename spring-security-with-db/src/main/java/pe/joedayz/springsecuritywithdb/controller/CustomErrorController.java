package pe.joedayz.springsecuritywithdb.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author josediaz
 **/
@Controller
@RequestMapping
public class CustomErrorController implements ErrorController {

  @GetMapping("/error")
  public String handleError(HttpServletRequest request) {
    Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    if(statusCode != null && Integer.parseInt(statusCode.toString()) == HttpStatus.FORBIDDEN.value()) {
      return "403";
    }else if (statusCode != null && Integer.parseInt(statusCode.toString()) == HttpStatus.NOT_FOUND.value()) {
      return "404";
    }
    return "error";
  }

}