package hanghae7e6.prototype.profile.dto;



import hanghae7e6.prototype.exception.AbstractException;
import hanghae7e6.prototype.profile.entity.ProfileEntity;
import hanghae7e6.prototype.profile.entity.ProfileTagEntity;
import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import hanghae7e6.prototype.user.UserEntity;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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
    private List <?> registeredPosts;
    private List <?> applyPosts;

    @Builder
    public ProfileResponse(String username, String email,
        String phone_number, String profile_image_url, String residence,
        String available_period, String available_time, String position, List<String> fields,
        boolean face_to_face, List<String> skills, String career_period, String portfolio_url, List<?> registeredPosts, List<?> applyPosts) {
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
        this.registeredPosts = registeredPosts;
        this.applyPosts = applyPosts;
    }

    public static ProfileResponse toResponse(ProfileEntity profile, List<ProfileTagEntity> profileTags, List<RecruitPostEntity> myRecruitPosts, List <RecruitPostEntity> myAppliedRecruitPosts) throws AbstractException{
        UserEntity user = profile.getUser();

        List <String> fields = profileTags.stream().filter(profileTag -> profileTag.getProfileAttributeName().equals("field")).map(profileTag -> profileTag.getTag().getBody()).collect(
            Collectors.toList());
        List <String> skills = profileTags.stream().filter(profileTag -> profileTag.getProfileAttributeName().equals("skill")).map(profileTag -> profileTag.getTag().getBody()).collect(
            Collectors.toList());

        List<Map<String, String>> registeredPosts = myRecruitPosts.stream().map(post -> convertPostEntityToMap(post, user.getId())).collect(
            Collectors.toList());

        List<Map<String, String>> applyPosts = myAppliedRecruitPosts.stream().map(post -> convertPostEntityToMap(post, user.getId())).collect(
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
                                .registeredPosts(registeredPosts)
                                .applyPosts(applyPosts)
                                .build();
    }


    public static Map<String, String> convertPostEntityToMap(RecruitPostEntity entity, Long userId) throws AbstractException {
        int totalCapacity = entity.getRequiredDesigners() + entity.getRequiredProjectManagers() + entity.getRequiredDevelopers();
        int currentHeads = entity.getAcceptedApplicantByPosition("개발자").size()
                            + entity.getAcceptedApplicantByPosition("기획자").size()
                            + entity.getAcceptedApplicantByPosition("디자이너").size();


        Map<String, String> map = new HashMap<>();

        map.put("id", entity.getId().toString());
        map.put("title", entity.getTitle());
        map.put("recruit_status", entity.getRecruitStatus().toString());
        map.put("capacity", Integer.toString(currentHeads) + "/" + Integer.toString(totalCapacity));
        map.put("recruit_due_time", entity.getRecruitDueTime().toString());
        map.put("project_start_time", entity.getProjectStartTime().toString());
        map.put("project_end_time", entity.getProjectEndTime().toString());

        if (Objects.equals(entity.getUser().getId(), userId)) {
            map.put("status", entity.getRecruitStatus().toString());
        } else {
            String applyStatus = entity.getApplicants().stream()
                                         .filter(applicant -> Objects.equals(applicant.getUser().getId(), userId))
                                         .findFirst()
                                         .get().getStatus();

            map.put("status", applyStatus);
        }

        return map;
    }
}
