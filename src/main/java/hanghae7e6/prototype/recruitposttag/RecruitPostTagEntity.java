package hanghae7e6.prototype.recruitposttag;

import hanghae7e6.prototype.common.BaseTimeEntity;
import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import hanghae7e6.prototype.tag.TagEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Entity
@Builder
@Table(name = "RECRUIT_POST_TAGS")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitPostTagEntity extends BaseTimeEntity {

    @Id
    @Column(name = "RECRUIT_POST_TAG_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECRUIT_POST_ID")
    private RecruitPostEntity recruitPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TAG_ID")
    private TagEntity tag;
}
