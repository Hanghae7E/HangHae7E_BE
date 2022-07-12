package hanghae7e6.prototype.tag;

import hanghae7e6.prototype.common.BaseTimeEntity;
import hanghae7e6.prototype.recruitposttag.RecruitPostTagEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Builder
@Table(name = "TAGS")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TAG_ID", nullable = false)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tag", orphanRemoval = true)
    private List<RecruitPostTagEntity> recruitPostTag;

    @Column(nullable = false, unique = true)
    private String body;

    public TagEntity(TagValue tag){
        this.id = tag.getTagId();
        this.body = tag.getValue();
    }

    public TagEntity(Long id){
        this.id = id;
    }
}
