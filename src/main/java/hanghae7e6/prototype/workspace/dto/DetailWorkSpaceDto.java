package hanghae7e6.prototype.workspace.dto;

import hanghae7e6.prototype.workspace.WorkSpaceEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DetailWorkSpaceDto {

    private Long workSpaceId;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public DetailWorkSpaceDto(WorkSpaceEntity workSpace){
        this.workSpaceId = workSpace.getId();
        this.title = workSpace.getTitle();
        this.content = workSpace.getContent();
        this.createdAt = workSpace.getCreatedAt();
    }

}