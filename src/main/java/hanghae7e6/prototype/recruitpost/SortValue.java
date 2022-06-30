package hanghae7e6.prototype.recruitpost;

import org.springframework.data.domain.Sort;

public enum SortValue {
    LATEST_ORDER(Sort.Direction.ASC, "projectStartTime"),
    CLOSING_ORDER(Sort.Direction.DESC, "recruitDueTime");

    private Sort.Direction direction;

    private String sortBy;

    SortValue(Sort.Direction direction, String sortBy) {
        this.direction = direction;
        this.sortBy = sortBy;
    }

    public static Sort getSort(int order){
        SortValue sortValue = SortValue.values()[order];
        return Sort.by(sortValue.direction, sortValue.sortBy);
    }
}
