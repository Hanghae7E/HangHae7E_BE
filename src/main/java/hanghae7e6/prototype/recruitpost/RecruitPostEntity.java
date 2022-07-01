package hanghae7e6.prototype.recruitpost;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitPostEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private int totalMemderCount;

    @Column
    private LocalDateTime projectStartTime;

    @Column
    private LocalDateTime projectEndTime;

    @Column
    private LocalDateTime recruitDueTime;

    @Column
    private String imageUrl;

}
