package licenta.project.Controllers;

import licenta.project.Dto.RegisterDto.RegisterDto;
import licenta.project.Exceptions.UserAlreadyExistAuthenticationException;
import licenta.project.Services.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/register")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class RegisterController {
    private final AppUserService appUserService;

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto registerDto) {
        try {
            appUserService.register(registerDto);
        } catch (UserAlreadyExistAuthenticationException | MessagingException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/confirm")
    public RedirectView confirm(@RequestParam("token") String token) {
        appUserService.confirmToken(token);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:3000/register/confirm");
        return redirectView;
    }
}
