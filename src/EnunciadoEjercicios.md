### Ejercicio 1. 
Crea un tipo con objeto denominado T ALUMNO, con 4 atributos, uno de tipo
PERSONA y tres que indican las notas de la primera, segunda y tercera evaluación. Crea
dos métodos para este objeto, junto al cuerpo de estos, uno que devuelva la nota más alta y
otro que devuelva la media del alumno.
Después crea un bloque PL/SQL en el que inicialices un objeto de ese tipo y saques por
pantalla el resultado de los dos métodos.

### Ejercicio 2. 
Crea la tabla ALUMNOS2 del tipo T_ALUMNO e inserta objetos en ella. Desde
Java, realiza luego una consulta que visualice:
- El nombre del alumno y la nota media.
- Alumnos de GUADALAJARA con nota media mayor de 6.
- Nombre Alumno con más nota media.
- Nombre del alumno con nota más alta (cualquiera de sus notas).
 
### Ejercicio 3. 
Crea las siguientes tablas y tipos:
``` 
 CREATE TABLE CUENTAS (
  NUMCTA NUMBER(5) primary key,
  DATOS PERSONA,
  SALDO NUMBER(7,2)
  );
``` 


``` 
  CREATE OR REPLACE TYPE T_MOVIM AS OBJECT (
  IMPORTE NUMBER(7,2),
  TIPOMOV CHAR, -- I ingreso, R reintegro
  FECHA DATE
  );
``` 

``` 
  CREATE TABLE MOVIMIENTOS (
  NUMCTA NUMBER(5) REFERENCES CUENTAS,
  MOV T_MOVIM,
  CONSTRAINT PK_MOV PRIMARY KEY(NUMCTA, MOV.FECHA)
  );
``` 

  Y, mediante código de Java, realiza los siguientes ejercicios:
1. Inserta datos en las tablas CUENTAS y MOVIMIENTOS. Asigna el valor 0 al saldo.
2. Realiza una consulta que muestre el nombre de la cuenta, la suma de ingresos y la
   suma de reintegros.
3. Modifica el saldo de la cuenta. Debe contener los ingresos menos los reintegros.
4. Gestiona mediante transacciones que los movimientos se inserten en la tabla por
   parejas, un ingreso, un reintegro.
  ### Ejercicio 4. 
Crea un VARRAY de 5 elementos de tipo PERSONA.