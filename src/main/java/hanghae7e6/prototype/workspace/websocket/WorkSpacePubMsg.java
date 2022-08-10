package hanghae7e6.prototype.workspace.websocket;

import hanghae7e6.prototype.security.jwt.JwtProvider;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class WorkSpacePubMsg {


    private String username; // 프론트와 이야기 해서 삭제하기
    private Long userId;
    private WorkStatus workStatus;
    private Long workSpaceId;
    private String uuid;
    private String title;
    private String content;
    private String token;


    public WorkSpacePubMsg(Map<String, ?> message){
        this.username = message.get("username").toString();
        this.uuid = message.get("uuid").toString();
        this.title = message.get("title").toString();
        this.content = message.get("content").toString();
        this.workStatus = WorkStatus.valueOf(message.get("workStatus").toString());
        this.workSpaceId = Long.valueOf(message.get("workSpaceId").toString());
        this.token = message.get("Authorization").toString();
    }


    public String getSubEndPoint(String prefix){
        if(workStatus.equals(WorkStatus.ENTER) || workStatus.equals(WorkStatus.LEAVE)){
            return prefix + "/" + uuid;
        }
        return prefix + "/" + uuid + "/" + workSpaceId;
    }

    public Map<String, String> getSubMsg(){
        Map<String, String> subMsg = new HashMap<>();

        subMsg.put("username", username);
        subMsg.put("userId", userId.toString());
        subMsg.put("title", title);
        subMsg.put("content", content);
        subMsg.put("workStatus", workStatus.toString());
        subMsg.put("workSpaceId", workSpaceId.toString());

        return subMsg;
    }


    public void validateToken(JwtProvider jwtProvider, String secretWord){
        if(jwtProvider.validateToken(token)){
            String secretKey = Base64.getEncoder().encodeToString(secretWord.getBytes());
            String userId = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody()
                    .get("userId", String.class);

            this.userId = Long.valueOf(userId);
        }

    }


}