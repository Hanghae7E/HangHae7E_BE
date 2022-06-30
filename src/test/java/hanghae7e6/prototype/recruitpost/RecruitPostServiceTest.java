package hanghae7e6.prototype.recruitpost;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecruitPostServiceTest {

    @InjectMocks
    RecruitPostService recruitPostService;

    @Mock
    RecruitPostRepository recruitPostRepository;


    public Sort sort;
    public Pageable pageable;

    @BeforeEach
    public void setTest(){
        Sort sort = SortValue.getSort(0);
        Pageable pageable = PageRequest.of(1,10, sort);

    }

//    @Test
//    public void testGetPosts(){
//        when(recruitPostRepository.findAll(pageable))
//                .thenReturn((Page<RecruitPostEntity>) new ArrayList<>());
//    }
}