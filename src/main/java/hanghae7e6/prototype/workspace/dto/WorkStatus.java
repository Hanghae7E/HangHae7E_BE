package hanghae7e6.prototype.workspace.dto;

public enum WorkStatus {
    ENTER, // 팀 페이지 들어왔을때
    EDIT, // 워크 스페이스 수정을 시작
    EDITING, // 수정된 데이터를 보낼 때
    FINISH, // 수정 끝났을때
    LEAVE; // 팀 페이지에서 나갈때
}
