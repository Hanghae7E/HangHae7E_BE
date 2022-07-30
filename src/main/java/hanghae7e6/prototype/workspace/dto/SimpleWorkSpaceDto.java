package hanghae7e6.prototype.workspace.dto;

import hanghae7e6.prototype.workspace.WorkSpaceEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class SimpleWorkSpaceDto {
    private Long workSpaceId;
    private String title;

    public SimpleWorkSpaceDto(WorkSpaceEntity workSpace){
        this.workSpaceId = workSpace.getId();
        this.title = workSpace.getTitle();
    }

    public static List<SimpleWorkSpaceDto> toDto(Page<WorkSpaceEntity> workSpaces){
        return workSpaces.toList()
                .stream()
                .map(SimpleWorkSpaceDto::new)
                .collect(Collectors.toList());

    }

}
