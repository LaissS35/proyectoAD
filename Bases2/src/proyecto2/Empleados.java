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
	
	public static void DNIemple(Connection conexion) {
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
	
	
	public static void eliminarEmpleado(Connection conexion, BufferedReader reader) throws IOException, SQLException {
		boolean seguir = false;
		
		DNIemple(conexion);//muestra dni y nombre del empleado
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
	
	
	public static void meterEmpleado(Connection conexion, BufferedReader reader) throws IOException {
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
		DNIemple(conexion);//muestra dni de los empleados
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

		Visitas.Codigovisita(conexion);// mostrar codigo y nombre de la visita

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
			String query = "UPDATE Empleados SET `Vcargo` = '" + codigo + "' WHERE (`DNI`='" + DNI + "')";
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

			resul.close();// Cerrar ResultSet
			sentencia.close();// Cerrar Statement

		} catch (SQLException e) {
			System.out.println("error conn la base de datos");
		}

		Visitas.Codigovisita(conexion);// mostrar codigo y nombre de la visita DEBE MOSTRAR TODO

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

}
