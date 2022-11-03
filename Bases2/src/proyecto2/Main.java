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

		menu();

	}

	// DATOS DE LA BASE DE DATOS
	public static void DatosBD(Connection conexion, DatabaseMetaData dbmd) throws SQLException {

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
			String catal = resul.getString(1);// columna1
			String esquema = resul.getString(2);
			String tabla = resul.getString(3);
			String tipo = resul.getString(4);
			String cinco = resul.getString(5);
			System.out.println(tipo + ", catalogo: " + catal + ", esquema: " + esquema + ", tabla: " + tabla);

		}

	}

	private static void menu() throws NumberFormatException, IOException, ClassNotFoundException, SQLException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Bienvenido!¿que agencia deseas consultar?:" + "1.-Agencia Pinkilo (H2)"
				+ "2.-Agencia Avian(Mysql)" + "3.-Agencia Caloriego(SQLlite)");

		int opcion = Integer.parseInt(reader.readLine());// coger un valor integer

		if (opcion == 1) {
			Mpinkilo(reader);

		}
		if (opcion == 2) {

		}
		if (opcion == 3) {
			Mcaloriego(reader);

		}

	}

	private static void Mcaloriego(BufferedReader reader)
			throws ClassNotFoundException, SQLException, NumberFormatException, IOException {
		// SQLlite
		// Driver
		Class.forName("org.sqlite.JDBC");
		// Establecemos la conexion con la BD
		Connection conexion = DriverManager.getConnection("jdbc:sqlite:/Users/lais/Desktop/Turismo.db");

		System.out.println("Bienvenido a la agencia de turismo PINKILO :" + " 1.-Ver datos de empresa"
				+ " 2.-ver Empleados" + " 3.-ver Visitas guiadas" + " 4.-ver Clientes" + " 5.-despedir Empleado"
				+ " 6.-Desactivar visita guiada" + " 7.-Eliminar cliente" + " 8.-volver al inicio"
				+ "  9.-Eliminar visita guiada" + "  10.-Añadir nuevo cliente" + " 11.-Añadir nueva visita guiada"
				+ " 12.-Contratar nuevo empleado" + " 13.-Editar visita guiada"
				+ "14.-Asignar nueva visita guiada a Empleado" + "  15.-Editar cliente");
		int eleccion = Integer.parseInt(reader.readLine());

	}

	// PINKILO

	private static void Mpinkilo(BufferedReader reader)
			throws NumberFormatException, IOException, SQLException, ClassNotFoundException {
		boolean seguir=false;
		// H2

		// Cargar el driver mysql
		Class.forName("com.mysql.cj.jdbc.Driver");

		// Establecemos la conexion con la BD

		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/turismo", "root", "soylalecheN7"); // mysql

		DatabaseMetaData dbmd = (DatabaseMetaData) conexion.getMetaData();// Creamos objeto DatabaseMetaData H2

		do {
			System.out.println(
					"Bienvenido a la agencia de turismo PINKILO :\n1.-Ver datos de empresa \n2.-ver Empleados \n3.-ver Visitas guiadas \n4.-ver Clientes \n5.-Ver clientes en visitas guiadas \n6.-eliminar visita guiada \n7.-eliminar cliente \n8.-Despedir empleado"
							+ " \n 9.-añadir nuevo cliente  \n10.-contratar empleado \n11.-nueva visita guiada \n12.-añadir cliente a una visita \n13.-asignar una visita a un empleado \n14.-editar visita guiada \n15.-editar cliente"
							+ "\n16.-información de la base de datos \n17.-volver al menú principal");
			int eleccion = Integer.parseInt(reader.readLine());
			switch (eleccion) {
			case 1: {
				verEmpresa(conexion);
				Mpinkilo(reader);
				break;
			}
			case 2: {
				verEmpleados(conexion);
				Mpinkilo(reader);
				break;
			}
			case 3: {
				verVisitas(conexion);
				Mpinkilo(reader);
				break;
			}
			case 4: {
				verClientes(conexion);
				Mpinkilo(reader);
				break;
			}
			case 5: {
				clientesEnvisita(conexion, reader);
				Mpinkilo(reader);
				break;

			}
			case 6: {
				eliminaVisita(conexion, reader);
				Mpinkilo(reader);
				break;
			}
			case 7: {
				eliminarCliente(conexion, reader);
				Mpinkilo(reader);
				break;
			}
			case 8: {
				eliminarEmpleado(conexion, reader);
				Mpinkilo(reader);
				break;
			}
			case 9: {
				meterCliente(conexion, reader);
				Mpinkilo(reader);
				break;
			}
			case 10: {
				meterEmpleado(conexion, reader);
				Mpinkilo(reader);
				break;
			}
			case 11: {
				meterVisita(conexion, reader);
				Mpinkilo(reader);
				break;
			}
			case 12: {
				registrarClienteVisi(conexion, reader);
				Mpinkilo(reader);
				break;
			}
			case 13: {
				asignarEmpleado(conexion, reader);
				Mpinkilo(reader);
				break;

			}
			case 14: {
				editarVisita(conexion, reader);
				Mpinkilo(reader);
				break;
			}
			case 15: {
				editarCliente(conexion, reader);
				Mpinkilo(reader);
				break;
			}
			case 16: {
				DatosBD(conexion, dbmd);
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


	

	}
	
	

	private static void clientesEnvisita(Connection conexion, BufferedReader reader) throws NumberFormatException, IOException {
		boolean seguir = false;
		int i = 0;
		int[] codigos = new int[100];

		// crear array con todos los codigos y comparara con el resultado metido

		try {

			Statement sentencia = conexion.createStatement();
			ResultSet resul = ((java.sql.Statement) sentencia).executeQuery("SELECT Codigo FROM visitas");

			// coger codigos de las visitas existentes
			while (resul.next()) {
				codigos[i] = resul.getInt(1);
				i++;
			}

			resul.close();// Cerrar ResultSet
			sentencia.close();// Cerrar Statement

		} catch (SQLException e) {
			System.out.println("error conn la base de datos");
		}

		Codigovisita(conexion);// mostrar codigo y nombre de la visita

		int codigo;
		// verificar que el usuario mete un codigo existente

		do {
			//
			System.out.println("Escribe el codigo de la visita que quieres consultar");
			codigo = Integer.parseInt(reader.readLine());
			for (int x = 0; x < codigos.length; x++) {
				if (codigo == codigos[x]) {
					seguir = true;
					break;// salir del bucle
				}
			}
		} while (!seguir);

		try {

			Statement sentencia = conexion.createStatement();
			ResultSet resul = ((java.sql.Statement) sentencia)
					.executeQuery("SELECT * FROM VisitaC WHERE `Cvisita`='" + codigo + "'");

			while (resul.next()) {
				System.out.println("Codigo de la visita: " + resul.getInt(1) + " Horario de la visita: "
						+ resul.getString(2) + " DNI del cliente que va: " + resul.getString(3));
			}

			resul.close();// Cerrar ResultSet
			sentencia.close();// Cerrar Statement

			System.out.println("Fin de lectura");

		} catch (SQLException e) {
			System.out.println("error conn la base de datos");
		}
		
	}

	private static void registrarClienteVisi(Connection conexion, BufferedReader reader) {
		boolean seguir = false;
		int i = 0;
		int[] codigos = new int[100];
		// Registrar cliente en visita

		// crear array con todos los codigos y comparara con el resultado metido

		try {

			Statement sentencia = conexion.createStatement();
			ResultSet resul = ((java.sql.Statement) sentencia).executeQuery("SELECT Codigo FROM visitas");

			// coger codigos de las visitas existentes
			while (resul.next()) {
				codigos[i] = resul.getInt(1);
				i++;
			}

			resul.close();// Cerrar ResultSet
			sentencia.close();// Cerrar Statement

		} catch (SQLException e) {
			System.out.println("error conn la base de datos");
		}

		Codigovisita(conexion);// mostrar codigo y nombre de la visita

		int codigo;
		// verificar que el usuario mete un codigo existente

		do {
			//
			System.out.println("Escribe el codigo de visita en la que quieres meter un cliente");
			codigo = Integer.parseInt(reader.readLine());
			for (int x = 0; x < codigos.length; x++) {
				if (codigo == codigos[x]) {
					seguir = true;
					break;// salir del bucle
				}
			}
		} while (!seguir);// AQUI

		DNIcliente(conexion);// mostrar dni y nombre cliente

		boolean ar = false;
		// asegurar que el dni mide 9 caracteres

		String DNI;
		do {
			System.out.println("Escribe el DNI del cliente");
			DNI = reader.readLine();

			if (DNI.length() == 9) {

				break;
			} else {
				System.out.println("este dni no sirve");
			}

		} while (!ar);

		System.out.print("Escribe a que hora empezara");
		String hora = reader.readLine();

		try {

			String query = "INSERT INTO VisitaC(`Cvisita`,`Horario`, `DNIc`) VALUES('" + codigo + "','" + hora
					+ "','" + DNI + "') ";
			PreparedStatement preparedStmt = conexion.prepareStatement(query);

			preparedStmt.executeUpdate();

			preparedStmt.close();

		} catch (SQLException e) {
			System.out.println("Error insertado el nuevo cliente");

		}

		
	}

	private static void asignarEmpleado(Connection conexion, BufferedReader reader)
			throws NumberFormatException, IOException {
		boolean ar = false;
		DNIemple(conexion);
//asegurar que el dni mide 9 caracteres

		String DNI;
		do {
			System.out.println(
					"Escribe el DNI del empleado al que quieres asignar una nueva visita guiada, recuerda, un empleado solo puede estar a cargo de una visita guiada");
			DNI = reader.readLine();

			if (DNI.length() == 9) {

				break;
			} else {
				System.out.println("este dni no sirve");
			}

		} while (!ar);

		boolean seguir = false;
		int i = 0;
		int[] codigos = new int[100];

		// crear array con todos los codigos y comparara con el resultado metido

		try {

			Statement sentencia = conexion.createStatement();
			ResultSet resul = ((java.sql.Statement) sentencia).executeQuery("SELECT Codigo FROM visitas");

			// coger codigos de las visitas existentes
			while (resul.next()) {
				codigos[i] = resul.getInt(1);
				i++;
			}

			resul.close();// Cerrar ResultSet
			sentencia.close();// Cerrar Statement

		} catch (SQLException e) {
			System.out.println("error conn la base de datos");
		}

		Codigovisita(conexion);// mostrar codigo y nombre de la visita

		int codigo;
		// verificar que el usuario mete un codigo existente

		do {
			//
			System.out.println("Escribe el codigo de la visita que quieres consultar");
			codigo = Integer.parseInt(reader.readLine());
			for (int x = 0; x < codigos.length; x++) {
				if (codigo == codigos[x]) {
					seguir = true;
					break;// salir del bucle
				}
			}
		} while (!seguir);

		try {
			String query = "UPDATE Empleados SET `V.cargo` = '" + codigo + "' WHERE (`DNI`='" + DNI + "')";
			PreparedStatement preparedStmt = conexion.prepareStatement(query);

			preparedStmt.executeUpdate();
			preparedStmt.close();
		} catch (SQLException e) {
			System.out.println("error asignando una visita al empleado");
		}

	}

	private static void editarVisita(Connection conexion, BufferedReader reader) throws IOException {
		boolean seguir = false;
		int i = 0;
		int[] codigos = new int[100];

		// crear array con todos los codigos y comparara con el resultado metido

		try {

			Statement sentencia = conexion.createStatement();
			ResultSet resul = ((java.sql.Statement) sentencia).executeQuery("SELECT Codigo FROM visitas");

			// coger codigos de las visitas existentes
			while (resul.next()) {
				codigos[i] = resul.getInt(1);
				i++;
			}

			resul.close();// Cerrar ResultSet
			sentencia.close();// Cerrar Statement

		} catch (SQLException e) {
			System.out.println("error conn la base de datos");
		}

		Codigovisita(conexion);// mostrar codigo y nombre de la visita DEBE MOSTRAR TODO

		int codigo;
		// verificar que el usuario mete un codigo existente

		do {
			//
			System.out.println("Escribe el codigo de la visita que quieres editar");
			codigo = Integer.parseInt(reader.readLine());
			for (int x = 0; x < codigos.length; x++) {
				if (codigo == codigos[x]) {
					seguir = true;
					break;// salir del bucle
				}
			}
		} while (!seguir);

		seguir = false;// PARA REUTILIZARLO

		String descripcion;
		do {
			System.out.println("escribe su nueva descripción");
			descripcion = reader.readLine();
			if (descripcion.length() < 4) {
				System.out.println("mete minimo 4 caracteres");
			} else {
				seguir = true;
			}
		} while (!seguir);

		seguir = false;

		double coste = 0;
		do {
			System.out.println("nuevos coste");
			try {
				coste = Double.parseDouble(reader.readLine());
			} catch (NumberFormatException e) {
				System.out.println("mete un valor númerico");
			} catch (IOException e) {

				e.printStackTrace();
			}
			if (coste < 50) {
				System.out.println("minimo 50");
			} else {
				seguir = true;
			}
		} while (!seguir);

		seguir = false;

		int numeroM = 0;
		do {
			System.out.println("número maximo de clientes");
			try {
				numeroM = Integer.parseInt(reader.readLine());
			} catch (NumberFormatException e) {
				System.out.println("mete un valor númerico");
			} catch (IOException e) {

				e.printStackTrace();
			}
			if (numeroM < 5) {
				System.out.println("minimo 5 clientes deben poder ir");
			} else {
				seguir = true;
			}
		} while (!seguir);

		seguir = false;

		String lugarP;
		do {
			System.out.println("nuevo lugar de partida");
			lugarP = reader.readLine();
			if (lugarP.length() < 4) {
				System.out.println("mete minimo 4 caracteres");
			} else {
				seguir = true;
			}
		} while (!seguir);

		seguir = false;

		String ubicacion;
		do {
			System.out.println("Ubicacion visita");
			ubicacion = reader.readLine();
			if (ubicacion.length() < 4) {
				System.out.println("mete minimo 4 caracteres");
			} else {
				seguir = true;
			}
		} while (!seguir);

		// CONTROLAR ESTADO, NUMERO DE CARACTERES EN CADA STRING

		try {
			String query = "UPDATE visitas SET `Tematica`='" + descripcion + "',`ClientesM` = '" + numeroM
					+ "',`Coste`='" + coste + "',`LugarP`='" + lugarP + "',`Ubicacion`='" + ubicacion
					+ "' WHERE (`Codigo`='" + codigo + "')";
			PreparedStatement preparedStmt = conexion.prepareStatement(query);

			preparedStmt.executeUpdate();
			preparedStmt.close();

			System.out.print("Fin del proceso");
		} catch (SQLException e) {
			System.out.println("error editando la visita");
		}

	}

	private static void editarCliente(Connection conexion, BufferedReader reader) throws IOException {
		DNIcliente(conexion);// mostrar dni y nombre cliente

		boolean ar = false;
		// asegurar que el dni mide 9 caracteres

		String DNI;
		do {
			System.out.println("Escribe el DNI del cliente al que quieras cambiar la profesion");
			DNI = reader.readLine();

			if (DNI.length() == 9) {

				break;
			} else {
				System.out.println("este dni no sirve");
			}

		} while (!ar);

		System.out.println("Escribe la nueva profesión");
		String profesion = reader.readLine();

		try {
			String query = "UPDATE clientes SET `Profesion` = '" + profesion + "' WHERE (`DNI`='" + DNI + "')";
			PreparedStatement preparedStmt = conexion.prepareStatement(query);

			preparedStmt.executeUpdate();
			preparedStmt.close();

			System.out.println("Cliente editado, con exito");
		} catch (SQLException e) {
			System.out.println("error editando el cliente");
		}

	}

	private static void eliminaVisita(Connection conexion, BufferedReader reader)
			throws NumberFormatException, IOException {
		boolean seguir = false;
		int i = 0;
		int[] codigos = new int[100];

		// crear array con todos los codigos y comparara con el resultado metido

		try {

			Statement sentencia = conexion.createStatement();
			ResultSet resul = ((java.sql.Statement) sentencia).executeQuery("SELECT Codigo FROM visitas");

			// coger codigos de las visitas existentes
			while (resul.next()) {
				codigos[i] = resul.getInt(1);
				i++;
			}

			resul.close();// Cerrar ResultSet
			sentencia.close();// Cerrar Statement

		} catch (SQLException e) {
			System.out.println("error conn la base de datos");
		}

		Codigovisita(conexion);// mostrar codigo y nombre de la visita DEBE MOSTRAR TODO

		int codigo;
		// verificar que el usuario mete un codigo existente

		do {
			//
			System.out.println("Escribe el codigo de la visita que quieres eliminar");
			codigo = Integer.parseInt(reader.readLine());
			for (int x = 0; x < codigos.length; x++) {
				if (codigo == codigos[x]) {
					seguir = true;
					break;// salir del bucle
				}
			}
		} while (!seguir);

		try {
			String query = "DELETE FROM visitas WHERE `Codigo`='" + codigo + "'";
			PreparedStatement preparedStmt = conexion.prepareStatement(query);

			preparedStmt.executeUpdate();
			preparedStmt.close();

			System.out.println("Visita eliminada");
		} catch (SQLException e) {
			System.out.println("error eliminando la visita");
		}

	}

	private static void meterVisita(Connection conexion, BufferedReader reader) throws IOException {
		boolean seguir = false;

		String nombre;
		do {

			System.out.println("Escribe el nombre de la nueva visita:");
			nombre = reader.readLine();
			if (nombre.length() < 4) {
				System.out.println("minimo 4 caracteres");
			} else {
				seguir = true;
			}
		} while (!seguir);

		seguir = false;

		String visita;
		do {
			System.out.println("Describe la visita");
			visita = reader.readLine();
			if (visita.length() < 4) {
				System.out.println("minimo 4 caracteres");
			} else {
				seguir = true;
			}

		} while (!seguir);

		seguir = false;

		int Max;
		do {
			System.out.println("Número de clientes maximo");
			Max = Integer.parseInt(reader.readLine());

			if (Max < 3) {
				System.out.println("minimo 3 clientes");
			} else {
				seguir = true;
			}
		} while (!seguir);

		seguir = false;

		double coste;
		do {
			System.out.println("Coste de la visita");
			coste = Double.parseDouble(reader.readLine());
			if (coste < 100) {
				System.out.println("minimo un coste de 100");
			} else {
				seguir = true;
			}
		} while (!seguir);

		seguir = false;

		String Lugarpartida;
		do {
			System.out.println("Lugar de partido");
			Lugarpartida = reader.readLine();
			if (Lugarpartida.length() < 4) {
				System.out.println("minimo 4 caracteres");
			} else {
				seguir = true;
			}
		} while (!seguir);

		seguir = false;

		String ubicacion;
		do {
			System.out.println("Ubicacion de la visita");
			ubicacion = reader.readLine();
			if (ubicacion.length() < 4) {
				System.out.println("minimo 4 caracteres");
			} else {
				seguir = true;
			}
		} while (!seguir);

		try {

			String query = "INSERT INTO visitas(`Nombre`, `Tematica`, `ClientesM`, `Coste`,`LugarP`,`Ubicacion`,`Estado`) VALUES('"
					+ nombre + "','" + visita + "','" + Max + "','" + coste + "','" + Lugarpartida + "','" + ubicacion
					+ "') ";
			PreparedStatement preparedStmt = conexion.prepareStatement(query);

			preparedStmt.executeUpdate();

			preparedStmt.close();

		} catch (SQLException e) {
			System.out.println("Error insertado la nueva visita guiada");

		}

	}

	private static void meterCliente(Connection conexion, BufferedReader reader) throws IOException {
		boolean seguir = false;
		String DNI;
		do {
			System.out.print("Escribe el DNI del nuevo empleado");
			DNI = reader.readLine();
			if (DNI.length() == 9) {
				seguir = true;
			} else {
				System.out.println("escribe bien el dni");
			}
		} while (!seguir);

		seguir = false;

		String nombre;
		do {
			System.out.print("Escribe el nombre del nuevo empleado:");
			nombre = reader.readLine();
			if (nombre.length() < 4) {
				System.out.println("minimo 4 caracteres");
			} else {
				seguir = true;
			}
		} while (!seguir);

		seguir = false;

		String apellido;
		do {
			System.out.print("Escribe su apellido");
			apellido = reader.readLine();
			if (apellido.length() < 4) {
				System.out.println("minimo 4 caracteres");
			} else {
				seguir = true;
			}
		} while (!seguir);

		seguir = false;

		int Ndepa;
		do {
			System.out.print("Edad");
			Ndepa = Integer.parseInt(reader.readLine());
			if (Ndepa < 18) {
				System.out.println("minimo debes tener 18 años");
			} else {
				seguir = true;
			}
		} while (!seguir);

		seguir = false;

		String profesion;
		do {
			System.out.println("Profesión");
			profesion = reader.readLine();
			if (profesion.length() < 4) {
				System.out.println("minimo 4 caracteres");
			} else {
				seguir = true;
			}
		} while (!seguir);

		try {

			String query = "INSERT INTO clientes(`DNI`,`Nombre`, `Apellido`, `Edad`, `Profesion`) VALUES('" + DNI
					+ "','" + nombre + "','" + apellido + "','" + Ndepa + "','" + profesion + "') ";
			PreparedStatement preparedStmt = conexion.prepareStatement(query);

			preparedStmt.executeUpdate();

			preparedStmt.close();

		} catch (SQLException e) {
			System.out.println("Error insertado el nuevo cliente");

		}

	}

	private static void meterEmpleado(Connection conexion, BufferedReader reader) throws IOException {
		boolean seguir = false;
		String DNI;
		do {
			System.out.print("Escribe el DNI del nuevo empleado");
			DNI = reader.readLine();
			if (DNI.length() == 9) {
				seguir = true;
			} else {
				System.out.println("este dni no sirve");
			}
		} while (!seguir);

		seguir = false;

		String nombre;
		do {
			System.out.print("Escribe el nombre del nuevo empleado:");
			nombre = reader.readLine();
			if (nombre.length() < 4) {
				System.out.println("minimo 4 caracteres");
			} else {
				seguir = true;
			}
		} while (!seguir);

		seguir = false;

		String apellido;
		do {
			System.out.print("Escribe su apellido");
			apellido = reader.readLine();
			if (apellido.length() < 4) {
				System.out.println("minimo 4 caracteres");
			} else {
				seguir = true;
			}
		} while (!seguir);

		seguir = false;

		String oficio;
		do {
			System.out.print("Fecha de nacimiento (xx/xx/xxxx)<-ejemplo");
			oficio = reader.readLine();
			if (oficio.length() < 10) {
				System.out.println("minimo 10 caracteres");
			} else {
				seguir = true;
			}
		} while (!seguir);

		seguir = false;

		String Falta;
		do {
			System.out.print("Fecha alta");
			Falta = reader.readLine();

			if (Falta.length() < 10) {
				System.out.println("minimo 10 caracteres");
			} else {
				seguir = true;
			}
		} while (!seguir);

		seguir = false;

		String nacionalidad;
		do {
			System.out.print("Nacionalidad");
			nacionalidad = reader.readLine();
			if (nacionalidad.length() < 4) {
				System.out.println("minimo 4 caracteres");
			} else {
				seguir = true;
			}

		} while (!seguir);

		seguir = false;

		String cargo;
		do {
			System.out.println("Oficio");
			cargo = reader.readLine();
			if (cargo.length() < 4) {
				System.out.println("minimo 4 caracteres");
			} else {
				seguir = true;
			}
		} while (!seguir);

		try {

			String query = "INSERT INTO Empleados(`DNI`,`Nombre`, `Apellido`, `F.nacimiento`, `F.alta`,`Nacionalidad`,`Cargo`) VALUES('"
					+ DNI + "','" + nombre + "','" + apellido + "','" + oficio + "','" + Falta + "','" + nacionalidad
					+ "','" + cargo + "') ";
			PreparedStatement preparedStmt = conexion.prepareStatement(query);

			preparedStmt.executeUpdate();

			preparedStmt.close();

			System.out.print("nuevo empleado añadido");

		} catch (SQLException e) {
			System.out.println("Error contratando al nuevo empleado");
			e.printStackTrace();

		}

	}

	private static void eliminarCliente(Connection conexion, BufferedReader reader) throws IOException {
		boolean seguir = false;
		// eliminar cliente

		String empleado;
		do {
			// despedir empleado por DNI
			System.out.print("Escribe el DNI del cliente que quieras echar");// AQUI
			empleado = reader.readLine();
			if (empleado.length() == 9) {
				seguir = true;
			} else {
				System.out.println("este dni no sirve");
			}
		} while (!seguir);

		try {
			String query = "DELETE FROM empleados WHERE `DNI`='" + empleado + "'";
			PreparedStatement preparedStmt = conexion.prepareStatement(query);// con este metodo ya que estamos haciendo
																				// un cambio en la base

			preparedStmt.executeUpdate();
			preparedStmt.close();

			System.out.println("empleado despedido");
		} catch (SQLException e) {
			System.out.println("error con la base de datos");
		}
	}

	private static void eliminarEmpleado(Connection conexion, BufferedReader reader) throws IOException, SQLException {
		boolean seguir = false;
		String empleado;
		do {
			// funciona elimina linea seleccionada
			// despedir empleado por DNI
			System.out.print("Escribe el DNI del empleado que quieres despedir");
			empleado = reader.readLine();
			if (empleado.length() == 9) {
				seguir = true;
			} else {
				System.out.println("este dni no sirve");
			}

		} while (!seguir);

		String query = "DELETE FROM empleados WHERE `DNI`='" + empleado + "'";
		PreparedStatement preparedStmt = conexion.prepareStatement(query);// con este metodo ya que estamos haciendo
																			// un cambio en la base

		preparedStmt.executeUpdate();
		preparedStmt.close();

		System.out.println("empleado despedido");

	}

	private static void verClientes(Connection conexion) {
		try {

			Statement sentencia = conexion.createStatement();
			ResultSet resul = ((java.sql.Statement) sentencia).executeQuery("SELECT * FROM clientes");

			// Recorremos el resultado para visualizar
			// Se hace un bucle mientras haya registros visualizando
			while (resul.next()) {
				System.out.println("DNI: " + resul.getString(1) + " Nombre: " + resul.getString(2) + " Apellido: "
						+ resul.getString(3) + " Edad: " + resul.getInt(4) + " Profesión: " + resul.getString(5));
			}

			resul.close();// Cerrar ResultSet
			sentencia.close();// Cerrar Statement

			System.out.println("Fin de lectura");

		} catch (SQLException e) {
			System.out.println("error conn la base de datos");

		}

	}

	private static void verVisitas(Connection conexion) {
		try {

			Statement sentencia = conexion.createStatement();
			ResultSet resul = ((java.sql.Statement) sentencia).executeQuery("SELECT * FROM visitas");

			// Recorremos el resultado para visualizar
			// Se hace un bucle mientras haya registros visualizando
			while (resul.next()) {
				System.out.println("Codigo: " + resul.getInt(1) + " Nombre: " + resul.getString(2) + " Tematica: "
						+ resul.getString(3) + " Número de clientes permitidos: " + resul.getInt(4) + " Coste: "
						+ resul.getDouble(5) + " Lugar de Partida: " + resul.getString(6) + " Ubicación: "
						+ resul.getString(7));
			}

			resul.close();// Cerrar ResultSet
			sentencia.close();// Cerrar Statement

			System.out.println("Fin de lectura");

		} catch (SQLException e) {
			System.out.println("error conn la base de datos");

		}

	}

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

	private static void verEmpleados(Connection conexion) {
		try {

			Statement sentencia = conexion.createStatement();

			ResultSet resul = ((java.sql.Statement) sentencia).executeQuery("select * from empleados");

			// Recorremos el resultado para visualizar
			// Se hace un bucle mientras haya registros visualizando
			while (resul.next()) {
				System.out.println("DNI: " + resul.getString(1) + " Nombre: " + resul.getString(2) + " Apellido: "
						+ resul.getString(3) + " Fecha de Nacimiento: " + resul.getString(4) + " Fecha Alta: "
						+ resul.getString(5) + " Nacionalidad: " + resul.getString(6) + " Cargos: " + resul.getString(7)
						+ " ESta acagor de la visita con codigo: " + resul.getInt(8));
			}

			resul.close();// Cerrar ResultSet
			sentencia.close();// Cerrar Statement

			System.out.println("Fin de lectura");

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	private static void DNIemple(Connection conexion) {
		try {

			Statement sentencia = conexion.createStatement();
			ResultSet resul = ((java.sql.Statement) sentencia).executeQuery("SELECT * FROM Empleados");

			while (resul.next()) {
				System.out.println("DNI empleado: " + resul.getString(1) + " Nombre: " + resul.getString(2));
			}

			resul.close();// Cerrar ResultSet
			sentencia.close();// Cerrar Statement

		} catch (SQLException e) {
			System.out.println("error conn la base de datos");
		}

	}

	private static void Codigovisita(Connection conexion) {
		try {

			Statement sentencia = conexion.createStatement();
			ResultSet resul = ((java.sql.Statement) sentencia).executeQuery("SELECT * FROM visitas");

			while (resul.next()) {
				System.out.println("Visita con codigo: " + resul.getInt(1) + " Nombre: " + resul.getString(2));
			}

			resul.close();// Cerrar ResultSet
			sentencia.close();// Cerrar Statement

		} catch (SQLException e) {
			System.out.println("error conn la base de datos");
		}

	}

	private static void DNIcliente(Connection conexion) {

		try {

			Statement sentencia = conexion.createStatement();
			ResultSet resul = ((java.sql.Statement) sentencia).executeQuery("SELECT * FROM clientes");

			while (resul.next()) {
				System.out.println("DNI cliente : " + resul.getString(1) + " Nombre: " + resul.getString(2));
			}

			resul.close();// Cerrar ResultSet
			sentencia.close();// Cerrar Statement

		} catch (SQLException e) {
			System.out.println("error conn la base de datos");
		}

	}

}
