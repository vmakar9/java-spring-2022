package com.example.javaspring2022.configs;


import com.example.javaspring2022.configs.dao.CustomMessageDAO;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@AllArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

    private CustomMessageDAO customMessageDAO;

    @Bean
    public WebSocketConnection webSocketConnection(){
        return new WebSocketConnection(customMessageDAO);
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketConnection(),"/chat")
                .setAllowedOrigins("*");
    }
}
