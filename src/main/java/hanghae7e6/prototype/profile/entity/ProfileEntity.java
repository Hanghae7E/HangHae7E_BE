package hanghae7e6.prototype.profile.entity;

import hanghae7e6.prototype.common.BaseTimeEntity;
import hanghae7e6.prototype.profile.dto.ProfileRequest;
import hanghae7e6.prototype.user.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Table(name = "PROFILES")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileEntity extends BaseTimeEntity {

    @Id
    @Column(name = "PROFILE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "POSITION_ID")
    private PositionEntity position;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProfileTagEntity> profileTags = new ArrayList<>();

    @Column(name = "PHONE_NUMBER", nullable = true)
    private String phoneNumber;

    @Column(name = "IMAGE_URL", nullable = true)
    private String imageUrl;

    @Column(name = "PORTFOLIO_URL", nullable = true)
    private String portfolioUrl;

    @Column(name = "AVAILABLE_PERIOD", nullable = true)
    private String availablePeriod;

    @Column(name = "AVAILABLE_TIME", nullable = true)
    private String availableTime;

    @Column(name = "FACE_TO_FACE", nullable = true)
    private boolean faceToFace;

    @Column(name = "CAREER_PERIOD", nullable = true)
    private String careerPeriod;

    @Column(name = "RESIDENCE", nullable = true)
    private String residence;

    @Builder
    public ProfileEntity(UserEntity user, PositionEntity position, String phoneNumber,
        String imageUrl, String portfolioUrl, String availablePeriod, String availableTime,
        boolean faceToFace, String careerPeriod, String residence) {
        this.user = user;
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.imageUrl = imageUrl;
        this.portfolioUrl = portfolioUrl;
        this.availablePeriod = availablePeriod;
        this.availableTime = availableTime;
        this.faceToFace = faceToFace;
        this.careerPeriod = careerPeriod;
        this.residence = residence;
    }

    public void update(ProfileRequest profileRequest, PositionEntity updatedPosition) {
        this.position = updatedPosition;
        this.phoneNumber = profileRequest.getPhone_number();
        this.imageUrl = profileRequest.getProfile_image_url();
        this.portfolioUrl = profileRequest.getPortfolio_url();
        this.availablePeriod = profileRequest.getAvailable_period();
        this.availableTime = profileRequest.getAvailable_time();
        this.faceToFace = profileRequest.isFace_to_face();
        this.careerPeriod = profileRequest.getCareer_period();
        this.residence = profileRequest.getResidence();
    }

    public String getPosition() {
        if (this.position == null) return "";
        return this.position.getPositionName();
    }

}
