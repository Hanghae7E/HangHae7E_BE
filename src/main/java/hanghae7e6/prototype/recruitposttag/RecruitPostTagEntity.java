package hanghae7e6.prototype.recruitposttag;

import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import hanghae7e6.prototype.tag.TagEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@Table(name = "RECRUIT_POST_TAGS")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitPostTagEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECRUITPOST_ID")
    private RecruitPostEntity recruitPost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TAG_ID")
    private TagEntity tag;
}
