package pe.joedayz.springsecuritywithdb.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author josediaz
 **/
@Controller
@RequestMapping("/error")
public class CustomErrorController {

  private final ErrorAttributes errorAttributes;

  public CustomErrorController(ErrorAttributes errorAttributes) {
    this.errorAttributes = errorAttributes;
  }

  @GetMapping
  public String handleError(HttpServletRequest request, Model model) {
    HttpStatus status = getStatus(request);
    model.addAttribute("status", status.value());
    model.addAttribute("error", status.getReasonPhrase());

    if (status == HttpStatus.FORBIDDEN) {
      return "403"; // Redirige a una página específica para 403
    } else if (status == HttpStatus.NOT_FOUND) {
      return "404"; // Redirige a una página específica para 404
    }

    return "error"; // Página genérica de error
  }

  private HttpStatus getStatus(HttpServletRequest request) {
    Object statusCode = request.getAttribute("jakarta.servlet.error.status_code");
    if (statusCode != null) {
      try {
        return HttpStatus.valueOf(Integer.parseInt(statusCode.toString()));
      } catch (NumberFormatException ignored) {
      }
    }
    return HttpStatus.INTERNAL_SERVER_ERROR;
  }
}
