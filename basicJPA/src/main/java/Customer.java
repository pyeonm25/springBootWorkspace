import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="customers")
@ToString
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본생성자를 jpa 만 생성할 수 있도록 해주기
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    LocalDate regDate;
    public Customer(String name ) {
        this.name = name;
        regDate = LocalDate.now();
    }

}
