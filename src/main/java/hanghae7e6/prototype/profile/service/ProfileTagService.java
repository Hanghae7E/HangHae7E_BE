package hanghae7e6.prototype.profile.service;

import hanghae7e6.prototype.exception.AbstractException;
import hanghae7e6.prototype.profile.entity.ProfileEntity;
import hanghae7e6.prototype.profile.entity.ProfileTagEntity;
import hanghae7e6.prototype.profile.repository.ProfileTagRepository;
import hanghae7e6.prototype.tag.TagEntity;
import hanghae7e6.prototype.tag.TagRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class ProfileTagService {

    @Autowired
    ProfileTagRepository profileTagRepository;

    @Autowired
    TagRepository tagRepository;


    public List <TagEntity> getTagsByAttributeNameAndProfileId(String attrName, Long profileId) {
        List <ProfileTagEntity> profileTags = profileTagRepository.findAllByProfileAttributeNameAndProfileId(attrName, profileId);
        return profileTags.stream().map(ProfileTagEntity::getTag).collect(Collectors.toList());
    }

    @Transactional
    public void updateProfileTags(List <ProfileTagEntity> profileTags, List<String> fields, List<String> skills, ProfileEntity profile) throws AbstractException {
        if (profileTags == null) profileTags = new ArrayList<>();
        List <TagEntity> requestFieldTags = tagRepository.findByBodyIn(fields);
        List <TagEntity> requestSkillTags = tagRepository.findByBodyIn(skills);

        profileTags.clear();

        for (TagEntity fieldTag : requestFieldTags) {
                ProfileTagEntity newProfileTag = ProfileTagEntity.builder().profile(profile).tag(fieldTag).profileAttributeName("field").build();
                profileTags.add(newProfileTag);

        }

        for (TagEntity skillTag : requestSkillTags) {
                ProfileTagEntity newProfileTag = ProfileTagEntity.builder().profile(profile).tag(skillTag).profileAttributeName("skill").build();
                profileTags.add(newProfileTag);

        }
    }

}
