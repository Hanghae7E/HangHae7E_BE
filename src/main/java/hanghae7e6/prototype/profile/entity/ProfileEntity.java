package hanghae7e6.prototype.profile.entity;

import hanghae7e6.prototype.common.BaseTimeEntity;
import hanghae7e6.prototype.profile.dto.ProfileRequest;
import hanghae7e6.prototype.user.UserEntity;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @BatchSize(size = 1)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "POSITION_ID")
    private PositionEntity position;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProfileTagEntity> profileTags = new ArrayList<>();

    @Column(name = "PHONE_NUMBER", columnDefinition = "varchar(255) default ''")
    private String phoneNumber;

    @Column(name = "IMAGE_URL", columnDefinition = "varchar(255) default ''")
    private String imageUrl;

    @Column(name = "PORTFOLIO_URL", columnDefinition = "varchar(255) default ''")
    private String portfolioUrl;

    @Column(name = "AVAILABLE_PERIOD", columnDefinition = "varchar(255) default ''")
    private String availablePeriod;

    @Column(name = "AVAILABLE_TIME", columnDefinition = "varchar(255) default ''")
    private String availableTime;

    @Column(name = "FACE_TO_FACE", columnDefinition = "boolean default false ")
    private Boolean faceToFace;

    @Column(name = "CAREER_PERIOD", columnDefinition = "varchar(255) default ''")
    private String careerPeriod;

    @Column(name = "RESIDENCE", columnDefinition = "varchar(255) default ''")
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
        this.position = updatedPosition != null? updatedPosition : this.position;
        this.phoneNumber = Optional.ofNullable(profileRequest.getPhone_number()).orElseGet(this::getPhoneNumber);
        this.portfolioUrl = Optional.ofNullable(profileRequest.getPortfolio_url()).orElseGet(this::getPortfolioUrl);
        this.availablePeriod = Optional.ofNullable(profileRequest.getAvailable_period()).orElseGet(this::getAvailablePeriod);
        this.availableTime = Optional.ofNullable(profileRequest.getAvailable_time()).orElseGet(this::getAvailableTime);
        this.faceToFace = Optional.ofNullable(profileRequest.getFace_to_face()).orElseGet(this::getFaceToFace);
        this.careerPeriod = Optional.ofNullable(profileRequest.getCareer_period()).orElseGet(this::getCareerPeriod);
        this.residence = Optional.ofNullable(profileRequest.getResidence()).orElseGet(this::getResidence);
    }

    public String getPosition() {
        if (this.position == null) return "";
        return this.position.getPositionName();
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public boolean isFaceToFace() {
        return this.faceToFace;
    }
}
