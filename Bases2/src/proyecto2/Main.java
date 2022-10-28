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

import com.mysql.cj.jdbc.DatabaseMetaData;

public class Main {

	public static void main(String[] args) throws IOException, NumberFormatException, ClassNotFoundException, SQLException {
		// H2, mysql, sqllite
		
		
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
			Mcaloriego(reader);
			
		}
		
		
	}
	private static void Mcaloriego(BufferedReader reader) throws ClassNotFoundException, SQLException, NumberFormatException, IOException {
		//SQLlite
		//Driver
		Class.forName("org.sqlite.JDBC");
		// Establecemos la conexion con la BD
		Connection conexion = DriverManager.getConnection("jdbc:sqlite:/Users/lais/Desktop/Turismo.db");
		
		
		
		System.out.println("Bienvenido a la agencia de turismo PINKILO :"
				+ " 1.-Ver datos de empresa"
				+ " 2.-ver Empleados"
				+ " 3.-ver Visitas guiadas"
				+ " 4.-ver Clientes"
				+ " 5.-despedir Empleado"
				+ " 6.-Desactivar visita guiada"
				+ " 7.-Eliminar cliente"
				+ " 8.-volver al inicio"
				+"  9.-Eliminar visita guiada"
				+"  10.-Añadir nuevo cliente"
				+ " 11.-Añadir nueva visita guiada"
				+ " 12.-Contratar nuevo empleado"
				+ " 13.-Editar visita guiada"
				+  "14.-Asignar nueva visita guiada a Empleado"
				+"  15.-Editar cliente");
		int eleccion = Integer.parseInt(reader.readLine());
		
			
		
	}
	
	//PINKILO

	private static void Mpinkilo(BufferedReader reader) throws NumberFormatException, IOException, SQLException, ClassNotFoundException {
				//H2
		
				//Cargar el driver
					Class.forName("com.mysql.cj.jdbc.Driver");
				
				// Establecemos la conexion con la BD
				
					Connection conexion =DriverManager.getConnection("jdbc:mysql://localhost/turismo","root", "soylalecheN7"); //mysql
				
					DatabaseMetaData dbmd = (DatabaseMetaData) conexion.getMetaData();//Creamos objeto DatabaseMetaData H2
			
		System.out.println("Bienvenido a la agencia de turismo PINKILO :"
				+ " 1.-Ver datos de empresa"
				+ " 2.-ver Empleados"
				+ " 3.-ver Visitas guiadas"
				+ " 4.-ver Clientes"
				+ " 5.-despedir Empleado"
				+ " 6.-Desactivar visita guiada"
				+ " 7.-Vetar cliente"
				+ " 8.-volver al inicio"
				+"  9.-Contratar Empleado");
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
		//VER VISITAS
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
		//VER CLIENTES
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
		//DESPEDIR CLEINTE
		if(eleccion==5) {
			//funciona elimina linea seleccionada
			//despedir empleado por DNI
			System.out.print("Escribe el DNI del empleado que quieres despedir");
			String empleado = reader.readLine();
			
			
			String query ="DELETE FROM empleados WHERE `DNI`='"+empleado+"'";
			PreparedStatement preparedStmt = conexion.prepareStatement(query);//con este metodo ya que estamos haciendo un cambio en la base
			
			preparedStmt.executeUpdate();
			preparedStmt.close();
			
			
			System.out.println("empleado despedido");
			//vuelta al menu
			Mpinkilo(reader);
		}
		////DESACTIVAR VISITA GUIADA
		if(eleccion==6) {
			//funciona, desactiva
			//cambiar estado de visita guiada a desactivado
			System.out.println("Escribe el codigo de la visita que quieres desactivar");
			int codigo= Integer.parseInt(reader.readLine());
			
			String query ="UPDATE visitas SET `Estado` = 'Desactivo' WHERE (`Codigo`='"+codigo+"')";
			PreparedStatement preparedStmt = conexion.prepareStatement(query);
			
			preparedStmt.executeUpdate();
			preparedStmt.close();
			
		}
		//ELIMINAR CLIENTE
		if(eleccion==7) {
			//eliminar cliente
			//funciona elimina linea seleccionada
			//despedir empleado por DNI
			System.out.print("Escribe el DNI del cliente que quieras echar");//AQUI
			String empleado = reader.readLine();
			
			
			String query ="DELETE FROM empleados WHERE `DNI`='"+empleado+"'";
			PreparedStatement preparedStmt = conexion.prepareStatement(query);//con este metodo ya que estamos haciendo un cambio en la base
			
			preparedStmt.executeUpdate();
			preparedStmt.close();
			
			
			System.out.println("empleado despedido");
		}
		if(eleccion==8) {
			System.out.println("Vuelves al menu principal");
			menu();
		}
		if(eleccion==9) {
			//SE AÑADE UN EMPLEADO SIN VISITA 
			
				System.out.print("Escribe el DNI del nuevo empleado");
				String DNI = reader.readLine();
				System.out.print("Escribe el nombre del nuevo empleado:");
				String nombre = reader.readLine();
				System.out.print("Escribe su apellido");
				String apellido = reader.readLine();
				System.out.print("Fecha de nacimiento");
				String oficio =reader.readLine();
				System.out.print("Fecha alta");
				String Falta=reader.readLine();
				System.out.print("Nacionalidad");
				String nacionaliddad = reader.readLine();
				System.out.println("Oficio");
				String cargo= reader.readLine();
				
				
				
				try {
				
				
				 String query = "INSERT INTO Empleados(`DNI`,`Nombre`, `Apellido`, `F.nacimiento`, `F.alta`,`Nacionalidad`,`Cargo`) VALUES('"+DNI+"','"+nombre+"','"+apellido+"','"+oficio+"','"+Falta+"','"+nacionaliddad+"','"+cargo+"') ";
			      PreparedStatement preparedStmt = conexion.prepareStatement(query);
			     
			      preparedStmt.executeUpdate();
			      
			      preparedStmt.close();
			      
			
				System.out.print("nuevo empleado añadido");
				
				}catch (SQLException e) {
					System.out.println("Error contratando al nuevo empleado");
					e.printStackTrace();
					
				}
				
				menu();
	
		}
		if(eleccion==10) {
			//FUNCIONA NUEVO CLIENTE
			System.out.print("Escribe el DNI del nuevo empleado");
			String DNI = reader.readLine();
			
			System.out.print("Escribe el nombre del nuevo empleado:");
			String nombre = reader.readLine();
			
			System.out.print("Escribe su apellido");
			String apellido = reader.readLine();
			
			System.out.print("Edad");
			int Ndepa= Integer.parseInt(reader.readLine());
			
			System.out.println("Profesión");
			String profesion= reader.readLine();
			
			
			try {
			
			
			 String query = "INSERT INTO clientes(`DNI`,`Nombre`, `Apellido`, `Edad`, `Profesion`) VALUES('"+DNI+"','"+nombre+"','"+apellido+"','"+Ndepa+"','"+profesion+"') ";
		      PreparedStatement preparedStmt = conexion.prepareStatement(query);
		     
		      preparedStmt.executeUpdate();
		      
		      preparedStmt.close();
		      
		
			
			
			}catch (SQLException e) {
				System.out.println("Error insertado el nuevo cliente");
				
			}
			
			menu();
		}
		if(eleccion==11) 
		{
			//FUNCIONA, AÑADE NUEVA VISITA GUIADA
			System.out.print("Escribe el nombre del nuevo empleado:");
			String nombre = reader.readLine();
			
			System.out.print("Describe la visita");
			String visita = reader.readLine();
			
			
			System.out.print("Número de clientes maximo");
			int Max= Integer.parseInt(reader.readLine());
			
			
			System.out.print("Coste de la visita");
			double coste = Double.parseDouble(reader.readLine());
			
			
			System.out.println("Lugar de partido");
			String Lugarpartida= reader.readLine();
			
			System.out.println("Ubicacion de la visita");
			String ubicacion = reader.readLine();
			
			String estado= "Activo";
			
			
			try {
			
			
			 String query = "INSERT INTO visitas(`Nombre`, `Tematica`, `ClientesM`, `Coste`,`LugarP`,`Ubicacion`,`Estado`) VALUES('"+nombre+"','"+visita+"','"+Max+"','"+coste+"','"+Lugarpartida+"','"+ubicacion+"','"+estado+"') ";
		      PreparedStatement preparedStmt = conexion.prepareStatement(query);
		     
		      preparedStmt.executeUpdate();
		      
		      preparedStmt.close();
		      
		
			
			
			}catch (SQLException e) {
				System.out.println("Error insertado la nueva visita guiada");
				
			}
			menu();
		}
		if(eleccion==12) {
			//FUNCIONA ELIMINACION DE VISITA GUIADA
			
			System.out.print("Escribe el codigo de la visita que quieres eliminar");
			int codigo= Integer.parseInt(reader.readLine());
			
			
			String query ="DELETE FROM visitas WHERE `Codigo`='"+codigo+"'";
			PreparedStatement preparedStmt = conexion.prepareStatement(query);
			
			preparedStmt.executeUpdate();
			preparedStmt.close();
			
			
			System.out.println("Visita eliminada");
			menu();
			
		}
		if(eleccion==13) {
			//editar cliente,FUNCIONA
			
			System.out.println("Escribe el DNI del cliente al que quieras cambiar la profesion");
			String dni= reader.readLine();
			
			System.out.println("Escribe la nueva profesión");
			String profesion = reader.readLine();
			
			
			String query ="UPDATE clientes SET `Profesion` = '"+profesion+"' WHERE (`DNI`='"+dni+"')";
			PreparedStatement preparedStmt = conexion.prepareStatement(query);
			
			preparedStmt.executeUpdate();
			preparedStmt.close();
			
			System.out.println("Cliente editado, con exito");
			menu();
		}
		if(eleccion==14) {
			//editar visita guiada 
			
			System.out.println("Escribe el codigo de la visita que quieres editar");
			
			int codigo= Integer.parseInt(reader.readLine());
			
			System.out.print("escribe su nueva descripción");
			String descripcion = reader.readLine();
			System.out.print("nuevos coste");
			double coste = Double.parseDouble(reader.readLine());
			System.out.print("número maximo de clientes");
			int numeroM = Integer.parseInt(reader.readLine());
			System.out.print("nuevo lugar de partida");
			String lugarP =reader.readLine();
			System.out.print("Ubicacion visita");
			String ubicacion = reader.readLine();
			System.out.print("su estado (activo o desactivo)");
			String estado = reader.readLine();
			
			//CONTROLAR ESTADO, NUMERO DE CARACTERES EN CADA STRING
			
			String query ="UPDATE visitas SET `V.cargo` = '"+codigo+"' WHERE (`Codigo`='"+codigo+"')";
			PreparedStatement preparedStmt = conexion.prepareStatement(query);
			
			preparedStmt.executeUpdate();
			preparedStmt.close();
			menu();
			
		}
		if(eleccion==15) {
			//asignar visita guiada a empleado, FUNCIONA
			
			System.out.println("Escribe el DNI del empleado al que quieres asignar una nueva visita guiada, recuerda, un empleado solo puede estar a cargo de una visita guiada");
			String dni= reader.readLine();
			System.out.println("codigo de la visita que le asignaras");
			int codigo= Integer.parseInt(reader.readLine());
			
			//SI LA VISITA ESTA DESACTIVADA IMPEDIR QUE SE LE ASIGNE
			
			String query ="UPDATE Empleados SET `V.cargo` = '"+codigo+"' WHERE (`DNI`='"+dni+"')";
			PreparedStatement preparedStmt = conexion.prepareStatement(query);
			
			preparedStmt.executeUpdate();
			preparedStmt.close();
			menu();
		}
	}
}



