package hanghae7e6.prototype.profile.service;

import hanghae7e6.prototype.exception.AbstractException;
import hanghae7e6.prototype.profile.entity.ProfileEntity;
import hanghae7e6.prototype.profile.entity.ProfileTagEntity;
import hanghae7e6.prototype.profile.repository.ProfileTagRepository;
import hanghae7e6.prototype.tag.TagEntity;
import hanghae7e6.prototype.tag.TagRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProfileTagService {

    @Autowired
    ProfileTagRepository profileTagRepository;

    @Autowired
    TagRepository tagRepository;

    @Transactional
    public void updateProfileTags(List <ProfileTagEntity> profileTags, List<String>fieldTags, List<String> skills, ProfileEntity profile) throws AbstractException {
        List <TagEntity> requestFieldTags = tagRepository.findByBodyIn(fieldTags);
        List <TagEntity> requestSkillTags = tagRepository.findByBodyIn(skills);

        List <TagEntity> requestAllTags = Stream.concat(requestFieldTags.stream(), requestSkillTags.stream()).
                                                collect(Collectors.toList());

        profileTags.removeIf(profileTag -> !requestAllTags.contains(profileTag.getTag()));

        for (TagEntity fieldTag : requestFieldTags) {
            if (profileTags.stream().noneMatch(profileTag -> profileTag.getTag() == fieldTag)) {
                ProfileTagEntity newProfileTag = ProfileTagEntity.builder().profile(profile).tag(fieldTag).profileAttributeName("field").build();
                profileTagRepository.save(newProfileTag);
                profileTags.add(newProfileTag);
            }
        }

        for (TagEntity skillTag : requestSkillTags) {
            if (profileTags.stream().noneMatch(profileTag -> profileTag.getTag() == skillTag)) {
                ProfileTagEntity newProfileTag = ProfileTagEntity.builder().profile(profile).tag(skillTag).profileAttributeName("skill").build();
                profileTagRepository.save(newProfileTag);
                profileTags.add(newProfileTag);
            }
        }
    }

}
