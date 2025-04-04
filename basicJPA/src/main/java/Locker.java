import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Locker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="locker_id")
    private Long id;
    private int lockNo;

    // 관계형 데이터베이스 mysql 에서는 student 생성이 안된다
    @OneToOne(mappedBy = "locker") // studentTable locker 객체이름
    private Student student;

    public Locker(int lockNo) {
        this.lockNo = lockNo;
    }
}