
create or replace TYPE  COCHE as object
(
 matricula VARCHAR2(10),
  marca VARCHAR2(10),
  modelo VARCHAR2(10),
   anio_fab NUMBER,
   cilindrada NUMBER
);

create or replace type array_coches is varray(5) of coche;

  create  or replace table propiedad(
  a_persona PERSONA,
  coches array_coches
  )

  1, “María”, Dirección(‘Avd/ España’,’Sevilla’,10001),’21/02/1978’