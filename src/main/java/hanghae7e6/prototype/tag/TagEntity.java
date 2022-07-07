package hanghae7e6.prototype.tag;

import hanghae7e6.prototype.recruitposttag.RecruitPostTagEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Builder
@Table(name = "TAGS")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagEntity {

    @Id
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
