package TestExamen;

import java.math.BigDecimal;
import java.sql.*;
import java.sql.Connection;
import java.util.Objects;
import java.sql.SQLException;
import java.sql.Statement;

public class TestEx {
    public static Connection getSystem() throws SQLException {
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "System", "1234");
    }
    public static void insertar(String tabla, String valores) {
        // 1. Conectarte al SGBD
        try {
            var conexion = getSystem();
            // 2. Crear un Statement
            Statement sentencia = conexion.createStatement();
            // 3. Ejecutarlo
            String consulta = "Insert into " + tabla + " values( " + valores + " )";
            sentencia.execute(consulta);
            sentencia.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

        insertar("PROPIEDAD","Persona( 1, 'María', Direccion('Avd/ España','Sevilla',10001),'21/02/1978' ),array_coches(COCHE( 'CA56712R','BMW','X1',2014, 321 ))");

        insertar("PROPIEDAD","Persona( 2, 'Ramón', Direccion('c/ Conde','Málaga',12001),'15/07/1980' ),array_coches(COCHE( 'CA4892V','BMW','M3',2016,254 ),Coche('CA13567R','Renault','Laguna',2013, 105))");
        insertar("PROPIEDAD","Persona( 3, 'María', Direccion('c/Huerta','Málaga',12001),'15/07/1980' ),array_coches(COCHE( 'CA4892V','BMW','M3',2016,254 ),Coche('CA13567R','Renault','Laguna',2013, 105))");
        insertar("PROPIEDAD","Persona( 4, 'María', Direccion('c/Huerta','Ca',10001),'21/02/1978' ),array_coches(COCHE( 'CA56712R','BMW','X1',2014, 321 ))");

    }
}
