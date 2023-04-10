package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

//@Entity(name = "Entity2")
//@SequenceGenerator(
//        name="member_seq_generator",
//        sequenceName = "member_seq", // 매핑 할 데이터베이스 시퀀스 이름
//        initialValue = 1,
//        allocationSize = 50 // 한번 호출 시 증가하는 수 (default: 50)
//)
public class Member {

    @Id // PK 셋팅
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "member_seq_generator")
    private Long id;

    @Column(name = "name", nullable = false) // DB Column명
    private String username;

    // JPA는 기본 생성자가 있어야 함.
    public Member() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
