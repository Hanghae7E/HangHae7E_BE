package hanghae7e6.prototype.tag;

import hanghae7e6.prototype.common.BaseTimeEntity;
import hanghae7e6.prototype.recruitposttag.RecruitPostTagEntity;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@Table(name = "TAGS")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
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
