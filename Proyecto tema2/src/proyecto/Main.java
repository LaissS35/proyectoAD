package proyecto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.DatabaseMetaData;

public class Main {

	public static void main(String[] args) throws IOException {
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

	
	
	
	private static void menu() throws NumberFormatException, IOException {
BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Bienvenido!¿que agencia deseas consultar?:"
				+ "1.-Agencia Pinkilo (H2)"
				+ "2.-Agencia Avian(Mysql)"
				+ "3.-Agencia Caloriego(?)");
		
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

	private static void Mpinkilo(BufferedReader reader) throws NumberFormatException, IOException {
		System.out.println("Bienvenido a la agencia de turismo PINKILO :"
				+ "1.-Ver datos de empresa"
				+ "2.-ver Empleados"
				+ "3.-ver Visitas guiadas"
				+ "4.-ver Clientes"
				+ "5.-despedir Empleado"
				+ "6.-Desactivar visita guiada"
				+ "7.-Vetar cliente"
				+ "8.-volver al inicio");
		int eleccion = Integer.parseInt(reader.readLine());
		
		if(eleccion==1) {
			try {
				
				Statement sentencia = conexion.createStatement();
				ResultSet resul = ((java.sql.Statement) sentencia).executeQuery("SELECT * FROM Empleados");
				
				// Recorremos el resultado para visualizar c
				// Se hace un bucle mientras haya registros visualizando
				while (resul.next())
				{
				System.out.println ("ID: "+resul.getInt(1) + " Nombre: "+resul.getString(2)+ " Apellido: " + resul.getString(3)+" Oficion: "+resul.getString(4)+" Salario: "+resul.getDouble(5));
				}
				
				resul.close();// Cerrar ResultSet
				sentencia.close();// Cerrar Statement

				}catch (SQLException e) {
				e.printStackTrace();
				
				}
			
		}
	}

}
