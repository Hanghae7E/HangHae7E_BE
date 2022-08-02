package hanghae7e6.prototype.workspace;

import hanghae7e6.prototype.workspace.dto.WorkSpacePubMsg;
import hanghae7e6.prototype.workspace.dto.WorkSpaceSubMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Slf4j
@Controller
//@CrossOrigin(origins = "*", allowCredentials = Boolean.TRUE)
public class WebSocketController {

    private final SimpMessagingTemplate template;

    public WebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }


    @MessageMapping("/workspace")
    public void sendWorkSpace(@Payload WorkSpacePubMsg message){
        String sub = message.getSubEndPoint("/sub/workspace");
        log.info(message.getWorkStatus().toString());
        log.info(sub);
//        log.info(message.getContent());
        template.convertAndSend(sub, message); // 받는 메시지와 보내는 메시지가 같은 타입이어야 한다
    }
}
