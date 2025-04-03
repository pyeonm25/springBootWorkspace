import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // 마이베터스 -> sqlSessionFactory -> openSesstion
        // JPA -> EntityManagaFactory -> EntityManger
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("customer-ex");
        EntityManager em = emf.createEntityManager(); // 마이베터스의 session 객체 // connection 객체
        EntityTransaction tx = em.getTransaction();
        tx.begin(); // start(); 트랜지션 시작부분
        try{
            // init(em); // 테이블 row 생성

            String query = "select s from Student s";
            List<Student> students = em.createQuery(query).getResultList();
            students.forEach(System.out::println);

            Major m = students.get(0).getMajor();
            System.out.println("m = " + m);

            Locker l = students.get(2).getLocker();
            System.out.println("l = " + l);

            tx.commit();      // 서비스 성공 -> 트렌지션 끝
        }catch(Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally{
            em.close();
            emf.close();  // 다시  emtityFactory session 자원을 반납
        }




    }



    public static void init(EntityManager em) {
        Student stu1 = new Student("김씨" , "1학년");
        Student stu2 = new Student("이씨" , "2학년");
        Student stu3 = new Student("박씨" , "3학년");
        Major m1 = new Major("컴싸","소프트엔지니어링");

        Locker locker1 = new Locker(1001);
        Locker locker2 = new Locker(1002);
        Locker locker3 = new Locker(1003);

        em.persist(stu1);
        em.persist(stu2);
        em.persist(stu3);
        em.persist(m1);
        em.persist(locker1);
        em.persist(locker2);
        em.persist(locker3);

        stu1.setMajor(m1); //
        stu2.setMajor(m1);
        stu3.setMajor(m1);
        stu1.setLocker(locker1);
        stu2.setLocker(locker2);
        stu3.setLocker(locker3);

    }
}
