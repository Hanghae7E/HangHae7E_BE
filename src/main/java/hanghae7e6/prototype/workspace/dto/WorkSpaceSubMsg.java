package hanghae7e6.prototype.workspace.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkSpaceSubMsg {

    private String username;
    private WorkStatus workStatus;
    private Long workSpaceId;
    private String title;
    private String content;

    public WorkSpaceSubMsg(WorkSpacePubMsg msg){
        this.title = msg.getTitle();
        this.content = msg.getContent();
        this.username = msg.getUsername();
        this.workStatus = msg.getWorkStatus();
        this.workSpaceId = msg.getWorkSpaceId();
    }
}
