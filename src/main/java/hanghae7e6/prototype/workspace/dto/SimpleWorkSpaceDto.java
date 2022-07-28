package hanghae7e6.prototype.workspace.dto;

import hanghae7e6.prototype.workspace.WorkSpaceEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleWorkSpaceDto {
    private Long workSpaceId;
    private String title;

    public SimpleWorkSpaceDto(WorkSpaceEntity workSpace){
        this.workSpaceId = workSpace.getId();
        this.title = workSpace.getTitle();
    }
}
