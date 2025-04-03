import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Table(name="students")
@Getter
@Setter

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="student_id")
    private Long studentId;
    private String name;
    private String grade;               // fetch = FetchType.LAZY 지연 로딩

    // 다대일 => 학생은 하나의 전공 , 전공은 여러학생
    @ManyToOne(fetch = FetchType.LAZY)  // 관계 구성    FetchType.EAGER(기본값): 즉시로딩 : 연관되어있는 모든 테이블 다 가져오기
    @JoinColumn(name="major_id")// 테이블 컬럼의 fk 명
    private Major major;  // marjor_id = 1

    // 일대일
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn( name="locker_id", unique = true )  // 이름 안주면 클래스이름_id 로 생성됨
    private Locker locker;  // locker_id 1를 누가 참조하고 있으면 다른 Student lock_id 1를 참조 불가

    public Student(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }


}