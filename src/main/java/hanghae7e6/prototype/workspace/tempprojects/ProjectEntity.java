package hanghae7e6.prototype.workspace.tempprojects;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectEntity {
    @Id
    @Column(name = "PROJECT_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String uuid;

    public ProjectEntity(Long id){
       this.id = id;
    }

}
