package licenta.project.Controllers;

import licenta.project.Dto.LoginDto.JwtDto;
import licenta.project.Dto.LoginDto.LoginDto;
import licenta.project.Services.AppUserService;
import licenta.project.Services.LoginService;
import licenta.project.Utils.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping(path = "/login")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class LoginController {
    private final LoginService loginService;
    private final JwtToken jwtToken;
    private final AppUserService appUserService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) throws Exception {
        try {
            String jwt = this.loginService.loginAccount(loginDto);
            return new ResponseEntity<>(
                    new JwtDto(jwt, appUserService.getAppUserByClaims(jwtToken.extractAllClaims(jwt))),
                    HttpStatus.OK);
        } catch (HttpServerErrorException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        }
    }

    @PostMapping(path = "/google")
    public ResponseEntity<?> loginWithGoogle(@RequestBody String credential) throws Exception {
        try {
            String jwt = this.loginService.connectWithGoogle(credential);
            return new ResponseEntity<>(
                    new JwtDto(jwt, appUserService.getAppUserByClaims(jwtToken.extractAllClaims(jwt))),
                    HttpStatus.OK);
        } catch (HttpServerErrorException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        }
    }

}
