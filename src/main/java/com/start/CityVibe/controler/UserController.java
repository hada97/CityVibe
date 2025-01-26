package com.start.CityVibe.controler;

import com.start.CityVibe.domain.evento.Evento;
import com.start.CityVibe.domain.user.*;
import com.start.CityVibe.repository.UserRepository;
import com.start.CityVibe.service.EventoService;
import com.start.CityVibe.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;


@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "bearer-key")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EventoService eventoService;

    // URL para revogar o token de acesso do Google
    private static final String GOOGLE_REVOKE_URL = "https://oauth2.googleapis.com/revoke?token=";

    @GetMapping
    public Page<User> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return userService.getAll(page, size);
    }

    @GetMapping("/eventos/{id}")
    public Page<Evento> getUserEvents(
            @PathVariable Long id, // Pega o ID do usuário
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return eventoService.getEventosByUserId(id, page, size);
    }

    // Endpoint de login via OAuth2
    @GetMapping("/login/oauth2/code/google")
    public ResponseEntity<UserDetail> loginWithGoogle(OAuth2AuthenticationToken authentication) {
        OAuth2User oAuth2User = authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        if (!userService.existsByEmail(email)) {
            userService.registerNewUser(email, name);
        }
        Long userId = userService.getUserIdByEmailFromGoogle();

        UserDetail userDetail = new UserDetail(name, null);
        return ResponseEntity.ok(userDetail);
    }


    @GetMapping("/login/id")
    public ResponseEntity<Long> userIdByGoogleEmail(OAuth2AuthenticationToken authentication) {
        OAuth2User oAuth2User = authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        Long userId = userService.getUserIdByEmailFromGoogle();
        return ResponseEntity.ok(userId);
    }


    @PatchMapping()
    public ResponseEntity<Void> updateUser(@RequestBody UserUpdateDto data) {
        userService.updateUser(data);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PostMapping("/register")
    public ResponseEntity<UserDetail> createUser(@RequestBody @Valid UserDto user) {

        if (userService.existsByEmail(user.email())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new UserDetail("User already registered", null));
        }
        userService.registerNewUser(user.email(), user.name());
        UserDetail userDetail = new UserDetail(user.name(), null);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDetail);
    }


    @PostMapping("/logout")
    public ResponseEntity<Void> logout(OAuth2AuthenticationToken authentication, HttpServletRequest request) {
        // Invalidar a sessão após revogar o token
        request.getSession().invalidate();
        // Retorna sucesso se tudo ocorrer bem
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.status(302)
                    .header("Location", "/login.html")
                    .build();
        }
        return ResponseEntity.notFound().build();
    }

}