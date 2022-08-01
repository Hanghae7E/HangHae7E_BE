package hanghae7e6.prototype.workspace.dto;

import hanghae7e6.prototype.workspace.WorkSpaceEntity;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.asm.Advice;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class SimpleWorkSpaceDto {
    private Long workSpaceId;
    private String workSpaceTitle;
    private LocalDateTime createdAt;

    public SimpleWorkSpaceDto(WorkSpaceEntity workSpace){
        this.workSpaceId = workSpace.getId();
        this.workSpaceTitle = workSpace.getTitle();
        this.createdAt = workSpace.getCreatedAt();
    }

    public static List<SimpleWorkSpaceDto> toDto(Page<WorkSpaceEntity> workSpaces){
        return workSpaces.toList()
                .stream()
                .map(SimpleWorkSpaceDto::new)
                .collect(Collectors.toList());

    }
}
