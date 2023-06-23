package epicenergyservice.u2bw.auth;


import epicenergyservice.u2bw.auth.payloads.AuthenticationSuccessfullPayload;
import epicenergyservice.u2bw.exceptions.NotFoundException;
import epicenergyservice.u2bw.exceptions.UnauthorizedException;
import epicenergyservice.u2bw.utenti.Utente;
import epicenergyservice.u2bw.utenti.services.UtenteService;
import epicenergyservice.u2bw.utenti.payloads.UtenteCreatePayload;
import epicenergyservice.u2bw.utenti.payloads.UtenteLoginPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UtenteService utenteService;
    @Autowired
    private PasswordEncoder bcrypt;

    @PostMapping("/register")
    public ResponseEntity<Utente> register(@RequestBody @Validated UtenteCreatePayload body) {

        body.setPassword(bcrypt.encode(body.getPassword()));
        Utente createdUser = utenteService.create(body);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody UtenteLoginPayload body)
            throws NotFoundException {

        Utente utente = utenteService.findByUserName(body.getUsername());

        String plainPW = body.getPassword();
        String hashedPW = utente.getPassword();

        if (!bcrypt.matches(plainPW, hashedPW))
            throw new UnauthorizedException("Credenziali non valide");

        String token = JWTTools.createToken(utente);
        return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
    }

}
