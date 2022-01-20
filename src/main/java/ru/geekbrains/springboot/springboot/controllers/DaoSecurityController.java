package ru.geekbrains.springboot.springboot.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.springboot.springboot.models.User;
import ru.geekbrains.springboot.springboot.services.UserService;

import java.security.Principal;

@RestController
@Profile("dao")
@Slf4j
@RequiredArgsConstructor
public class DaoSecurityController {
    private final UserService userService;

    @GetMapping("/dao")
    public String daoTestPage(Principal principal) {
//        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(
                principal.getName()
        ).orElseThrow(() -> new RuntimeException());
        return "authenticated: " + user.getUsername() + " : " + user.getEmail();
    }
}
