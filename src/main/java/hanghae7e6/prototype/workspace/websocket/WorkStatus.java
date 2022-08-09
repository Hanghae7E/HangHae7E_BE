package hanghae7e6.prototype.workspace.websocket;

import hanghae7e6.prototype.workspace.redis.UserStatusService;

import java.util.function.BiConsumer;

public enum WorkStatus {
    ENTER(setUserStatus()), // 팀 페이지 들어왔을때

    EDIT(setUserStatus()), // 워크 스페이스 수정을 시작
    EDITING((UserStatusService userStatusService, WorkSpacePubMsg pubMsg) -> {}), // 수정된 데이터를 보낼 때
    FINISH(setUserStatus()), // 수정 끝났을때
    LEAVE(deleteUserStatus()); // 팀 페이지에서 나갈때


    BiConsumer<UserStatusService, WorkSpacePubMsg> setUserData;

    WorkStatus(BiConsumer<UserStatusService, WorkSpacePubMsg> setUserData) {
        this.setUserData = setUserData;
    }

    private static BiConsumer<UserStatusService, WorkSpacePubMsg> setUserStatus(){
        return (UserStatusService userStatusService, WorkSpacePubMsg pubMsg) ->
            userStatusService.setMemberStatus(pubMsg.getUuid(), pubMsg.getUserId(), pubMsg.getWorkSpaceId());
    }

    private static BiConsumer<UserStatusService, WorkSpacePubMsg> deleteUserStatus(){
        return (UserStatusService userStatusService, WorkSpacePubMsg pubMsg) ->
            userStatusService.deleteStatus(pubMsg.getUserId(), pubMsg.getUuid());
    }
}