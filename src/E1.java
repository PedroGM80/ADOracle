import java.math.BigDecimal;
import java.sql.*;
import java.sql.Connection;

import oracle.jdbc.pool.OracleDataSource;

public class E1 {
    public static void insertar(String tabla, String valores) throws SQLException {
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

    private static Connection getSystem() throws SQLException {
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "System", "1234");
    }

    public static void main(String[] args) throws SQLException {
        insertar("CUENTAS", "1,new PERSONA(1,'Juan',new DIRECCION('San Quintin','San Fernando','11100'), SYSDATE),0.0");
        insertar("CUENTAS", "2,new PERSONA(2,'Pedro',new DIRECCION('Calle Rosario','San Fernando','11100'), SYSDATE),0.0");
        insertar("CUENTAS", "3,new PERSONA(3,'Marco',new DIRECCION('Calle Real','San Fernando','11100'), SYSDATE),0.0");
        insertar("MOVIMIENTOS", "1,new  T_MOVIM('7200,50','I',SYSDATE)");
        insertar("MOVIMIENTOS", "2,new  T_MOVIM('1200,50','I',  SYSDATE )");
        insertar("MOVIMIENTOS", "3,new  T_MOVIM('15100,50','I',  SYSDATE )");
        insertar("MOVIMIENTOS", "1,new  T_MOVIM('300,50','R',SYSDATE)");
        insertar("MOVIMIENTOS", "2,new  T_MOVIM('200,50','R',  SYSDATE)");
        insertar("MOVIMIENTOS", "3,new  T_MOVIM('100,50','R', SYSDATE )");
    }
}


