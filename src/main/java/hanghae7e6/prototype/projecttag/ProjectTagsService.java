package hanghae7e6.prototype.projecttag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectTagsService {

  private final ProjectTagsRepository projectTagsRepository;


  //GetMapping
  public List<ProjectTagsEntity> getProjectTags() {
    return projectTagsRepository.findAll();
  }


}
