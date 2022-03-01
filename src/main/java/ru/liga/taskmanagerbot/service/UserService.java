package ru.liga.taskmanagerbot.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.liga.taskmanagerbot.exception.UserNotFoundException;

@Service
public class UserService {
    private final WebClient webClient;

    public UserService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String getUserByIdSync(final String id) {
        return webClient
                .get()
                .uri(String.join("", "/users/", id))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.just(new UserNotFoundException("user ".concat(id).concat(" not found"))))
                .bodyToMono(String.class)
                .block();
    }

}
