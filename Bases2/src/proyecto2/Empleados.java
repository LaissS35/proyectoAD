package proyecto2;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Empleados {

	public static void verEmpleados(Connection conexion) {

		try {

			Statement sentencia = conexion.createStatement();

			ResultSet resul = ((java.sql.Statement) sentencia).executeQuery("select * from empleados");

			while (resul.next()) {
				System.out.println("DNI: " + resul.getString(1) + " Nombre: " + resul.getString(2) + " Apellido: "
						+ resul.getString(3) + " Fecha de Nacimiento: " + resul.getString(4) + " Fecha Alta: "
						+ resul.getString(5) + " Nacionalidad: " + resul.getString(6) + " Cargos: " + resul.getString(7)
						+ " Esta acagor de la visita con codigo: " + resul.getInt(8));
			}

			resul.close();
			sentencia.close();

			System.out.println("Fin de lectura");

		} catch (SQLException e) {
			System.out.println("Error mostrando los empleados");

		}

	}

	public static void DNIemple(Connection conexion) {
		try {

			// muestra el dni y nombre del empleado

			Statement sentencia = conexion.createStatement();
			ResultSet resul = ((java.sql.Statement) sentencia).executeQuery("SELECT * FROM Empleados");

			while (resul.next()) {
				System.out.println("DNI empleado: " + resul.getString(1) + " Nombre: " + resul.getString(2));
			}

			resul.close();
			sentencia.close();

		} catch (SQLException e) {
			System.out.println("error mostrando el DNI de los empleados");
		}

	}

	public static void eliminarEmpleado(Connection conexion, BufferedReader reader) throws IOException {
		boolean seguir = false;

		DNIemple(conexion);// muestra dni y nombre del empleado
		String empleado;
		do {

			// despedir empleado por DNI

			System.out.print("Escribe el DNI del empleado que quieres despedir");
			empleado = reader.readLine();

			if (empleado.length() == 9) {// ver que el tamaño del dni sea correcto
				seguir = true;
			} else {
				System.out.println("este dni no sirve");
			}

		} while (!seguir);// si no se mete bien se repetira

		try {
			String query = "DELETE FROM empleados WHERE `DNI`='" + empleado + "'";
			PreparedStatement preparedStmt = conexion.prepareStatement(query);// con este metodo ya que estamos haciendo
																				// un cambio en la base

			preparedStmt.executeUpdate();
			preparedStmt.close();

			System.out.println("empleado despedido");
		} catch (SQLException e) {
			System.out.println("Error eliminando el empleado");
		}

	}

	public static void meterEmpleado(Connection conexion, BufferedReader reader) throws IOException {
		boolean seguir = false;
		String DNI;
		// se repetira cada dato del cliente a rellenar si no se cumpe los requisitos de
		// caracter minimo
		// BUCLE DNI
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
		//BUCLE NOMBRE
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
		//BUCLE APELLIDO
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
			//BUCLE FECHA DE NACIMIENTO
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
		//BUCLE FECHA DE ALTA
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
		//BUCLE NACIONALIDAD
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
		//BUCLE OFICIO
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
			//nuevo empleado
			String query = "INSERT INTO Empleados(`DNI`,`Nombre`, `Apellido`, `Fnacimiento`, `Falta`,`Nacionalidad`,`Cargo`) VALUES('"
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

	public static void asignarEmpleado(Connection conexion, BufferedReader reader)
			throws NumberFormatException, IOException {
		boolean ar = false;
		DNIemple(conexion);// muestra dni de los empleados

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

			resul.close();
			sentencia.close();

		} catch (SQLException e) {
			System.out.println("error conn la base de datos");
		}

		Visitas.Codigovisita(conexion);// mostrar codigo y nombre de la visita

		int codigo;
		// verificar que el usuario mete un codigo existente

		do {
			//
			System.out.println("Escribe el codigo de la visita que quieres consultar");
			try {
				codigo = Integer.parseInt(reader.readLine());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int x = 0; x < codigos.length; x++) {
				if (codigo == codigos[x]) {
					seguir = true;
					break;// salir del bucle
				}
			}
		} while (!seguir);

		try {
			String query = "UPDATE Empleados SET `Vcargo` = '" + codigo + "' WHERE (`DNI`='" + DNI + "')";// se edita al
																											// empleado
																											// metiendo
																											// el numero
																											// de la
																											// visita
																											// que tiene
																											// asignada
			PreparedStatement preparedStmt = conexion.prepareStatement(query);

			preparedStmt.executeUpdate();
			preparedStmt.close();
		} catch (SQLException e) {
			System.out.println("error asignando una visita al empleado");
		}

	}

	public static void editarVisita(Connection conexion, BufferedReader reader) throws IOException {
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

			resul.close();
			sentencia.close();

		} catch (SQLException e) {
			System.out.println("error mostrando los codigos de las visitas");
		}

		Visitas.Codigovisita(conexion);// mostrar codigo y nombre de la visita DEBE MOSTRAR TODO

		int codigo=-1;
		// verificar que el usuario mete un codigo existente

		do {
			//
			System.out.println("Escribe el codigo de la visita que quieres editar");
			try {
				codigo = Integer.parseInt(reader.readLine());
			} catch (NumberFormatException e) {
				System.out.println("Escribe un valor númerico");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
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
		//BUCLE NUEVO COSTE
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
		//BUCLE NUMERO DE CLIENTES MAXIMO
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
		// BUCLE PUNTO DE PARTIDA

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
		//BUCLE UBICACION
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

		try {
			String query = "UPDATE visitas SET `Tematica`='" + descripcion + "',`ClientesM` = '" + numeroM
					+ "',`Coste`='" + coste + "',`LugarP`='" + lugarP + "',`Ubicacion`='" + ubicacion
					+ "' WHERE (`Codigo`='" + codigo + "')";// edicion de la visita
			PreparedStatement preparedStmt = conexion.prepareStatement(query);

			preparedStmt.executeUpdate();
			preparedStmt.close();

			System.out.print("Fin del proceso");
		} catch (SQLException e) {
			System.out.println("error editando la visita");
		}

	}

}
