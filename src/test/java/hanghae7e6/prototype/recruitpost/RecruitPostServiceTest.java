package hanghae7e6.prototype.recruitpost;

import hanghae7e6.prototype.recruitpost.dto.DetailPostResponseDto;
import hanghae7e6.prototype.recruitpost.dto.PostParamDto;
import hanghae7e6.prototype.recruitpost.dto.SimplePostResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecruitPostServiceTest {

    @InjectMocks
    RecruitPostService recruitPostService;

    @Mock
    RecruitPostRepository recruitPostRepository;


    private Sort sort;
    private Pageable pageable;
    private RecruitPostEntity recruitPostEntity;
    private PostParamDto postParamDto;
    private DetailPostResponseDto detailPostResponseDto;

    @BeforeEach
    void setTest(){

        recruitPostEntity = RecruitPostEntity.builder()
                        .body("프로젝트 모집 내용")
                        .title("프로젝트 모집")
                        .imageUrl("abc/abc")
                        .projectStartTime(LocalDateTime.now())
                        .projectEndTime(LocalDateTime.now())
                        .recruitDueTime(LocalDateTime.now())
                        .totalMemderCount(1)
                        .build();

        postParamDto = PostParamDto.builder()
                .limit(2)
                .sort(0)
                .page(1)
                .tag("all")
                .build();

        sort = SortValue.getSort(postParamDto.getSort());
        pageable = PageRequest.of(postParamDto.getPage()
                ,postParamDto.getLimit(), sort);

    }

    @Test
    @DisplayName("전체 게시글 가져오기")
    void testGetPosts(){
        // given
        List<RecruitPostEntity> posts = new ArrayList<>(
                Arrays.asList(recruitPostEntity, recruitPostEntity)
        );

        // when
        when(recruitPostRepository.findAll(pageable))
                .thenReturn(new PageImpl<>(posts));

        List<SimplePostResponseDto> result = recruitPostService.getPosts(postParamDto);

        // then
        for(int i=0; i<result.size(); i++){
            SimplePostResponseDto dto = result.get(i);
            RecruitPostEntity post = posts.get(i);

            assertAll(
                    () -> assertThat(dto.getTitle()).isEqualTo(post.getTitle()),
                    () -> assertThat(dto.getRecruitDueTime()).isEqualTo(post.getRecruitDueTime()),
                    () -> assertThat(dto.getProjectStartTime()).isEqualTo(post.getProjectStartTime()),
                    () -> assertThat(dto.getProjectEndTime()).isEqualTo(post.getProjectEndTime()),
                    () -> assertThat(dto.getProjectImage()).isEqualTo(post.getImageUrl()),
                    () -> assertThat(result.size()).isEqualTo(postParamDto.getLimit())
            );
        }
    }

    @Test
    @DisplayName("상세 게시글 가져오기")
    void testGetPost(){
        // given
        Long postId = 0L;

        // when
        when(recruitPostRepository.findById(postId))
                .thenReturn(Optional.of(recruitPostEntity));

        DetailPostResponseDto dto = recruitPostService.getPost(postId);

        // then
        assertAll(
                () -> assertThat(dto.getTitle()).isEqualTo(recruitPostEntity.getTitle()),
                () -> assertThat(dto.getBody()).isEqualTo(recruitPostEntity.getBody()),
                () -> assertThat(dto.getProjectStartTime()).isEqualTo(recruitPostEntity.getProjectStartTime()),
                () -> assertThat(dto.getProjectEndTime()).isEqualTo(recruitPostEntity.getProjectEndTime()),
                () -> assertThat(dto.getRecruitDueTime()).isEqualTo(recruitPostEntity.getRecruitDueTime()),
                () -> assertThat(dto.getTotalHeadCount()).isEqualTo(recruitPostEntity.getTotalMemderCount())
        );

    }
}