package com.example.websockt;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@SpringBootApplication
public class WebsocktApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsocktApplication.class, args);
    }

    @Component
    public class MeuWebSocketHandler extends TextWebSocketHandler {

        private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();

        @Override
        public void afterConnectionEstablished(WebSocketSession session) {
            sessions.add(session);
        }

        @Override
        public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
            sessions.remove(session);
        }

        public void enviarMensagemParaTodos(String mensagem) {
            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {
                    try {
                        session.sendMessage(new TextMessage(mensagem));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @RestController
    @RequestMapping("/atualizar")
    public class AtualizacaoController {

        private final MeuWebSocketHandler handler;

        public AtualizacaoController(MeuWebSocketHandler handler) {
            this.handler = handler;
        }

        @PostMapping
        public ResponseEntity<Void> enviarAtualizacao(@RequestBody String dados) {
            handler.enviarMensagemParaTodos("Atualização recebida: " + dados);
            return ResponseEntity.ok().build();
        }
    }

    @Configuration
    @EnableWebSocket
    public class WebSocketConfig implements WebSocketConfigurer {

        private final MeuWebSocketHandler handler;

        public WebSocketConfig(MeuWebSocketHandler handler) {
            this.handler = handler;
        }

        @Override
        public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
            registry.addHandler(handler, "/meu-websocket").setAllowedOrigins("*");
        }
    }
}
