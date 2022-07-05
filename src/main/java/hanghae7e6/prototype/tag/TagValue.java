package hanghae7e6.prototype.tag;

import lombok.Getter;

@Getter
public enum TagValue {

    ALL(0L, "ALL"),
    DEVELOPER(1L, "개발자"),
    PM(2L, "프로젝트 매니저"),
    DESIGNER(3L, "디자이너"),
    FRONT(4L, "프론트엔드"),
    BACK(5L, "백엔드");

    private final Long tagId;

    private final String value;


    TagValue(Long tagId, String value){
        this.tagId = tagId;
        this.value = value;
    }

    public static boolean isAll(Long tagId){
        return ALL.getTagId().equals(tagId);
    }
}
