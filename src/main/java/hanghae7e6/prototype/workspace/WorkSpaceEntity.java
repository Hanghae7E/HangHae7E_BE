package hanghae7e6.prototype.workspace;

import hanghae7e6.prototype.workspace.dto.DetailWorkSpaceDto;
import hanghae7e6.prototype.workspace.tempteam.Team;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class WorkSpaceEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "CRAETED_AT")
    private LocalDateTime createdAt;

    public WorkSpaceEntity(Team team){
        this.team = team;
        this.title = "none";
        this.content = "none";
        this.createdAt = LocalDateTime.now();
    }

    public void update(DetailWorkSpaceDto dto){
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }
}
