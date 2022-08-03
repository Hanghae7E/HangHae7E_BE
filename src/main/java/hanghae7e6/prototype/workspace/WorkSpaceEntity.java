package hanghae7e6.prototype.workspace;

import hanghae7e6.prototype.common.BaseTimeEntity;
import hanghae7e6.prototype.project.ProjectEntity;
import hanghae7e6.prototype.workspace.dto.DetailWorkSpaceDto;
import hanghae7e6.prototype.workspace.dto.WorkSpaceSubMsg;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "WORK_SPACE")
@EntityListeners(AuditingEntityListener.class)
public class WorkSpaceEntity extends BaseTimeEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PROJECT_ID")
    private ProjectEntity project;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;


    public WorkSpaceEntity(ProjectEntity project){
        this.project= project;
        this.title = "none";
        this.content = "none";
    }


    public void update(WorkSpaceSubMsg dto){
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }
}