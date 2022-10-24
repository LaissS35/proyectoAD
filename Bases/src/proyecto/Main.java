package proyecto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.DatabaseMetaData;

public class Main {

	public static void main(String[] args) throws IOException, NumberFormatException, ClassNotFoundException, SQLException {
		// H2, mysql
		
		
		menu();
		
		

	}
	//DATOS DE LA BASE DE DATOS
	public static void DatosBD(Connection conexion, DatabaseMetaData dbmd) throws SQLException {
		
		ResultSet resul = null;
		String nombre = dbmd.getDatabaseProductName();
		String driver = dbmd.getDriverName();
		String url = dbmd.getURL();
		String usuario = dbmd.getUserName() ;
		
		
		System.out.println("INFORMACIN SOBRE LA BASE DE DATOS:"+ nombre);
		System.out.println("Driver : " + driver );
		System.out.println("URL : " + url );
		System.out.println("Usuario: " + usuario );
		resul = dbmd.getTables(null, "ejemplo", null, null);
		
		System.out.println();//espacio
		
		
	
		
		
		while(resul.next()) {
			String catal = resul.getString(1);//columna1
			String esquema = resul.getString(2);
			String tabla = resul.getString(3);
			String tipo = resul.getString(4);
			String cinco = resul.getString(5);
			System.out.println(tipo +", catalogo: "+catal+", esquema: "+esquema+", tabla: "+tabla);
			
		}
		
		
		
		
	}

	
	
	
	private static void menu() throws NumberFormatException, IOException, ClassNotFoundException, SQLException {
BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Bienvenido!¿que agencia deseas consultar?:"
				+ "1.-Agencia Pinkilo (H2)"
				+ "2.-Agencia Avian(Mysql)"
				+ "3.-Agencia Caloriego(SQLlite)");
		
		int opcion= Integer.parseInt(reader.readLine());//coger un valor integer
		
		
		if (opcion ==1) {
			Mpinkilo(reader);
			
		}
		if(opcion==2) {
			
		}
		if(opcion==3) {
			
		}
		
		
	}
	//PINKILO

	private static void Mpinkilo(BufferedReader reader) throws NumberFormatException, IOException, SQLException, ClassNotFoundException {
				//Cargar el driver
				/*Class.forName("com.mysql.cj.jdbc.Driver");
				
				// Establecemos la conexion con la BD
				
					Connection conexion =DriverManager.getConnection("jdbc:mysql://localhost/turismo","root", "soylalecheN7");*/ //H2 creo
				
					
				
				// Establecemos la conexion con la BD
					
					//SQLlite
		Class.forName("org.sqlite.JDBC");
				
					Connection conexion = null;
					try {
						conexion = DriverManager.getConnection("jdbc:sqlite:/Users/lais/Desktop/Turismo.db");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	
					
				//	DatabaseMetaData dbmd = (DatabaseMetaData) conexion.getMetaData();//Creamos objeto DatabaseMetaData H2
					
					
					
		System.out.println("Bienvenido a la agencia de turismo PINKILO :"
				+ " 1.-Ver datos de empresa"
				+ " 2.-ver Empleados"
				+ " 3.-ver Visitas guiadas"
				+ " 4.-ver Clientes"
				+ " 5.-despedir Empleado"
				+ " 6.-Desactivar visita guiada"
				+ " 7.-Vetar cliente"
				+ " 8.-volver al inicio");
		int eleccion = Integer.parseInt(reader.readLine());
		//DATOS DE EMPRESA
		if(eleccion==1) {
			try {
			
				
				Statement sentencia = conexion.createStatement();
				ResultSet resul = ((java.sql.Statement) sentencia).executeQuery("SELECT * FROM empresa");
				
				// Recorremos el resultado para visualizar 
				// Se hace un bucle mientras haya registros visualizando
				while (resul.next())
				{
				System.out.println ("Nombre: "+resul.getString(1) + " Apertura: "+resul.getString(2)+ " Dirección: " + resul.getString(3)+" Propietario: "+resul.getString(4));
				}
				
				resul.close();// Cerrar ResultSet
				sentencia.close();// Cerrar Statement
				
				System.out.println("Fin de lectura");

				}catch (SQLException e) {
				System.out.println("error conn la base de datos");
				
				}
			Mpinkilo(reader);
			
			
		}
		//VER EMPLEADOS
		if(eleccion==2) {
			try {
				
				Statement sentencia = conexion.createStatement();
				//ResultSet resul = sentencia.executeQuery("SELECT * FROM empleados");
				ResultSet resul = ((java.sql.Statement) sentencia).executeQuery("select * from empleados");
				
				// Recorremos el resultado para visualizar 
				// Se hace un bucle mientras haya registros visualizando
				while (resul.next())
				{
				System.out.println ("DNI: "+resul.getString(1) + " Nombre: "+resul.getString(2)+ " Apellido: " + resul.getString(3)+" Fecha de Nacimiento: "+resul.getString(4)+" Fecha Alta: "+resul.getString(5)+
						" Nacionalidad: "+resul.getString(6)+" Cargos: "+resul.getString(7)+" ESta acagor de la visita con codigo: "+resul.getInt(8));
				}
				
				resul.close();// Cerrar ResultSet
				sentencia.close();// Cerrar Statement
				
				System.out.println("Fin de lectura");

				}catch (SQLException e) {
					e.printStackTrace();
				
				}
			Mpinkilo(reader);
			
		}
		
		if(eleccion==3) {
			try {
			
				Statement sentencia = conexion.createStatement();
				ResultSet resul = ((java.sql.Statement) sentencia).executeQuery("SELECT * FROM visitas");
				
				// Recorremos el resultado para visualizar 
				// Se hace un bucle mientras haya registros visualizando
				while (resul.next())
				{
				System.out.println ("Codigo: "+resul.getInt(1) + " Nombre: "+resul.getString(2)+ " Tematica: " + resul.getString(3)+" Número de clientes permitidos: "+resul.getInt(4)+" Coste: "+resul.getDouble(5)+
						" Lugar de Partida: "+resul.getString(6)+" Ubicación: "+resul.getString(7));
				}
				
				resul.close();// Cerrar ResultSet
				sentencia.close();// Cerrar Statement
				
				System.out.println("Fin de lectura");

				}catch (SQLException e) {
				System.out.println("error conn la base de datos");
				
				}
			Mpinkilo(reader);
		}
		if(eleccion==4) {
			try {
				
				Statement sentencia = conexion.createStatement();
				ResultSet resul = ((java.sql.Statement) sentencia).executeQuery("SELECT * FROM clientes");
				
				// Recorremos el resultado para visualizar 
				// Se hace un bucle mientras haya registros visualizando
				while (resul.next())
				{
				System.out.println ("DNI: "+resul.getString(1) + " Nombre: "+resul.getString(2)+ " Apellido: " + resul.getString(3)+" Edad: "+resul.getInt(4)+" Profesión: "+resul.getString(5));
				}
				
				resul.close();// Cerrar ResultSet
				sentencia.close();// Cerrar Statement
				
				System.out.println("Fin de lectura");

				}catch (SQLException e) {
				System.out.println("error conn la base de datos");
				
				}
			Mpinkilo(reader);
		}
	}


}
