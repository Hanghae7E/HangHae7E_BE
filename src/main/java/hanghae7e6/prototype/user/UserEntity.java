package hanghae7e6.prototype.user;

import hanghae7e6.prototype.common.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


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
}
