package pacHibernate;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
//import org.hibernate.mapping.List;


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
		
		
		
		int numeroMenu, nId;
		Transaction tx;
		List<Libro> libros;
		List<Lector> lectores;
		List<Prestamo> prestamos;
		
        do{
        	
            numeroMenu = -1;
            System.out.printf("\n-------BIBLIOTECA-------\n");
            System.out.println("1: Insertar libro");
            System.out.println("2: Insertar lector");
            System.out.println("3: Listado de Libros");
            System.out.println("4: Listado de Lectores");
            System.out.println("5: Ver Libro por ID");
            System.out.println("6: Ver Lector por ID");
            System.out.println("7: Registrar un prestamo");
            System.out.println("8: Libros prestados a un lector");
            System.out.println("9: Libros disponibles");
            //System.out.println("10: Historial de prestamos por lector");
            System.out.println("0: Salir");
            System.out.println("---------------------------");
            
            System.out.printf("\nSeleccione una opcion: ");


            //sc.nextLine();
            numeroMenu = sc.nextInt();
            while(numeroMenu < 0 || numeroMenu > 8){
                System.out.println("Opcion Incorrecta\n Repite: ");
                numeroMenu = sc.nextInt();
                
            }
            sc.nextLine();
            switch (numeroMenu) {
                case 1:
                	tx = session.beginTransaction();
                	Libro libro = new Libro();
                	
                	System.out.println("Indica el título del libro: ");
                	libro.setTitulo(sc.nextLine());
                	
                	System.out.println("Indica el autor del libro: ");
                	libro.setAutor(sc.nextLine());
                	
                	System.out.println("Indica el año de publicación del libro: ");
                	libro.setAñoPublicacion(sc.nextInt());
                	
                	System.out.println("Indica si el libro se encuentra disponible[true/false]: ");
                    libro.setDisponible(sc.nextBoolean());

                	session.save(libro);
                	tx.commit();
                	System.out.println("LIBRO CREADO");

                	
                	
                    break;
                case 2:

                	tx = session.beginTransaction();
                	Lector lector = new Lector();
                	
                	System.out.println("Indica el nombre del lector: ");
                	lector.setNombre(sc.nextLine());
                	
                	System.out.println("Indica el apellido del lector: ");
                	lector.setApellido(sc.nextLine());
                	
                	System.out.println("Indica el email del lector: ");
                	lector.setEmail(sc.nextLine());
                	


                	session.save(lector);
                	tx.commit();
                	System.out.println("LECTOR CREADO");
                	
                	
                	
                	
                    break;
                case 3:
                	libros = session.createQuery("FROM Libro", Libro.class).getResultList();
                	for(Libro a : libros) {
                		System.out.println(a.toString());
                	}
                    
                    break;

                case 4:
                	lectores = session.createQuery("FROM Lector", Lector.class).getResultList();
                	for(Lector a : lectores) {
                		System.out.println(a.toString());
                	}
                    break;

                case 5:
                	
                	System.out.println("Indica el ID del libro que quieres ver");
                	nId = sc.nextInt();
                	libros = session.createQuery("FROM Libro", Libro.class).getResultList();
                	
                	System.out.println(libros.get(nId).toString());
                	
                    break;
                case 6:
                	
                	System.out.println("Indica el ID del lector que quieres ver");
                	nId = sc.nextInt();
                	
                	lectores = session.createQuery("FROM Lector", Lector.class).getResultList();
                	
                	System.out.println(lectores.get(nId).toString());
                	
                    break;
                case 7:
                	tx = session.beginTransaction();
                	Prestamo prestamo = new Prestamo();
                	
                	System.out.println("Indica la fecha del prestamo: ");
                	prestamo.setFechaPrestamo(sc.nextLine());
                	
                	System.out.println("Indica la fecha de devolucion del prestamo: ");
                	prestamo.setFechaDevolucion(sc.nextLine());
                	
                	System.out.println("Indica el ID del libro que se presta: ");
                	int lib = sc.nextInt() - 1;
                	libros = session.createQuery("FROM Libro", Libro.class).getResultList();
                	prestamo.setLibro(libros.get(lib));
                	
                	
                	//Si el libro no esta disponble pues tira atras. No necesita rollback porque no se ha confirmado nada.
                	if(libros.get(lib).isDisponible() == false) {
                		System.out.println("Este libro no esta disponible");
                		break;
                	}
                	//Indicamos que ese libro ya no esta disponible
                	libros.get(lib).setDisponible(false);
                	
                	
                	System.out.println("Indica el ID del Lector: ");
                	int lec = sc.nextInt() - 1;
                	lectores = session.createQuery("FROM Lector", Lector.class).getResultList();
                	prestamo.setLector(lectores.get(lec));
                	
                	

                	session.save(prestamo);
                	tx.commit();
                	System.out.println("PRESTAMO CREADO");
                	

                	break;
                	
                
                	
                case 8:
                	
                	libros = session.createQuery("FROM Libro WHERE disponible = false", Libro.class).getResultList();
            		System.out.println("LIBROS PRESTADOS:");

            		for (Libro libr : libros) {
            		    List<Integer> lectorIds = session.createQuery("SELECT p.lector.idLector FROM Prestamo p WHERE p.libro.idLibro = :libroId", Integer.class)
            		                                      .setParameter("libroId", libr.getIdLibro())
            		                                      .getResultList();
            		    System.out.println("Para el libro " + libr.getIdLibro() + ", los lectores son:");
            		    for (Integer lectorId : lectorIds) {
            		        System.out.println("Lector ID: " + lectorId);
            		    }
            		}

                	
                	break;
                case 9:
                	
                	libros = session.createQuery("FROM Libro WHERE disponible = true", Libro.class).getResultList();
            		System.out.println("LIBROS DISPONIBLES:");

                	for(Libro a : libros) {
                		System.out.println(a.toString());
                	}
                	
                	break;
                case 0:
                    System.out.printf("Gracias por usar este programa\n");
                    //sc.close();
                    break;

                

                default:
                    System.out.printf("No hay tantas opciones en el menu\n");
                    break;
            }
            
        }while(numeroMenu != 0);
        
        sc.close();
		
		
			
		
		session.close();
		sessionFactory.close();
	}
	
	
	
	
	
	
}


