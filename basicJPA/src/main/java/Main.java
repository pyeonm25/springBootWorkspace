import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        // 마이베터스 -> sqlSessionFactory -> openSesstion
        // JPA -> EntityManagaFactory -> EntityManger

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("customer-ex");
       // System.out.println("emf = " + emf);
        EntityManager em = emf.createEntityManager(); // 마이베터스의 session 객체 // connection 객체
      //  System.out.println("em = " + em);
        EntityTransaction tx = em.getTransaction();
       // System.out.println("tx = " + tx);
        tx.begin(); // start(); 트랜지션 시작부분

        try{
//            // 비지니스 로직~~
//            Customer customer1 = new Customer("Park");
//            Customer customer2 = new Customer("Lee");
//            Customer customer3 = new Customer("Kim");
//            // 비영속 -> 영속
//            em.persist(customer1); // 영속성 컨텍스트 안에 저장
//            em.persist(customer2);
//            em.persist(customer3);

            Customer customer1 = em.find(Customer.class, 2L);
            System.out.println("customer1 = " + customer1);
            System.out.println("============================");
            Customer customer2 = em.find(Customer.class, 2L);
            System.out.println("customer2 = " + customer2);
            System.out.println(customer1 == customer2);
            System.out.println("============================");
           // em.detach(customer1);
            customer1.setName("개똥이22");  /// "Park"
            em.flush(); // 쓰기저장소에 있는 sql 명령문을 미리 db로 다 보낼 수 있다 .
            em.remove(customer1);

            tx.commit();      // 서비스 성공 -> 트렌지션 끝
        }catch(Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally{
            em.close();
            emf.close();  // 다시  emtityFactory session 자원을 반납
        }




    }
}
