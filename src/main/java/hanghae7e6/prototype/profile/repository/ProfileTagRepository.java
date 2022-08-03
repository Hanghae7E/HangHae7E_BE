package hanghae7e6.prototype.profile.repository;

import hanghae7e6.prototype.profile.entity.ProfileTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileTagRepository extends JpaRepository<ProfileTagEntity, Long> {

    List<ProfileTagEntity> findAllByProfileId(Long profileId);
    Optional<ProfileTagEntity> findByTagId(Long tagId);

    List<ProfileTagEntity> findAllByProfileAttributeNameAndProfileId(String profileAttributeName, Long profileId);
}
