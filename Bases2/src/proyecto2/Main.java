package proyecto2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.mysql.cj.jdbc.DatabaseMetaData;

public class Main {

	public static void main(String[] args)
			throws IOException, NumberFormatException, ClassNotFoundException, SQLException {
		// H2, mysql, sqllite

		menu();//muestra el primer menu en el que se da la opcion deelegir una de las 4 agencias de turismo

	}


	private static void menu() throws NumberFormatException, IOException, ClassNotFoundException, SQLException {
		boolean seguir = false;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		do {
			System.out.println(
					"Bienvenido!¿que agencia deseas consultar?: \n1.-Agencia Pinkilo (MySQL) \n2.-Agencia Avian(H2) \n3.-Agencia Caloriego(SQLlite) \n4.-Fin");
			int opcion=0;
			try {
				opcion = Integer.parseInt(reader.readLine());
			} catch (NumberFormatException e) {
				System.out.println("escribe un valor númerico");
			} 
			
			switch (opcion) {
			case 1: {
				Mpinkilo(reader);
				break;
			}
			case 2: {
				Mavian(reader);
				break;
			}
			case 3: {
				Mcaloriego(reader);
				break;
			}
			case 4: {
				System.out.println("Fin del programa");
				seguir = true;//para que termine el programa entero
				break;
			}

			default:
				System.out.println("opcion no valida " );
			}
		} while (!seguir);//hasta que no ponga una opcion valida o 4 se seguira repitiendo.

	

	}
	
	//menu AVIAN
	private static void Mavian(BufferedReader reader) throws NumberFormatException, ClassNotFoundException, IOException, SQLException {
		
		//H2
		
		try {
			Class.forName("org.h2.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/turismo", "root","soylalecheN7");
		
		
		
		
		
		boolean seguir=false;
		boolean correcto= false;


		do {
			System.out.println(
					"Bienvenido a la agencia de turismo AVIAN :\n1.-Ver datos de empresa \n2.-ver Empleados \n3.-ver Visitas guiadas \n4.-ver Clientes \n5.-Ver clientes en visitas guiadas \n6.-eliminar visita guiada \n7.-eliminar cliente \n8.-Despedir empleado"
							+ " \n9.-añadir nuevo cliente  \n10.-contratar empleado \n11.-nueva visita guiada \n12.-añadir cliente a una visita \n13.-asignar una visita a un empleado \n14.-editar visita guiada \n15.-editar cliente"
							+ "\n16.-información de la base de datos \n17.-volver al menú principal");
			int eleccion=0;
			
			do {
				try {
					eleccion = Integer.parseInt(reader.readLine());
					if(eleccion>0) {
						correcto=true;
					}
				} catch (NumberFormatException e) {
					System.out.println("escribe un valor numerico");
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			} while (!correcto);//hasta que no meta un número superior a 0 no avanzara
			
		
		switch (eleccion) {
		//apartir de aquie se llama a cada metodo correspondiente a la opcion selecionada y tras terminar el proceso volvera a mostrar el menu de la agencia
		case 1: {
			verEmpresa(conexion);
			Mavian(reader);
			break;
		}
		case 2: {
			Empleados.verEmpleados(conexion);
			Mavian(reader);
			break;
		}
		case 3: {
			Visitas.verVisitas(conexion);
			Mavian(reader);
			break;
		}
		case 4: {
			Clientes.verClientes(conexion);
			Mavian(reader);
			break;
		}
		case 5: {
			Visitas.clientesEnvisita(conexion, reader);
			Mavian(reader);
			break;

		}
		case 6: {
			Visitas.eliminaVisita(conexion, reader);
			Mavian(reader);
			break;
		}
		case 7: {
			Clientes.eliminarCliente(conexion, reader);
			Mavian(reader);
			break;
		}
		case 8: {
			Empleados.eliminarEmpleado(conexion, reader);
			Mavian(reader);
			break;
		}
		case 9: {
			Clientes.meterCliente(conexion, reader);
			Mavian(reader);
			break;
		}
		case 10: {
			Empleados.meterEmpleado(conexion, reader);
			Mavian(reader);
			break;
		}
		case 11: {
			Visitas.meterVisita(conexion, reader);
			Mavian(reader);
			break;
		}
		case 12: {
			Clientes.registrarClienteVisi(conexion, reader);
			Mavian(reader);
			break;
		}
		case 13: {
			Empleados.asignarEmpleado(conexion, reader);
			Mavian(reader);
			break;

		}
		case 14: {
			Empleados.editarVisita(conexion, reader);
			Mavian(reader);
			break;
		}
		case 15: {
			Clientes.editarCliente(conexion, reader);
			Mavian(reader);
			break;
		}
		case 16: {
			DatosBD2(conexion);
			Mavian(reader);
			break;
		}
		case 17: {
			seguir=true;
			menu();//volver al menu de eleccion de agencias
			break;
		}
		default:
			throw new IllegalArgumentException("esta opción no sirve: " + eleccion);
		}
		}while(!seguir);
		
		} catch (ClassNotFoundException e1) {
			System.out.println("Error al intentar conectarse a la base de datos, asegurate de que esta conectada");
		} catch (SQLException e1) {
			System.out.println("Error al intentar conectarse a la base de datos, asegurate de que esta conectada");
		}
		
	}

	//menu CALORIEGO
	private static void Mcaloriego(BufferedReader reader)throws ClassNotFoundException, SQLException, NumberFormatException, IOException {
		boolean seguir=false;
		boolean correcto= false;
		// SQLite
		
		
		try {
			Class.forName("org.sqlite.JDBC");
		Connection	conexion = DriverManager.getConnection("jdbc:sqlite:/Users/lais/Desktop/Turismo.db");
		


		do {
			System.out.println(
					"Bienvenido a la agencia de turismo CALORIEGO :\n1.-Ver datos de empresa \n2.-ver Empleados \n3.-ver Visitas guiadas \n4.-ver Clientes \n5.-Ver clientes en visitas guiadas \n6.-eliminar visita guiada \n7.-eliminar cliente \n8.-Despedir empleado"
							+ " \n9.-añadir nuevo cliente  \n10.-contratar empleado \n11.-nueva visita guiada \n12.-añadir cliente a una visita \n13.-asignar una visita a un empleado \n14.-editar visita guiada \n15.-editar cliente"
							+ "\n16.-información de la base de datos \n17.-volver al menú principal");
			int eleccion=0;
			do {
				try {
					eleccion = Integer.parseInt(reader.readLine());
					if(eleccion>0) {
						correcto=true;
					}
				} catch (NumberFormatException e) {
					System.out.println("escribe un valor numerico");
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			} while (!correcto);//hasta que no se meta un numero superior a 0 el bucle no terminara
			
			
			
			switch (eleccion) {
			case 1: {
				verEmpresa(conexion);
				Mcaloriego(reader);
				break;
			}
			case 2: {
				Empleados.verEmpleados(conexion);
				Mcaloriego(reader);
				break;
			}
			case 3: {
				Visitas.verVisitas(conexion);
				Mcaloriego(reader);
				break;
			}
			case 4: {
				Clientes.verClientes(conexion);
				Mcaloriego(reader);
				break;
			}
			case 5: {
				Visitas.clientesEnvisita(conexion, reader);
				Mcaloriego(reader);
				break;

			}
			case 6: {
				Visitas.eliminaVisita(conexion, reader);
				Mcaloriego(reader);
				break;
			}
			case 7: {
				Clientes.eliminarCliente(conexion, reader);
				Mcaloriego(reader);
				break;
			}
			case 8: {
				Empleados.eliminarEmpleado(conexion, reader);
				Mcaloriego(reader);
				break;
			}
			case 9: {
				Clientes.meterCliente(conexion, reader);
				Mcaloriego(reader);
				break;
			}
			case 10: {
				Empleados.meterEmpleado(conexion, reader);
				Mcaloriego(reader);
				break;
			}
			case 11: {
				Visitas.meterVisita(conexion, reader);
				Mcaloriego(reader);
				break;
			}
			case 12: {
				Clientes.registrarClienteVisi(conexion, reader);
				Mcaloriego(reader);
				break;
			}
			case 13: {
				Empleados.asignarEmpleado(conexion, reader);
				Mcaloriego(reader);
				break;

			}
			case 14: {
				Empleados.editarVisita(conexion, reader);
				Mcaloriego(reader);
				break;
			}
			case 15: {
				Clientes.editarCliente(conexion, reader);
				Mcaloriego(reader);
				break;
			}
			case 16: {
				
				DatosBD2(conexion);
				Mcaloriego(reader);
				break;
			}
			case 17: {
				seguir=true;
				menu();
				break;
			}
			default:
				throw new IllegalArgumentException("esta opción no sirve: " + eleccion);
			}
			
			
			

	}while(!seguir);
		} catch (ClassNotFoundException e1) {
			System.out.println("Error al intentar conectarse a la base de datos, asegurate de que esta todo correctamente");
		} catch (SQLException e1) {
			System.out.println("Error al intentar conectarse a la base de datos, asegurate de que esta todo correctamente");
		}
		}

	// menu PINKILO
	private static void Mpinkilo(BufferedReader reader)
			throws NumberFormatException, IOException, SQLException, ClassNotFoundException {
		boolean seguir=false;
		boolean correcto= false;
	

		// mysql
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/turismo", "root", "soylalecheN7");
		
		
		
		DatabaseMetaData dbmd = (DatabaseMetaData) conexion.getMetaData();

		do {
			System.out.println(
					"Bienvenido a la agencia de turismo PINKILO :\n1.-Ver datos de empresa \n2.-ver Empleados \n3.-ver Visitas guiadas \n4.-ver Clientes \n5.-Ver clientes en visitas guiadas \n6.-eliminar visita guiada \n7.-eliminar cliente \n8.-Despedir empleado"
							+ " \n9.-añadir nuevo cliente  \n10.-contratar empleado \n11.-nueva visita guiada \n12.-añadir cliente a una visita \n13.-asignar una visita a un empleado \n14.-editar visita guiada \n15.-editar cliente"
							+ "\n16.-información de la base de datos \n17.-volver al menú principal");
			int eleccion=0;
			do {
				try {
					eleccion = Integer.parseInt(reader.readLine());
					if(eleccion>0) {
						correcto=true;
					}
				} catch (NumberFormatException e) {
					System.out.println("escribe un valor numerico");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} while (!correcto);
			
			
			switch (eleccion) {
			case 1: {
				verEmpresa(conexion);
				Mpinkilo(reader);
				break;
			}
			case 2: {
				Empleados.verEmpleados(conexion);
				Mpinkilo(reader);
				break;
			}
			case 3: {
				Visitas.verVisitas(conexion);
				Mpinkilo(reader);
				break;
			}
			case 4: {
				Clientes.verClientes(conexion);
				Mpinkilo(reader);
				break;
			}
			case 5: {
				Visitas.clientesEnvisita(conexion, reader);
				Mpinkilo(reader);
				break;

			}
			case 6: {
				Visitas.eliminaVisita(conexion, reader);
				Mpinkilo(reader);
				break;
			}
			case 7: {
				Clientes.eliminarCliente(conexion, reader);
				Mpinkilo(reader);
				break;
			}
			case 8: {
				Empleados.eliminarEmpleado(conexion, reader);
				Mpinkilo(reader);
				break;
			}
			case 9: {
				Clientes.meterCliente(conexion, reader);
				Mpinkilo(reader);
				break;
			}
			case 10: {
				Empleados.meterEmpleado(conexion, reader);
				Mpinkilo(reader);
				break;
			}
			case 11: {
				Visitas.meterVisita(conexion, reader);
				Mpinkilo(reader);
				break;
			}
			case 12: {
				Clientes.registrarClienteVisi(conexion, reader);
				Mpinkilo(reader);
				break;
			}
			case 13: {
				Empleados.asignarEmpleado(conexion, reader);
				Mpinkilo(reader);
				break;

			}
			case 14: {
				Empleados.editarVisita(conexion, reader);
				Mpinkilo(reader);
				break;
			}
			case 15: {
				Clientes.editarCliente(conexion, reader);
				Mpinkilo(reader);
				break;
			}
			case 16: {
				DatosBD(conexion);
				Mpinkilo(reader);
				break;
			}
			case 17: {
				seguir=true;
				menu();
				break;
			}
			default:
				throw new IllegalArgumentException("esta opción no sirve: " + eleccion);
			}
		} while (!seguir);

		} catch (ClassNotFoundException e1) {
			System.out.println("Error al intentar conectarse a la base de datos, asegurate de que esta todo correctamente");
		} catch (SQLException e1) {
			System.out.println("Error al intentar conectarse a la base de datos, asegurate de que esta todo correctamente");
		} 
		
	

	}
	

	
//muestra los datos de la empresa, nombre,propietario...
	private static void verEmpresa(Connection conexion) {
		try {

			Statement sentencia = conexion.createStatement();
			ResultSet resul = ((java.sql.Statement) sentencia).executeQuery("SELECT * FROM empresa");

			// Recorremos el resultado para visualizar
			// Se hace un bucle mientras haya registros visualizando
			while (resul.next()) {
				System.out.println("Nombre: " + resul.getString(1) + " Apertura: " + resul.getString(2) + " Dirección: "
						+ resul.getString(3) + " Propietario: " + resul.getString(4));
			}

			resul.close();// Cerrar ResultSet
			sentencia.close();// Cerrar Statement

			System.out.println("Fin de lectura");

		} catch (SQLException e) {
			System.out.println("error conn la base de datos");

		}
	}
	

	// DATOS DE LA BASE DE DATOS
	public static void DatosBD(Connection conexion) throws SQLException {

		
		DatabaseMetaData dbmd = (DatabaseMetaData) conexion.getMetaData();

		ResultSet resul = null;
		String nombre = dbmd.getDatabaseProductName();
		String driver = dbmd.getDriverName();
		String url = dbmd.getURL();
		String usuario = dbmd.getUserName();

		System.out.println("INFORMACIN SOBRE LA BASE DE DATOS:" + nombre);
		System.out.println("Driver : " + driver);
		System.out.println("URL : " + url);
		System.out.println("Usuario: " + usuario);
		resul = dbmd.getTables(null, "ejemplo", null, null);

		System.out.println();// espacio

		while (resul.next()) {
			String catal = resul.getString(1);
			String esquema = resul.getString(2);
			String tabla = resul.getString(3);
			String tipo = resul.getString(4);
			String cinco = resul.getString(5);
			System.out.println(tipo + ", catalogo: " + catal + ", esquema: " + esquema + ", tabla: " + tabla);

		}

	}


	//para el H2 y SQLlite
	public static void DatosBD2(Connection conexion) throws SQLException {
		
		

		ResultSet resul = null;
		String nombre = conexion.getMetaData().getDatabaseProductName();
		String driver = conexion.getMetaData().getDriverName();
		String url = conexion.getMetaData().getURL();
		String usuario = conexion.getMetaData().getUserName();

		System.out.println("INFORMACIN SOBRE LA BASE DE DATOS:" + nombre);
		System.out.println("Driver : " + driver);
		System.out.println("URL : " + url);
		System.out.println("Usuario: " + usuario);
		resul = conexion.getMetaData().getTables(null, "ejemplo", null, null);

		System.out.println();// espacio

		while (resul.next()) {
			String catal = resul.getString(1);
			String esquema = resul.getString(2);
			String tabla = resul.getString(3);
			String tipo = resul.getString(4);
			String cinco = resul.getString(5);
			System.out.println(tipo + ", catalogo: " + catal + ", esquema: " + esquema + ", tabla: " + tabla);

		}

	}







}
