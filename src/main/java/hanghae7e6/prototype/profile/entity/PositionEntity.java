package hanghae7e6.prototype.profile.entity;

import hanghae7e6.prototype.common.BaseTimeEntity;
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
@Table(name = "POSITIONS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PositionEntity extends BaseTimeEntity {

    @Id
    @Column(name = "POSITION_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "POSITION_NAME", unique = true)
    private String positionName;

    @Builder
    public PositionEntity(String positionName) {
        this.positionName = positionName;
    }
}
