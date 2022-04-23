import java.sql.SQLException;
import java.util.Objects;

public class E5 {
    /*
    Crea la tabla GRUPOS2 del ejercicio anterior, pero en vez de guardar a las
personas en un VARRAY guárdala en una tabla anidada.

    CREATE TYPE personas_anidadas AS TABLE OF PERSONA;


- Además ahora la tabla GRUPOS2 tendrá dos atributos más ID, un identificador numérico, que será la clave primaria, y
Fecha_Creación, un DATE.

    CREATE TABLE GRUPOS2 (
          id number(2) PRIMARY KEY,
          Fecha_Creación DATE,
          nombre VARCHAR2(15),
          personas personas_anidadas
    ) NESTED TABLE   personas  STORE AS per_anidada2;

- El grupo INFORMÁTICA tendrá ID 1 y fecha de creación
21/01/2020, el grupo ADMINISTRACIÓN tendrá ID 2 y fecha de creación 2/02/2020 y el
grupo DIRECCIÓN tendrá ID 1 y fecha de creación 10/03/2020.
     */

    public static void main(String[] args) throws SQLException {
        String p1 = "PERSONA(1, 'Juan',Direccion('c/ Conde','Málaga',12001),'17/02/1990')";
        String p2 = "PERSONA(2, 'María', Direccion('c/ Amiel','Córdoba',16001),'21/10/1991')";
        String p3 = "PERSONA(3, 'Sofía', Direccion('c/ Castillo','Cádiz',15001),'1/09/1992')";
        String p4 = "PERSONA( 4, 'Carla', Direccion('c/ Sin nombre','Cádiz',11001),'14/08/1990')";
        String p5 = "PERSONA(5,'Manuel', Direccion('c/ Cuba','Málaga',10001),'27/02/1989')";

        String pa1 = "PERSONA( 6, 'Lucas', Direccion('c/ España','Granada',11001),'29/01/1988')";
        String pa2 = "PERSONA( 7, 'Marta', Direccion('c/ Conde','Málaga',12001),'30/07/1986')";
        String pa3 = "PERSONA( 8, 'Carmen', Direccion('c/ Mimbre','Granada',11001),'1/04/1990')";
        String pa4 = "PERSONA( 9, 'Milagros', Direccion('c/ Segura','Sevilla',16001),'7/01/1994')";

        String pd1 = "PERSONA( 10, 'José Miguel', Direccion('c/ Volga','Sevilla',15001),'5/12/1979')";
        String pd2 = "PERSONA( 11, 'Antonia', Direccion('c/ Tajo','Málaga',15001),'17/05/1980')";
/*
        E3.insertar("grupos2 ", "1,'21/01/2020','INFORMÁTICA', personas_anidadas ()");
        E3.insertar("grupos2 ", "2,'2/02/2020','ADMINISTRACIÓN', personas_anidadas ()");
        E3.insertar("grupos2 ", "3,'10/03/2020','DIRECCIÓN', personas_anidadas ()");
        String mis_personas_informatica[] = new String[]{p1, p2, p3, p4, p5};
        String mis_personas_administracion[] = new String[]{pa1, pa2, pa3, pa4};
        String mis_personas_direccion[] = new String[]{pd1, pd2};
        for (int i = 0; i < mis_personas_informatica.length; i++) {
            System.out.println(mis_personas_informatica[i]);
            E3.insertarTablaAnidada(" ( SELECT personas FROM  grupos2 where id=1)", " ( " + mis_personas_informatica[i] + " )");
        }
        for (int i = 0; i < mis_personas_administracion.length; i++) {
            System.out.println(mis_personas_administracion[i]);
            E3.insertarTablaAnidada(" ( SELECT personas FROM  grupos2 where id=1)", " ( " + mis_personas_administracion[i] + " )");
        }
        for (int i = 0; i < mis_personas_direccion.length; i++) {
            System.out.println(mis_personas_direccion[i]);
            E3.insertarTablaAnidada(" ( SELECT personas FROM  grupos2 where id=1)", " ( " + mis_personas_direccion[i] + " )");
        }
*/
     var rs=   E3.consulta("*", "grupos2, TABLE(personas) per where per.direc.ciudad ='Málaga'");

        while (Objects.requireNonNull(rs).next()) {
            var nombre = rs.getString(1);
            var notaMedia = rs.getFloat(2);
            System.out.println(nombre + " Nota media: " + notaMedia);
        }
     /*

#### Desde Java, realiza los siguientes apartados:
____________________________________________________________
a) Introduce los datos en la tabla GRUPOS2.
b) Realiza las siguientes consultas:

    i) Muestra toda la información de los grupos en los que haya alguna persona
    que resida en Málaga.

    ii) Muestras el grupo al que pertenecen y el nombre de las personas que han
    nacido antes de 1990.

    iii) Muestra la dirección de los empleados que pertenecen al grupo DIRECCIÓN.
    iv) Muestra el nombre y la fecha de nacimiento de las personas que pertenezcan
    a un grupo creado antes de marzo de 2020.


c) Actualiza la tabla anidada de personas asociadas al grupo “DIRECCIÓN” para añadir
una nueva persona.

d) Actualiza la tabla anidada de personas asociadas al grupo “DIRECCIÓN” para
eliminar a la persona con código 8.

e) Modifica la fecha de creación del grupo con id 2 para que sea ‘20/05/2021’.

f) Borra el grupo INFORMÁTICA
         */
    }
}
