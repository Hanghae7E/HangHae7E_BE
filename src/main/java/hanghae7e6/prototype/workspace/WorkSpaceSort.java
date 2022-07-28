package hanghae7e6.prototype.workspace;


import org.springframework.data.domain.Sort;

public enum WorkSpaceSort {
    LATEST_WORKSPACE(Sort.Direction.ASC, "createdAt");

    private Sort.Direction direction;
    private String sortBy;

    WorkSpaceSort(Sort.Direction direction, String sortBy) {
        this.direction = direction;
        this.sortBy = sortBy;
    }

    public Sort getSort(){
        return Sort.by(this.direction, this.sortBy);
    }
}
