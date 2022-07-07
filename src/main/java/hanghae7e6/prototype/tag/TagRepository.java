package hanghae7e6.prototype.tag;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {

    List<TagEntity> findByBodyIn(List<String> bodies);
    boolean existsByBody(String body);
}
