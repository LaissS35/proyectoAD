package proyecto2;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Visitas {
	
	public static void clientesEnvisita(Connection conexion, BufferedReader reader) throws NumberFormatException, IOException {
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
			System.out.println("error con el codigo de las visitas");
		}

		Codigovisita(conexion);// mostrar codigo y nombre de la visita

		int codigo=-1;
		// verificar que el usuario mete un codigo existente

		do {
			//
			try {
				System.out.println("Escribe el codigo de la visita que quieres consultar");
				codigo = Integer.parseInt(reader.readLine());
			} catch (NumberFormatException e) {
				System.out.println("Mete un valor númerico");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			for (int x = 0; x < codigos.length; x++) {
				if (codigo == codigos[x]) {
					seguir = true;
					break;
				}
			}
		} while (!seguir);
		
		//MOSTRAR CLIENTES EN VISITAS

		try {

			Statement sentencia = conexion.createStatement();
			ResultSet resul = ((java.sql.Statement) sentencia)
					.executeQuery("SELECT * FROM VisitaC WHERE `Cvisita`='" + codigo + "'"); //ver clientes registrados en una visita

			while (resul.next()) {
				System.out.println("Codigo de la visita: " + resul.getInt(1) + " fecha y horario de la visita: "
						+ resul.getString(2) + " DNI del cliente que va: " + resul.getString(3));
			}

			resul.close();
			sentencia.close();

			System.out.println("Fin de lectura");

		} catch (SQLException e) {
			System.out.println("error mostrando el registro de clientes en la visita");
		}
		
	}
	public static void eliminaVisita(Connection conexion, BufferedReader reader)
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

			resul.close();
			sentencia.close();

		} catch (SQLException e) {
			System.out.println("error conn la base de datos");
		}

		Codigovisita(conexion);// mostrar codigo y nombre de la visita 

		int codigo=-1;
		// verificar que el usuario mete un codigo existente

		do {
			//
			System.out.println("Escribe el codigo de la visita que quieres eliminar");
			
			try {
				codigo = Integer.parseInt(reader.readLine());
			} catch (NumberFormatException e) {
				System.out.println("mete unvalor númerico");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int x = 0; x < codigos.length; x++) {
				if (codigo == codigos[x]) {
					seguir = true;
					break;
				}
			}
		} while (!seguir);
		
		//ELIMINAR VISITA

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
	
	
	public static void meterVisita(Connection conexion, BufferedReader reader) throws IOException {
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

		int Max=1;
		do {
			System.out.println("Número de clientes maximo");
			try {
				Max = Integer.parseInt(reader.readLine());
			} catch (NumberFormatException e) {
				System.out.println("mete unvalor númerico");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (Max < 3) {
				System.out.println("minimo 3 clientes");
			} else {
				seguir = true;
			}
		} while (!seguir);

		seguir = false;

		double coste=0;
		do {
			System.out.println("Coste de la visita");
			try {
				coste = Double.parseDouble(reader.readLine());
			} catch (NumberFormatException e) {
				System.out.println("mete unvalor númerico");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		
		//INSERTAR NUEVA VISITA

		try {

			String query = "INSERT INTO visitas(`Nombre`, `Tematica`, `ClientesM`, `Coste`,`LugarP`,`Ubicacion`) VALUES('"
					+ nombre + "','" + visita + "','" + Max + "','" + coste + "','" + Lugarpartida + "','" + ubicacion
					+ "') ";//nueva visita
			PreparedStatement preparedStmt = conexion.prepareStatement(query);

			preparedStmt.executeUpdate();

			preparedStmt.close();

		} catch (SQLException e) {
			System.out.println("Error insertado la nueva visita guiada");

		}

	}
	
	
	

	public static void Codigovisita(Connection conexion) {
		try {

			Statement sentencia = conexion.createStatement();
			ResultSet resul = ((java.sql.Statement) sentencia).executeQuery("SELECT * FROM visitas");

			while (resul.next()) {
				System.out.println("Visita con codigo: " + resul.getInt(1) + " Nombre: " + resul.getString(2));
			}

			resul.close();
			sentencia.close();

		} catch (SQLException e) {
			System.out.println("error mostrando el codigo de las visitas");
		}

	}
	
	
	public static void verVisitas(Connection conexion) {
		try {

			Statement sentencia = conexion.createStatement();
			ResultSet resul = ((java.sql.Statement) sentencia).executeQuery("SELECT * FROM visitas");

			while (resul.next()) {
				System.out.println("Codigo: " + resul.getInt(1) + " Nombre: " + resul.getString(2) + " Tematica: "
						+ resul.getString(3) + " Número de clientes permitidos: " + resul.getInt(4) + " Coste: "
						+ resul.getDouble(5) + " Lugar de Partida: " + resul.getString(6) + " Ubicación: "
						+ resul.getString(7));
			}

			resul.close();
			sentencia.close();

			System.out.println("Fin de lectura");

		} catch (SQLException e) {
			System.out.println("error mostrando las visitas");

		}

	}
	
	
	

}
