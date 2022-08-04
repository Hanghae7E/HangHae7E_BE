package hanghae7e6.prototype.projectmember;

import hanghae7e6.prototype.profile.entity.ProfileEntity;
import hanghae7e6.prototype.user.UserEntity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProjectMemberDto {

     private Long userId;
     private String username;
     private String position;
     private String profileImageUrl;

     public ProjectMemberDto(ProjectMemberEntity projectMember){
          UserEntity user = projectMember.getUser();
          ProfileEntity profile = user.getProfile();

          this.userId = user.getId();
          this.username = user.getUsername();
          this.position = profile.getPosition();
          this.profileImageUrl = profile.getImageUrl();
     }
}
