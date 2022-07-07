package hanghae7e6.prototype.tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {

    List<TagEntity> findByBodyIn(List<String> bodies);
    boolean existsByBody(String body);
}
