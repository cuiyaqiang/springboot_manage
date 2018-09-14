package com.guye.sun.managent.config.webSocket.config;

import com.guye.sun.managent.config.webSocket.handle.SocketHandle;
import com.guye.sun.managent.config.webSocket.interceptor.WebSocketInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * WebSocketConfig
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(new SocketHandle(),"/app")
                .addInterceptors(new WebSocketInterceptor())
                .setAllowedOrigins("*");
    }
}
