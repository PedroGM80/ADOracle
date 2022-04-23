
import java.sql.Array;
import java.sql.SQLException;
import java.util.Objects;

/*
Crea un VARRAY de 5 elementos de tipo PERSONA.
create or replace type array_personas is varray(5) of persona;

Crea después la tabla GRUPOS, con dos columnas: la primera contiene el nombre de grupo
de tipo VARCHAR2(15) y la segunda es del tipo definido anteriormente.

  create  table grupos(
  nombre VARCHAR2(15),
  personas ARRAY_PERSONAS
  )

 Desde java, realiza los siguientes apartados:

a) Introduce la siguiente información en la tabla GRUPOS.

El grupo “INFORMÁTICA” tendrá las siguientes personas:
 */
public class E4 {

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

        // E3.insertar("grupos", " 'INFORMÁTICA' ,ARRAY_PERSONAS(" + p1 + "," + p2 + "," + p3 + "," + p4 + "," + p5 + ")");
        // E3.insertar("grupos", " 'ADMINISTRACIÓN' ,ARRAY_PERSONAS(" + pa1 + "," + pa2 + "," + pa3 + "," + pa4 + "," + null + ")");
        //   E3.insertar("grupos", " 'DIRECCIÓN' ,ARRAY_PERSONAS(" + pd1 + "," + pd2 + "," + null + "," + null + "," + null + ")");
        var resultado = E3.consulta("personas", "grupos where grupos.nombre='INFORMÁTICA'");
        System.out.println("Personas en el grupo INFORMÁTICA");
        while (Objects.requireNonNull(resultado).next()) {
            Array objeto = (Array) resultado.getObject(1);
            Object[] personas = (Object[]) objeto.getArray();
            if (personas.length == 0)
                System.out.printf("NO TIENE NINGUNA PERSONA EN EL ARRAY \n");
            else {
                for (int i = 0; i < personas.length; i++) {
                    oracle.sql.STRUCT structPersona = (oracle.sql.STRUCT) personas[i];
                    Object[] personaAttributes = structPersona.getAttributes();
                    var id = (String) personaAttributes[0].toString();
                    String name = (String) personaAttributes[1];
                    var direction = (oracle.sql.STRUCT) personaAttributes[2];
                    var direcValues = direction.getAttributes();
                    var fecha = (String) personaAttributes[3].toString();
                    System.out.println(id + " " + name + " " + direcValues[0] + " " + direcValues[1] + " " + direcValues[2] + " " + fecha);
                }
            }
        }
        var resultado2 = E3.consulta("personas,nombre", "grupos ");
        while (Objects.requireNonNull(resultado2).next()) {
            System.out.println(resultado2.getString(2));
            Array objeto = (Array) resultado2.getObject(1);
            Object[] personas = (Object[]) objeto.getArray();
            if (personas.length == 0)
                System.out.printf("NO TIENE NINGUNA PERSONA EN EL ARRAY \n");
            else {
                for (int i = 0; i < personas.length; i++) {
                    if (personas[i] != null) {
                        oracle.sql.STRUCT structPersona = (oracle.sql.STRUCT) personas[i];
                        Object[] personaAttributes = structPersona.getAttributes();
                        var id = (String) personaAttributes[0].toString();
                        String name = (String) personaAttributes[1];
                        var direction = (oracle.sql.STRUCT) personaAttributes[2];
                        var direcValues = direction.getAttributes();
                        var fecha = (String) personaAttributes[3].toString();

                        System.out.println(id + " " + name + " " + direcValues[0] + " " + direcValues[1] + " " + direcValues[2] + " " + fecha);
                    }
                }
            }
        }
        String pd3 = "PERSONA( 15, 'Marta', Direccion('c/ Duero','Sevilla',15002),'20/10/1981')";

        E3.actualizar("PERSONAS= ARRAY_PERSONAS(" + pd1 + "," + pd2 + "," + pd3 + ") where grupos.nombre='DIRECCIÓN'", "grupos");


        //d) un for con java
        E3.actualizar("PERSONAS= ARRAY_PERSONAS(" + pd1 + "," + pd2 + ") where grupos.nombre='ADMINISTRACIÓN'", "grupos");
        E3.borrar("grupos", "grupos.nombre='INFORMÁTICA'");
        /*
c) Actualiza el grupo 'DIRECCIÓN' añadiendo una nueva persona.
d) Actualiza el grupo 'ADMINISTRACIÓN' eliminando la persona con código 8.
e) Borra el grupo 'INFORMÁTICA'.
*/

    }
}