package hanghae7e6.prototype.profile.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class ProfileRequest {

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


    public List<String> getFields() {
        if (this.fields == null)
            return new ArrayList<>();
        return fields;
    }

    public List<String> getSkills() {
        if (this.skills == null)
            return new ArrayList<>();
        return skills;
    }
}
