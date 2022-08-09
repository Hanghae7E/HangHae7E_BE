package hanghae7e6.prototype.workspace.websocket;

import hanghae7e6.prototype.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class StompHandler implements ChannelInterceptor {


    public StompHandler(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    private JwtProvider jwtProvider;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
//        System.out.println("message:" + message);
//        System.out.println("헤더 : " + message.getHeaders());
//        System.out.println("토큰" + accessor.getNativeHeader("Authorization"));

        return message;
    }

    public void validateToken(StompHeaderAccessor accessor){
        String token = accessor.getFirstNativeHeader("Authorization");
        jwtProvider.validateToken(token);
    }
    }
