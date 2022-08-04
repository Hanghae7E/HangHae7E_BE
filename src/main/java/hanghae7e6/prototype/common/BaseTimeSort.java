package hanghae7e6.prototype.common;


import org.springframework.data.domain.Sort;

public enum BaseTimeSort {
    LATEST_DATA(Sort.Direction.ASC, "createdAt");

    private Sort.Direction direction;
    private String sortBy;

    BaseTimeSort(Sort.Direction direction, String sortBy) {
        this.direction = direction;
        this.sortBy = sortBy;
    }

    public Sort getSort(){
        return Sort.by(this.direction, this.sortBy);
    }
}