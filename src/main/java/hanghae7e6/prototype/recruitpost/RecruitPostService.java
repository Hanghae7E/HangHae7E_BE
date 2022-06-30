package hanghae7e6.prototype.recruitpost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitPostService {

    RecruitPostRepository recruitPostRepository;

    @Autowired
    public RecruitPostService(RecruitPostRepository recruitPostRepository){
        this.recruitPostRepository = recruitPostRepository;
    }

    public List<SimplePostResponseDto> getPosts(
            int limit, int page, int sortOrder, String tag){

        Page<RecruitPostEntity> posts;

//        if(tag.equals("all")){
//        }

        Sort sort = SortValue.getSort(sortOrder);
        Pageable pageable = PageRequest.of(page, limit, sort);

        posts = recruitPostRepository.findAll(pageable);

        return SimplePostResponseDto.getDtos(posts);
    }
}
