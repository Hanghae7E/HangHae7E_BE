package hanghae7e6.prototype.recruitpost;

import com.querydsl.core.types.OrderSpecifier;
import lombok.Getter;
import org.springframework.data.domain.Sort;

import static hanghae7e6.prototype.recruitpost.QRecruitPostEntity.recruitPostEntity;

@Getter
public enum SortValue {
    LATEST_ORDER(Sort.Direction.ASC, "createdAt",
            recruitPostEntity.createdAt.asc()),
    CLOSING_ORDER(Sort.Direction.DESC, "recruitDueTime",
            recruitPostEntity.recruitDueTime.desc());

    private Sort.Direction direction;

    private String sortBy;

    private OrderSpecifier<?> orderSpecifier;

    SortValue(Sort.Direction direction, String sortBy, OrderSpecifier<?> orderSpecifier) {
        this.direction = direction;
        this.sortBy = sortBy;
        this.orderSpecifier = orderSpecifier;
    }

    public static Sort getSort(int order){
        SortValue sort = SortValue.values()[order];
        return Sort.by(sort.direction, sort.sortBy);
    }

    public static OrderSpecifier<?> getOrderSpecifier(int order){
        SortValue sort = SortValue.values()[order];
        return sort.getOrderSpecifier();
    }

}
