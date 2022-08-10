package hanghae7e6.prototype.workspace.websocket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkSpacePubDto {

    private String username;
    private WorkStatus workStatus;
    private Long workSpaceId;
    private String title;
    private String content;

    public WorkSpacePubDto(WorkSpacePubMsg msg){
        this.title = msg.getTitle();
        this.content = msg.getContent();
        this.username = msg.getUsername();
        this.workStatus = msg.getWorkStatus();
        this.workSpaceId = msg.getWorkSpaceId();
    }
}