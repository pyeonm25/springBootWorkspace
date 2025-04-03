import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name="majors")
@ToString(exclude = "students")  // 항상 연관관계가 있는 필드는 toStirng에서 제외를 시켜줘야한다 : toString 무한루프 뜸
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="majar_id")
    private Long id;
    private String name;
    private String category;

    // 관계형 데이터베이스 mysql 에서는 생성이 안된다
    //학생은 하나의 전공, 전공은 여러학생을 가지고 있다
    @OneToMany(mappedBy = "major") // 연관관계 주인은 Student table의 major 프로퍼티라는 의미
    private List<Student> students = new ArrayList<>();  // 읽기전용

    public Major(String name, String category) {
        this.name = name;
        this.category = category;
    }
}