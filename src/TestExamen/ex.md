1. Crea el tipo objeto COCHE con 5 argumentos: matrícula (VARCHAR2), marca
   (VARCHAR2), modelo (VARCHAR2), año de fabricación (NUMBER) y cilindrada
   (NUMBER).
   (1 punto)
2. Crea un VARRAY de 4 elementos de tipo COCHE.
   (0.5 puntos)
   A continuación crea una tabla denominada PROPIEDAD con dos columnas: un tipo Persona
   (objeto definido en los apuntes) y el tipo COCHE definido anteriormente.
   (0.5 puntos)
---------------------------------------
   Desde Java realiza las siguientes operaciones:
   a) Introduce en la tabla PROPIEDAD la siguiente información:
   Persona 1: 1, “María”, Dirección(‘Avd/ España’,’Sevilla’,10001),’21/02/1978’
   ● "CA56712R","BMW","X1",2014, 321.
   ● "CA35678C","Seat","Córdoba",2010, 98.
   Persona 2: 2, “Ramón”, Dirección(‘c/ Conde’,’Málaga’,12001),’15/07/1980’
   ● "CA4892V","BMW","M3",2016, 254.
   ● "CA13567R","Renault","Laguna",2013, 105.
   Persona 3: 3, “María”, Dirección(‘c/ Huerta’,Cádiz,11010),’19/05/1977’
   ● "SE56754R","BMW","X7",2014, 350.
   ● "MA3452B","Seat","Toledo",2018, 100.
   Persona 4: 4, “Sofía”, Dirección(‘c/ Sena’,’Córdoba’,14001),’17/10/1990’
   ● "CA56712R","Mercedes","CLA",2017, 298.
   Persona 5: 5, “Alberto”, Dirección(‘c/ Conde’,’Cádiz’’,11016),’30/12/1969’
   ● "CA43261S","Audi","A3",2016, 250.
   ● "CA0101S","Audi","A4",2018, 269.
   ● "CA49011S","Audi","A6",2019, 301.
   ● "CA12561S","Audi","A7",2021, 356.
   (1 punto)
   b) Realiza las siguientes consultas:
   i) Muestra toda la información de todos las personas y sus coches contenidas
   en la tabla.
   ii) Muestra el nombre de todas las personas que hayan nacido después del
3. 
iii) Muestra los coches de todas las personas que residan en la ciudad “Cádiz”.
(1 puntos)
c) Elimina el coche con matrícula “MA3452B” de la lista de coches asociados a la
persona 3.
(0.5 puntos)
3. Crea una nueva tabla denominada PROPIEDAD2 que contenga la información de la tabla
   del ejercicio anterior, pero en vez de guardar a los coches en un VARRAY guárdalos en una
   tabla anidada.
   (0.5 puntos)
   Desde Java, realiza los siguientes apartados:
   a) Introduce los datos del indicados en el apartado 2.a en la tabla PROPIEDAD2.
   (1 punto)
   b) Realiza las siguientes consultas:
   i) Muestra toda la información de todos las personas y sus coches contenidas
   en la tabla.
   ii) Muestra toda la información de las personas que tengan un coche de la
   marca “BMW”.
   iii) Muestra el nombre de la persona propietaria y la cilindrada de los coches que
   tenga una cilindrada superior a 300.
   iv) Muestra la dirección de las personas que tienen un coche fabricado antes de
2015.
(1.5 puntos)
c) Actualiza la tabla anidada asociada a la persona 1 para añadir un nuevo coche.
(0.75 puntos)
d) Actualiza la tabla anidada asociada a la persona 4 para eliminar el coche más
antiguo.
(0.75 puntos)
e) Realiza mediante una transacción que el coche que pertenece a una persona pase a
otra. Por ejemplo, puedes hacer que el coche con matrícula "CA12561S" de la
persona 5 pase a la persona 2.
(1 puntos)