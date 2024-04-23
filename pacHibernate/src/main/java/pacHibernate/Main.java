package pacHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//Indicamos que queremos usar Hibernate
		Configuration cfg = new Configuration().configure();
		
		SessionFactory sessionFactory = cfg.buildSessionFactory(
				new StandardServiceRegistryBuilder().configure().build());
		Session session = sessionFactory.openSession();
		
		System.out.println("Configuracion Hibernate hecha");
		
		session.close();
		sessionFactory.close();
	}

}
