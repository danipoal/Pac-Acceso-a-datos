package pacHibernate;





public class Alumno {
	
	private int idAlumno;
	private String nombre;
	private int edad;
	
	
	public Alumno(int idAlumno, String nombre, int edad) {
		super();	
		this.idAlumno = idAlumno;
		this.nombre = nombre;
		this.edad = edad;
	}
	

	
	public int getIdAlumno() {
		return idAlumno;
	}
	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}



	@Override
	public String toString() {
		return "Alumno [idAlumno=" + idAlumno + ", nombre=" + nombre + ", edad=" + edad + "]";
	}
	
}
