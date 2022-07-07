package hanghae7e6.prototype.profile.dto;


import hanghae7e6.prototype.profile.entity.ProfileEntity;
import hanghae7e6.prototype.profile.entity.ProfileTagEntity;
import hanghae7e6.prototype.user.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProfileResponse {

    private String username;
    private String email;
    private String phone_number;
    private String profile_image_url;
    private String residence;
    private String available_period;
    private String available_time;
    private String position;
    private List<String> fields;
    private boolean face_to_face;
    private List<String> skills;
    private String career_period;
    private String portfolio_url;


    @Builder
    public ProfileResponse(String username, String email,
        String phone_number, String profile_image_url, String residence,
        String available_period, String available_time, String position, List<String> fields,
        boolean face_to_face, List<String> skills, String career_period, String portfolio_url) {
        this.username = username;
        this.email = email;
        this.phone_number = phone_number;
        this.profile_image_url = profile_image_url;
        this.residence = residence;
        this.available_period = available_period;
        this.available_time = available_time;
        this.position = position;
        this.fields = fields;
        this.face_to_face = face_to_face;
        this.skills = skills;
        this.career_period = career_period;
        this.portfolio_url = portfolio_url;
    }

    public static ProfileResponse toResponse(ProfileEntity profile, List<ProfileTagEntity> profileTags) {
        UserEntity user = profile.getUser();

        List <String> fields = profileTags.stream().filter(profileTag -> profileTag.getProfileAttributeName().equals("field")).map(profileTag -> profileTag.getTag().getBody()).collect(
            Collectors.toList());
        List <String> skills = profileTags.stream().filter(profileTag -> profileTag.getProfileAttributeName().equals("skill")).map(profileTag -> profileTag.getTag().getBody()).collect(
            Collectors.toList());


        return ProfileResponse.builder()
                                .username(user.getUsername())
                                .email(user.getEmail())
                                .phone_number(profile.getPhoneNumber())
                                .profile_image_url(profile.getImageUrl())
                                .residence(profile.getResidence())
                                .available_period(profile.getAvailablePeriod())
                                .available_time(profile.getAvailableTime())
                                .position(profile.getPosition())
                                .fields(fields)
                                .skills(skills)
                                .face_to_face(profile.isFaceToFace())
                                .career_period(profile.getCareerPeriod())
                                .portfolio_url(profile.getPortfolioUrl())
                                .build();
    }
}
