package hanghae7e6.prototype.workspace.websocket;

import hanghae7e6.prototype.security.jwt.JwtProvider;
import hanghae7e6.prototype.workspace.redis.UserStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class WebSocketController {


    private final SimpMessagingTemplate template;
    private final UserStatusService userStatusService;
    private final JwtProvider jwtProvider;

    @Autowired
    public WebSocketController(SimpMessagingTemplate template, UserStatusService userStatusService, JwtProvider jwtProvider) {
        this.template = template;
        this.userStatusService = userStatusService;
        this.jwtProvider = jwtProvider;
    }


    @MessageMapping("/workspace")
    public void sendWorkSpace(@Payload Map<String, ?> message){

        WorkSpacePubMsg pubMsg = new WorkSpacePubMsg(message);
        pubMsg.validateToken(jwtProvider);

        WorkStatus workStatus = pubMsg.getWorkStatus();
        workStatus.setUserData.accept(userStatusService, pubMsg);

        String sub = pubMsg.getSubEndPoint("/sub/workspace");
        Map<String, String> subMessage = pubMsg.getSubMsg();

        template.convertAndSend(sub, subMessage);
    }


}
