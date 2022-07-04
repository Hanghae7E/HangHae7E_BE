package hanghae7e6.prototype.domain.entity;

import javax.persistence.*;

import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USERS")
public class UserEntity extends BaseTimeEntity {

    @Id
    @Column(name = "USER_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
    private List<RecruitPostEntity> posts;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(name = "PHONE_NUMBER", nullable = true)
    private String phoneNumber;

    @Column(name = "IMAGE_URL", nullable = true)
    private String imageUrl;

    @Column(name = "SOCIAL_TYPE", nullable = false)
    private String socialType;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE", nullable = false)
    private UserRole userRole;

    @Builder
    public UserEntity(Long id, String email, String username,
        String phoneNumber, String imageUrl, UserRole userRole, String socialType) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.imageUrl = imageUrl;
        this.userRole = userRole;
        this.socialType = socialType;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
