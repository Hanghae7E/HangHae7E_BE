package hanghae7e6.prototype.user;

import hanghae7e6.prototype.common.BaseTimeEntity;
import hanghae7e6.prototype.profile.entity.ProfileEntity;
import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(name = "SOCIAL_TYPE", nullable = true)
    private String socialType;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE", nullable = true)
    private UserRole userRole;


    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private ProfileEntity profile;


    @Builder
    public UserEntity(Long id, String email, String username, UserRole userRole, String socialType) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.userRole = userRole;
        this.socialType = socialType;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public void setUsername(String username) {this.username = username;}
}
