package pacHibernate;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
public class Main {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//Indicamos que queremos usar Hibernate
		Configuration cfg = new Configuration().configure();
		
		SessionFactory sessionFactory = cfg.buildSessionFactory(
				new StandardServiceRegistryBuilder().configure().build());
		Session session = sessionFactory.openSession();
		
		System.out.println("Configuracion Hibernate hecha");
		
		
		
		int numeroMenu;
        
        do{
        	
            numeroMenu = -1;
            System.out.printf("\n-------BIBLIOTECA-------\n");
            System.out.println("1: Insertar libro");
            System.out.println("2: Insertar lector");
            System.out.println("3: Listado de Libros");
            System.out.println("4: Listado de Lectores");
            System.out.println("5: Ver Libro por ID");
            System.out.println("6: Ver Lector por ID");
            System.out.println("7: Salir");
            System.out.println("---------------------------");
            System.out.printf("\nSeleccione una opcion: ");

            System.out.printf("Introduce opcion\n");
            //sc.nextLine();
            numeroMenu = sc.nextInt();
            while(numeroMenu < 1 || numeroMenu > 7){
                System.out.println("Opcion Incorrecta\n Repite: ");
                numeroMenu = sc.nextInt();
                
            }
            sc.nextLine();
            switch (numeroMenu) {
                case 1:
                    
                	
                	
                	
                	
                    break;
                case 2:

                    
                	
                	
                	
                	
                	
                    break;
                case 3:
  

                    
                    break;

                case 4:
                    
                    break;

                case 5:
                    
                    break;
                case 6:
                    
                    break;
                case 7:
                    System.out.printf("Gracias por usar este programa\n");
                    //sc.close();
                    break;

                case 0:

                default:
                    System.out.printf("No hay tantas opciones en el menu\n");
                    break;
            }
            
        }while(numeroMenu != 7);
        
        sc.close();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		session.close();
		sessionFactory.close();
	}
	
	
	
	
	
	
}


