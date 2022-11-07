package proyecto2;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Clientes {

	public static void registrarClienteVisi(Connection conexion, BufferedReader reader) throws IOException {
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

			resul.close();
			sentencia.close();

		} catch (SQLException e) {
			System.out.println("error con el codigo de las visitas");
		}

		Visitas.Codigovisita(conexion);// mostrar codigo y nombre de la visita

		int codigo;
		// verificar que el usuario mete un codigo existente

		do {
			//
			System.out.println("Escribe el codigo de visita en la que quieres meter un cliente");
			codigo = Integer.parseInt(reader.readLine());
			for (int x = 0; x < codigos.length; x++) {
				if (codigo == codigos[x]) {
					seguir = true;
					break;
				}
			}
		} while (!seguir);

		DNIcliente(conexion);// mostrar dni y nombre cliente

		boolean ar = false;

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
		
		ar=false;
		

		String hora;
		do {
			System.out.print("Escribe a que hora empezara");
			hora = reader.readLine();
			if(hora.length()>=5) {
				ar=true;
			}else {
				System.out.println("minimo 5 caracteres");
			}
		} while (!ar);
		
		//INSERCION DE NUEVO CLIENTE EN UNA VISITA

		try {

			String query = "INSERT INTO VisitaC(`Cvisita`,`Horario`, `DNIc`) VALUES('" + codigo + "','" + hora + "','"
					+ DNI + "') ";
			PreparedStatement preparedStmt = conexion.prepareStatement(query);

			preparedStmt.executeUpdate();

			preparedStmt.close();

		} catch (SQLException e) {
			System.out.println("Error insertado el cliente en una visita");

		}

	}

	public static void editarCliente(Connection conexion, BufferedReader reader) throws IOException {
		DNIcliente(conexion);// mostrar dni y nombre cliente

		boolean ar = false;
		// asegurar que el dni mide 9 caracteres

		String DNI;
		do {
			System.out.println("Escribe el DNI del cliente al que quieras cambiar la profesion");
			DNI = reader.readLine();

			if (DNI.length() == 9) {
				ar=true;

				break;
			} else {
				System.out.println("este dni no sirve");
			}

		} while (!ar);
		
		ar=false;
		

		String profesion;
		do {
			System.out.println("Escribe la nueva profesión");
			profesion = reader.readLine();
			if(profesion.length()>=4) {
				ar=true;
			}else {
				System.out.println("minimo 4 caracteres");
			}
		} while (!ar);
		
		//EDITAR EL CLIENTE

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

	public static void verClientes(Connection conexion) {
		
		try {

			Statement sentencia = conexion.createStatement();
			ResultSet resul = ((java.sql.Statement) sentencia).executeQuery("SELECT * FROM clientes");

			
			while (resul.next()) {
				System.out.println("DNI: " + resul.getString(1) + " Nombre: " + resul.getString(2) + " Apellido: "
						+ resul.getString(3) + " Edad: " + resul.getInt(4) + " Profesión: " + resul.getString(5));
			}

			resul.close();
			sentencia.close();

			System.out.println("Fin de lectura");

		} catch (SQLException e) {
			System.out.println("error mostrando los clientes");

		}

	}

	public static void DNIcliente(Connection conexion) {
		//MOSTRAR DNI DE LOS CLIENTES

		try {

			Statement sentencia = conexion.createStatement();
			ResultSet resul = ((java.sql.Statement) sentencia).executeQuery("SELECT * FROM clientes");

			while (resul.next()) {
				System.out.println("DNI cliente : " + resul.getString(1) + " Nombre: " + resul.getString(2));
			}

			resul.close();
			sentencia.close();

		} catch (SQLException e) {
			System.out.println("error mostrando el DNI de los clientes");
		}

	}

	public static void eliminarCliente(Connection conexion, BufferedReader reader) throws IOException {
		boolean seguir = false;
		// eliminar cliente

		DNIcliente(conexion);// muestra el dni y nombre del cliente

		String empleado;
		do {

			System.out.print("Escribe el DNI del cliente que quieras echar");
			empleado = reader.readLine();
			if (empleado.length() == 9) {
				seguir = true;
			} else {
				System.out.println("este dni no sirve");
			}
		} while (!seguir);
		
		//ELIMINAR EL CLIENTE

		try {
			String query = "DELETE FROM clientes WHERE `DNI`='" + empleado + "'";
			PreparedStatement preparedStmt = conexion.prepareStatement(query);// con este metodo ya que estamos haciendo
																				// un cambio en la base

			preparedStmt.executeUpdate();
			preparedStmt.close();

			System.out.println("empleado despedido");
		} catch (SQLException e) {
			System.out.println("error eliminando un cliente");
		}
	}

	public static void meterCliente(Connection conexion, BufferedReader reader) throws IOException {
		boolean seguir = false;
		
		//BUCLES PARA VERIFICAR QUE SE METEN BIEN LOS VALORES
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
		
		//METER EL DATO

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

}
