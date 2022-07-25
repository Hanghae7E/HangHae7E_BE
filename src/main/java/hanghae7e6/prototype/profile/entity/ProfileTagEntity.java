package hanghae7e6.prototype.profile.entity;

import hanghae7e6.prototype.common.BaseTimeEntity;
import hanghae7e6.prototype.tag.TagEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PROFILE_TAGS")
public class ProfileTagEntity extends BaseTimeEntity {

    @Id
    @Column(name = "PROFILE_TAG_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PROFILE_ID")
    private ProfileEntity profile;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TAG_ID")
    private TagEntity tag;

    @Column(name = "PROFILE_ATTRIBUTE_NAME")
    private String profileAttributeName;

    @Builder
    public ProfileTagEntity(ProfileEntity profile, TagEntity tag, String profileAttributeName) {
        this.profile = profile;
        this.tag = tag;
        this.profileAttributeName = profileAttributeName;
    }

}
