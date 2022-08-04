package hanghae7e6.prototype.user;

import hanghae7e6.prototype.common.BaseTimeEntity;
import hanghae7e6.prototype.profile.entity.ProfileEntity;
import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
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

<<<<<<< HEAD
    @BatchSize(size = 1)
    @OneToOne(mappedBy = "user")
    private ProfileEntity profile;

=======

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private ProfileEntity profile;


>>>>>>> 3b6fc400c7ded6e0db2efc3b4cf744b166a9d88b
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
