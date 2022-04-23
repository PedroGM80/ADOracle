//create table alumnos2(Alumnos Alumno)
//insert into alumnos2 values(NEW ALUMNO(new PERSONA(1,'Manolo Ramos', new DIRECCION('San Quintin','San Fernando','11100'), SYSDATE),'6,2','3,5','2,1'));
//insert into alumnos2 values(NEW ALUMNO(new PERSONA(2,'Pepe', new DIRECCION('San Juan','GUADALAJARA','11300'), SYSDATE),'9,1','6,5','4,1'));
//insert into alumnos2 values(NEW ALUMNO(new PERSONA(3,'Marco', new DIRECCION('San Rafael','San Fernando','11100'), SYSDATE),'4,2','5,5','6,1'));
//insert into alumnos2 values(NEW ALUMNO(new PERSONA(4,'Raul', new DIRECCION('Calle Real','San Fernando','11100'), SYSDATE),'8,2','9,5','9,1'));
/*
Ejercicio 2. Crea la tabla ALUMNOS2 del tipo T_ALUMNO e inserta objetos en ella. Desde
Java, realiza luego una consulta que visualice:
- El nombre del alumno y la nota media.
- Alumnos de GUADALAJARA con nota media mayor de 6.
- Nombre Alumno con más nota media.
- Nombre del alumno con nota más alta (cualquiera de sus notas).
 */


import java.sql.*;
import java.util.Objects;

public class E2 {
    public static ResultSet consulta(String campos, String tabla) {
        try {
            Connection conexion = getSystem();
            Statement sentencia = conexion.createStatement();
            String consulta = "SELECT " + campos + " FROM " + tabla;
            return sentencia.executeQuery(consulta);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getSystem() throws SQLException {
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "System", "1234");
    }

    public static void main(String[] args) throws SQLException {

        var rs = consulta(" a.alumnos.unapersona.nombre Nombre,a.alumnos.nota_media() Nota_media", "alumnos2 A");
        while (Objects.requireNonNull(rs).next()) {
            var nombre = rs.getString(1);
            var notaMedia = rs.getFloat(2);
            System.out.println(nombre + " Nota media: " + notaMedia);
        }
        rs = consulta(" a.alumnos.unapersona.nombre Nombre,a.alumnos.nota_media() Nota_media", "alumnos2 A where a.alumnos.nota_media()>6 and a.alumnos.unapersona.direc.ciudad='GUADALAJARA'");
        while (Objects.requireNonNull(rs).next()) {
            var nombre = rs.getString(1);
            System.out.println("Nombre del alumno/s de GUADALAJARA con nota media mayor de 6: " + nombre);
        }
        rs = consulta(" a.alumnos.unapersona.nombre Nombre,a.alumnos.nota_media() Nota_media", "alumnos2 A order by a.alumnos.nota_media()  DESC FETCH FIRST 1 ROWS ONLY " );

        while (Objects.requireNonNull(rs).next()) {
            var nombre = rs.getString(1);
            var notaMedia = rs.getFloat(2);
            System.out.println("Nombre del alumno con mayor nota media: " + nombre+ " nota media: " + notaMedia);
        }

        rs = consulta(" a.alumnos.unapersona.nombre Nombre,a.alumnos.nota_alta() ", "alumnos2 A order by a.alumnos.nota_alta()  DESC FETCH FIRST 1 ROWS ONLY");
        while (Objects.requireNonNull(rs).next()) {
            var nombre = rs.getString(1);
            var nota = rs.getFloat(2);
            System.out.println("Nombre del alumno con mayor nota: " + nombre + " nota mas alta: " + nota);
        }
    }
}
