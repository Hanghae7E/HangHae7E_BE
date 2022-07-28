package hanghae7e6.prototype.workspace.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkSpacePubMsg {
    private String username;
    private WorkStatus workStatus;
    private Long workSpaceId;
    private String uuid;
    private String title;
    private String content;

    public String getSubEndPoint(String prefix){
        if(workStatus.equals(WorkStatus.EDITING)){
           return prefix + "/" + uuid + "/" + workSpaceId;
        }
        return prefix + "/" + uuid;
    }
}
