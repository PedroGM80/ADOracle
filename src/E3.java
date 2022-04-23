
import java.math.BigDecimal;
import java.sql.*;
import java.sql.Connection;
import java.util.Objects;

public class E3 {

    public static void insertar(String tabla, String valores) {
        // 1. Conectarte al SGBD
        try {
            var conexion = E2.getSystem();
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
    public static void insertarTablaAnidada(String tabla, String valores) {
        // 1. Conectarte al SGBD
        try {
            var conexion = E2.getSystem();
            // 2. Crear un Statement
            Statement sentencia = conexion.createStatement();
            // 3. Ejecutarlo
            String consulta = "Insert into table" + tabla + " values ( " + valores + " )";
            sentencia.execute(consulta);
            sentencia.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void borrar(String tabla, String condicion) {
        // 1. Conectarte al SGBD
        try {
            var conexion = E2.getSystem();
            // 2. Crear un Statement
            Statement sentencia = conexion.createStatement();
            // 3. Ejecutarlo
            String consulta = "Delete from " + tabla + " where " + condicion;
            sentencia.execute(consulta);
            sentencia.close();
            conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ResultSet consulta(String campos, String tabla) {
        try {
            Connection conexion = E2.getSystem();
            Statement sentencia = conexion.createStatement();
            String consulta = "SELECT " + campos + " FROM " + tabla;
            return sentencia.executeQuery(consulta);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void transaccion(String tabla, String valoresReintegro, String valoresIngreso) {
        // 1. Conectarte al SGBD
        Connection conexion = null;
        try {
            conexion = E2.getSystem();
            Statement sentencia = conexion.createStatement();
            // 3. Ejecutarlo

            String consulta1 = "Insert into " + tabla + " values( " + valoresReintegro + " )";
            String consulta2 = "Insert into " + tabla + " values( " + valoresIngreso + " )";
            conexion.setAutoCommit(false);

            sentencia.execute(consulta1);
            sentencia.execute(consulta2);
            if (valoresIngreso.contains(",'I',") && valoresReintegro.contains(",'R',")) {
                System.out.println("Transaccion correcta");
                conexion.commit();
            } else {
                System.err.println("Transaccion Incorrecta debe haber una transacción de ingreso y otra de reintegro");
                throw new SQLException("Transaccion incorrecta");
            }

            sentencia.close();
            conexion.close();

        } catch (SQLException e) {

            try {
                Objects.requireNonNull(conexion).rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }


    }

    public static void actualizar(String campos, String tabla) {

        try {
            Connection connexion = E2.getSystem();
            Statement sentencia = connexion.createStatement();
            // 3. Ejecutarlo
            String sql = "UPDATE " + tabla + " SET " + campos;
            sentencia.execute(sql);
            System.out.println("Actualización correcta");
            sentencia.close();
            connexion.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


    public static void main(String[] args) throws SQLException {
    /*    insertar("CUENTAS", "1,new PERSONA(1,'Juan',new DIRECCION('San Quintin','San Fernando','11100'), SYSDATE),0.0");
        insertar("CUENTAS", "2,new PERSONA(2,'Pedro',new DIRECCION('Calle Rosario','San Fernando','11100'), SYSDATE),0.0");
        insertar("CUENTAS", "3,new PERSONA(3,'Marco',new DIRECCION('Calle Real','San Fernando','11100'), SYSDATE),0.0");

        insertar("MOVIMIENTOS", "1,new  T_MOVIM('7200,50','I',SYSDATE)");
        insertar("MOVIMIENTOS", "2,new  T_MOVIM('1200,50','I',  SYSDATE )");
        insertar("MOVIMIENTOS", "3,new  T_MOVIM('15100,50','I',  SYSDATE )");
        insertar("MOVIMIENTOS", "1,new  T_MOVIM('300,50','R',SYSDATE)");
        insertar("MOVIMIENTOS", "2,new  T_MOVIM('200,50','R',  SYSDATE)");
        insertar("MOVIMIENTOS", "3,new  T_MOVIM('100,50','R', SYSDATE )");*/
      //  insertar("CUENTAS", "13,new PERSONA(13,'Pepin',new DIRECCION('Calle Carraca','San Fernando','11100'), SYSDATE),0.0");
        var campos = "  c.datos.nombre,sum(m.mov.importe),m.mov.tipomov";
        String nombre;
        StringBuilder resume = new StringBuilder();
        var tablas = "cuentas c, movimientos m where c.numcta=m.numcta group by  c.datos.nombre,m.mov.tipomov order by c.datos.nombre";
        var rs = consulta(campos, tablas);
        BigDecimal ingresos = BigDecimal.valueOf(0.0);
        BigDecimal reintegros = BigDecimal.valueOf(0.0);

        while (Objects.requireNonNull(rs).next()) {
            nombre = rs.getString(1);
            char tipo = rs.getString(3).charAt(0);
            if (tipo == 'I') {
                System.out.println(nombre + " suma de ingresos: " + rs.getBigDecimal(2) + "€");
                ingresos = rs.getBigDecimal(2);
            } else if (tipo == 'R') {
                System.out.println(nombre + " suma de reintegros: " + rs.getBigDecimal(2) + "€");
                reintegros = rs.getBigDecimal(2);
            }
            if (reintegros.doubleValue() != 0 && ingresos.doubleValue() != 0) {
                resume.append("Extracto de : ").append(nombre).append(" Suma de ingresos:").append(ingresos).append("€ Suma de reintegros: ").append(reintegros).append("€\n");

                BigDecimal saldo = ingresos.subtract(reintegros);
                System.out.println(nombre + ":" + saldo);
                actualizar("SALDO= " + saldo + " where C.DATOS.nombre= '" + nombre + "'", "CUENTAS C");
                ingresos = BigDecimal.valueOf(0.0);
                reintegros = BigDecimal.valueOf(0.0);
            }
        }
        System.out.println(resume);
        transaccion("MOVIMIENTOS", "13,new  T_MOVIM('100,50','R', SYSDATE )", "1,new  T_MOVIM('100,50','R', SYSDATE )");
    }

}


