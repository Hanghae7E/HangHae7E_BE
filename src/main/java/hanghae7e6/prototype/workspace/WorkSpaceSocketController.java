package hanghae7e6.prototype.workspace;

import hanghae7e6.prototype.workspace.dto.WorkSpacePubMsg;
import hanghae7e6.prototype.workspace.dto.WorkSpaceSubMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class WorkSpaceSocketController {


    private SimpMessageSendingOperations messageTemplate;

    @Autowired
    public WorkSpaceSocketController(SimpMessageSendingOperations messageTemplate) {
        this.messageTemplate = messageTemplate;
    }


    @MessageMapping("/workspace")
    public void sendWorkSpace(WorkSpacePubMsg message){
        String sub = message.getSubEndPoint("/sub/workspace");
        messageTemplate.convertAndSend(sub, new WorkSpaceSubMsg(message));
    }
}