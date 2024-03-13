package hello.login.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {
    private Long id; //데이터베이스에 저장되는 id

    @NotEmpty
    private String loginId; //회원가입에 입력하는 로그인 ID
    @NotEmpty
    private String name; //사용자 이름
    @NotEmpty
    private String password; //비밀번호
}
