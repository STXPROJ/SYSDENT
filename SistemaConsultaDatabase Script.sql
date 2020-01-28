drop database if exists SistemaConsultorio;
SET GLOBAL log_bin_trust_function_creators = 1;
create database SistemaConsultorio;
use SistemaConsultorio;

create table EstadoCita(
idEstado int not null auto_increment,
Nombre varchar(20) not null,
Constraint pk_EstadoCita primary key(idEstado));

create table Sexo(
idSexo int not null auto_increment,
Letra varchar(1) not null,
Genero varchar(15) not null,
Constraint pk_Sexo primary key(idSexo),
Constraint ck_Sexo check (Letra in('M','F')));

create table EstadoCivil(
idEstado int not null auto_increment,
Nombre varchar(20),
Constraint pk_Estado primary key(idEstado));

create table Cargo(
idCargo int not null auto_increment,
Nombre varchar(20) not null,
Descripcion varchar(40) null,
Constraint pk_Cargo primary key(idCargo));

create table Paciente(
idPaciente int not null AUTO_INCREMENT,
Nombre varchar(30) not null,
idSexo int not null,
FechaIngreso Date not null,
FechaNacimiento Date null,
idEstado int null,
Ocupacion varchar(30) null,
Telefono varchar(13) null,
inactive boolean default false,
Constraint pk_Paciente primary key (idPaciente),
Constraint fk_Sexo_Paciente foreign key(idSexo) references Sexo(idSexo),
constraint fk_estado_paciente foreign key(idEstado) references EstadoCivil(idEstado));

create table historialClinico(
idPaciente int not null auto_increment,
diabetes boolean null,
lesionCongenita boolean null,
hipertension boolean null,
cefalea boolean null,
mareos boolean null,
hemofilia boolean null,
endocarditis boolean null,
hepatitis boolean null,
asma boolean null,
fiebre boolean null,
artritis boolean null,
cancer boolean null,
nervios boolean null,
hemorragia boolean null,
alergiaAnestesia boolean null,
alergiaAlimentos boolean null,
alergiaAntibiotico boolean null,
venerea boolean null,
varices boolean null,
rx boolean null,
tx boolean null,
Constraint pk_Historial primary key(idPaciente),
Constraint fk_historial_paciente foreign key(idPaciente) references
paciente(idPaciente));

create table Empleado(
idEmpleado int not null AUTO_INCREMENT,
Nombre varchar(30) not null,
Apellido varchar(30) not null,
idSexo int not null,
FechaIngreso Date not null,
Cedula varchar(13) not null,
idCargo int null,
Direccion text null,
Telefono varchar(13) null,
Correo varchar(35) null,
Sueldo double null,
inactive boolean default false,
Constraint pk_Empleado primary key(idEmpleado),
Constraint fk_Sexo_Empleado foreign key(idSexo) references Sexo(idSexo),
Constraint pk_Cargo_Empleado foreign key (idCargo) references Cargo(idCargo));

drop table if exists Rol;
create table Rol(
idRol int not null auto_increment,
nombre varchar(20),
descripcion varchar(30),
inactive boolean default false,
Constraint pk_Rol primary key(idRol));


drop table if exists usuario;
create table usuario(
idUsuario int not null auto_increment,
nombre varchar(20),
idEmpleado int not null,
idRol int not null,
password varchar(15),
inactive boolean default false,
Constraint pk_usuario primary key(idUsuario,idEmpleado),
constraint fk_Empleado_usuario foreign key(idEmpleado) references 
Empleado(idEmpleado));



drop table if exists tipoServicio;
create table tipoServicio(
idTipo int not null auto_increment,
nombre varchar(20) not null,
Descripcion varchar(20) null,
inactive boolean default false,
Constraint pk_tipoServicio primary key(idTipo));

insert into tipoServicio(nombre) value('Operatorias');

drop table if exists Servicio;
create table Servicio(
idServicio int not null auto_increment,
idTipo int not null,
nombre varchar (20) not null,
Descripcion text null,
Precio double null,
inactive boolean default false,
Constraint pk_Servicio primary key(idServicio),
Constraint fk_tiposervicio_idTipo foreign key(idTipo) references
tipoServicio(idTipo));


create table AgendarCita(
idCita int not null AUTO_INCREMENT,
FechaCita Date not null,
FechaProgramada Date not null,
HoraCita Time null,
idPaciente int not null,
idEstadoCita int,
idEmpleado int not null,
inactive boolean default false,
Constraint pk_AgendarCita primary key(idCita),
Constraint fk_AgendarCita_idPaciente foreign key (idPaciente) references
Paciente (idPaciente),
Constraint fk_AgendarCita_idEmpleado foreign key (idEmpleado) references 
Empleado (idEmpleado),
Constraint fk_EstadoCita_EstadoCita foreign key (idEstadoCita) references
EstadoCita (idEstado));

Create table Diagnostico(
idDiagnostico int not null AUTO_INCREMENT,
idPaciente int not null,
idUsuario int not null,
FechaDiagnostico date null,
inactive boolean default false,
Constraint pk_Diagnostico primary key (idDiagnostico),
Constraint fk_Diagnostico_idPaciente foreign key(idPaciente) references
Paciente(idPaciente),
Constraint fk_Diagnostico_idUsuario foreign key(idUsuario) references
Usuario(idUsuario));


create table Dentagrama(
idPaciente int not null,
idDiagnostico int not null,
iu8 bool null,
iu7 bool null,
iu6 bool null,
iu5 bool null,
iu4 bool null,
iu3 bool null,
iu2 bool null,
iu1 bool null,
id8 bool null,
id7 bool null,
id6 bool null,
id5 bool null,
id4 bool null,
id3 bool null,
id2 bool null,
id1 bool null,
du8 bool null,
du7 bool null,
du6 bool null,
du5 bool null,
du4 bool null,
du3 bool null,
du2 bool null,
du1 bool null,
dd8 bool null,
dd7 bool null,
dd6 bool null,
dd5 bool null,
dd4 bool null,
dd3 bool null,
dd2 bool null,
dd1 bool null,
Constraint pk_Dentagrama primary key(idPaciente,idDiagnostico),
Constraint fk_Paciente_Dentagrama foreign key(idPaciente) references Paciente(idPaciente),
Constraint fk_Diagnostico_Dentagrama foreign key(idDiagnostico) references Diagnostico(idDiagnostico));


Create table Detalle_Diagnostico(
idDiagnostico int not null,
idServicio int not null,
Cantidad int null,
Descripcion text null,
inactive boolean default false,
constraint pk_Detalle_Diagnostico primary key (idDiagnostico,idServicio),
Constraint fk_DetalleDiagnostico_idDiagnostico foreign key (idDiagnostico) references
Diagnostico(idDiagnostico),
Constraint fk_DetalleDiagnostico_idServicio foreign key (idServicio) references
Servicio(idServicio));


create table Laboratorio(
idLaboratorio int not null auto_increment,
Nombre varchar(30) not null,
Telefono varchar(13) null,
Rnc varchar(11) null,
Contacto varchar(35) null,
Correo varchar(35) null,
Direccion text,
Descripcion varchar(20),
inactive boolean default false,
Constraint pk_Laboratorio primary key(idLaboratorio));


create table orden_Protesis(
idSolicitud int not null auto_increment,
Fecha Date not null,
idPaciente int not null,
idLaboratorio int not null,
Estado boolean default false null,
inactive boolean default false,
Constraint pk_Orden_Protesis primary key(idSolicitud),
constraint fk_Orden_Protesis_idPaciente foreign key (idPaciente) references 
Paciente(idPaciente),
constraint fk_Orden_Protesis_idLaboratorio foreign key (idLaboratorio) references 
Laboratorio(idLaboratorio));


create table Detalle_Protesis(
idSolicitud int not null,
idServicio int not null,
Cantidad int,
Descripcion text null,
inactive boolean default false,
Constraint pk_DetalleProtesis primary key (idSolicitud,idServicio),
Constraint fk_DetalleProtesis_idSolicitud foreign key(idSolicitud) references
orden_Protesis(idSolicitud),
Constraint fk_DetalleProtesis_idServicio foreign key(idServicio) references
Servicio(idServicio));

create table CuentaPorPagar(
idCuenta int not null auto_increment,
idSolicitud int not null,
Monto double null,
Estado varchar(10) null,
Pagado double null,
inactive boolean default false,
Constraint pk_CxP primary key (idCuenta),
Constraint fk_CxP_idSolicitud foreign key (idSolicitud) references
orden_Protesis(idSolicitud));

create table Factura(
idFactura int not null auto_increment,
idPaciente int not null,
Fecha Date,
idUsuario int not null,
Estado boolean default false null,
descuento int null,
inactive boolean default false,
Constraint pk_Factura primary key(idFactura),
Constraint fk_Factura_idPaciente foreign key (idPaciente) references
Paciente(idPaciente),
Constraint fk_Factura_idUsuario foreign key (idUsuario) references
Usuario(idUsuario));

create table Detalle_Factura (
idFactura int not null,
idServicio int not null,
Cantidad int null,
Descripcion text null,
inactive boolean default false,
Constraint pk_DetalleFactura primary key (idFactura,idServicio),
Constraint fk_DetalleFactura_idFactura foreign key (idFactura) references
Factura(idFactura),
Constraint fk_DetalleFactura_idServicio foreign key (idServicio) references
Servicio(idServicio));


create table Tipo_Pago(
idTipo int not null auto_increment,
Tipo varchar(20) not null,
inactive boolean default false,
Constraint pk_TipoPago primary key(idTipo));

Create table Pago_Factura(
idPago int not null auto_increment,
idFactura int not null,
Fecha Date not null,
inactive boolean default false,
Constraint pk_PagoFactura primary key(idpago,idFactura),
Constraint fk_PagoFactura_idFactura foreign key(idFactura) references
Factura(idFactura)
);

create table Detalle_Pagos(
idPago int not null,
idTipo int not null,
Descripcion text null,
monto double null,
inactive boolean default false,
Constraint pk_pagos_Tipos primary key(idPago,idTipo),
Constraint fk_PagoTipos_idPago foreign key(idPago) references
Pago_Factura(idpago),
Constraint fk_PagoTipos_idTipo foreign key(idTipo) references
tipo_Pago(idTipo));


create table CuentaPorCobrar(
idCuenta int not null auto_increment,
idFactura int not null,
Monto double null,
Estado varchar(10),
Pagado double null,
inactive boolean default false,
Constraint pk_CxC primary key(idCuenta),
Constraint fk_CxC_idFactura foreign key(idFactura) references
Factura(idFactura));




insert into Tipo_Pago (Tipo) value ('Efectivo');
insert into Tipo_Pago (Tipo) value ('Banco');
insert into Tipo_Pago (Tipo) value ('Tarjeta');
/******** TRIGGERS DE LAS TABLAS **************************************************************/
-- Trigger Crear Historial After Insert PAciente-------------------------------
delimiter //
drop trigger if exists trgPacienteCreateHistorial //
create trigger trgPacienteCreateHistorial after insert on Paciente
for each row
begin
call insertHistorial(new.idPaciente);
end //
delimiter ;
-- Trigger Cambiar Estado After Insert Pago-----------------------------------------------------------------------
delimiter //
drop trigger if exists trgEstadoFacturaInsert //
create trigger trgEstadoFacturaInsert after insert on Pago_Factura
for each row
begin
if saldoPendiente(idFactura) <= 0 then
update factura set estado  = 1 where new.idFactura = new.idFactura;
end if;
end //
delimiter ;
-- Trigger Cambiar Estado After Update Pago-----------------------------------------------------------------------
delimiter //
drop trigger if exists trgEstadoFacturaUpdate //
create trigger trgEstadoFacturaUpdate after Update on Pago_Factura
for each row
begin
if saldoPendiente(new.idFactura) <= 0 then
update factura set estado  = 1 where idFactura = new.idFactura;
end if;
end //
delimiter ;
/******** PROCEDIMIENTOS PARA INSERTAR VALORES ************************************************/
-- Procedimiento para Insertar Historial Clinico---------------------------------------------
delimiter //
drop procedure if exists insertHistorial //
create procedure insertHistorial(_idPaciente int)
Begin
insert into historialClinico (idPaciente) value(_idPaciente);
end //
delimiter ;
-- Insertat Tipo de Pago--------------------------------------
delimiter //
drop procedure if exists insertTipoPago //
create procedure insertTipoPago(_Tipo varchar(20))
begin
insert into Tipo_Pago(Tipo) values (_Tipo);
end //
delimiter ;
-- insertar usuario----------------------------------------
delimiter //
drop procedure if exists insertUsuario //
create procedure insertUsuario(_nombre varchar(20),_idEmpleado int,_idRol int,_password varchar(15))
begin
insert into usuario(nombre,idEmpleado,idRol,password) values (_nombre,_idEmpleado,_idRol,_password);
end //
delimiter ;
-- Procedimiento inser Rol---------------
delimiter //
drop procedure if exists insertRol //
create procedure insertRol(_nombre varchar(20),_descripcion varchar(30))
begin
insert into rol(nombre,descripcion) values (_nombre,_descripcion);
end //
delimiter ;

-- Procedimiento Tipo Servicio------------------------
delimiter //
drop procedure if exists insertTipoServicio //
create procedure insertTipoServicio(_nombre varchar(20),_descripcion varchar(20))
begin
insert into tipoServicio(nombre,descripcion) values (_nombre,_descripcion);
end //
delimiter ;
-- Procedimiento Crear EstadoCivil-----------------
delimiter //
drop procedure if exists insertEstado //
create procedure insertEstado(_nombre varchar(20))
begin
insert into EstadoCivil (nombre) value (_nombre);
end //
delimiter ;
-- Procedimiento Crear Sexo---------------------------------------
delimiter //
drop procedure if exists insertSexo //
create procedure insertSexo(_letra char,_Genero varchar(15))
begin
insert into Sexo (letra,Genero) values(_letra,_genero);
end //
delimiter ;
-- Procedimiento Crear Cargo -----------------------------------
delimiter //
drop procedure if exists insertCargo //
create procedure insertCargo(_nombre varchar(20),_Descripcion varchar(40))
begin
insert into Cargo(nombre,Descripcion) values(_nombre,_Descripcion);
end //
delimiter ;
-- Procedimiento Crear EstadoCita -----------------------------------
delimiter //
drop procedure if exists insertEstadoCita //
create procedure insertEstadoCita(_nombre varchar(20))
begin
insert into EstadoCita(nombre) value(_nombre);
end //
delimiter ;
-- Procedimiento para crear Paciente---------------------------------------------
delimiter //
drop procedure if exists insertPaciente //
create procedure insertPaciente( _nombre varchar(30),_Sexo int,_Ingreso varchar(10),_Nacimiento varchar(10), _Estado int,_Ocupacion varchar(30),
_Telefono varchar(13))
Begin
insert into Paciente (Nombre,idSexo,FechaIngreso,FechaNacimiento,idEstado,Ocupacion,Telefono) values (_Nombre,_Sexo,str_to_date(_Ingreso, '%d/%m/%y'),str_to_date(_Nacimiento, '%d/%m/%y'),
_Estado,_Ocupacion,_Telefono);
end //
delimiter ; 
-- Procedimiento para crear Empleado---------------------------------------------
delimiter //
drop procedure if exists insertEmpleado //
create procedure insertEmpleado( _nombre varchar(30),_apellido varchar(30),_Sexo int,_Ingreso varchar(10),_Cedula varchar(13), _Cargo int,_Direccion text,
_Telefono varchar(13),_Correo varchar(35),_Sueldo double)
Begin
insert into Empleado (Nombre,Apellido,idSexo,FechaIngreso,Cedula,idCargo,Direccion,Telefono,Correo,Sueldo) values (_Nombre,_Apellido,_Sexo,str_to_date(_Ingreso, '%d/%m/%y'),_Cedula,
_Cargo,_Direccion,_Telefono,_Correo,_Sueldo);
end //
delimiter ; 
-- Procedimiento para crear Servicio--------------------------------------------------
delimiter //
drop procedure if exists insertServicio //
create procedure insertServicio(_idTipo int, _nombre varchar(30),_Descripcion varchar(20),_Precio double)
Begin
insert into Servicio (idtipo,nombre,Descripcion,Precio) values (_idTipo,_Nombre,_Descripcion,_Precio);
end //
delimiter ; 
-- Procedimiento Para Agendar Cita--------------------------
delimiter //
drop procedure if exists AgendaCita //
create procedure AgendaCita(_Programada Date,_hora varchar(10),_Paciente int,_Estado int,_Empleado int)
Begin
insert into AgendarCita(FechaCita,FechaProgramada,HoraCita,idPaciente,idEstadoCita,idEmpleado) values (curdate(),date_format(_Programada, '%d/%m/%y'),
 str_to_date(_Hora, '%h:%i %p'),_Paciente,_Estado,_Empleado);
end //
delimiter ;
-- Procedimiento de Realizar Diagnostico --------------------------------
delimiter //
drop procedure if exists RealizarDiagnostico //
create procedure RealizarDiagnostico(_Paciente int,_Usuario int)
begin
insert into Diagnostico(idPaciente,idUsuario,FechaDiagnostico) values(_Paciente,_Usuario,curdate());
end //
delimiter ;
-- Proceso de Insertar Detalle de Diagnostico---------------------------
delimiter //
drop procedure if exists insertDetalle_Diagnostico //
create procedure insertDetalle_Diagnostico(_Diagnostico int,_Servicio int,_Cantidad int,_Descripcion text)
begin
insert into Detalle_Diagnostico(idDiagnostico,idServicio,Cantidad,Descripcion) values(_Diagnostico,_Servicio,_Cantidad,_Descripcion);
end //
delimiter ;
-- Procesos Insertar Laboratorio---------------------
delimiter //
drop procedure if exists insertLaboratorio //
create procedure insertLaboratorio(_nombre varchar(30),_Telefono varchar(13),_rnc varchar(11),_contacto varchar(35),_correo varchar(35),_Direccion text,_Descripcion varchar(25))
Begin
insert into Laboratorio (Nombre,Telefono,Rnc,Contacto,Correo,Direccion,Descripcion) values (_nombre,_Telefono,_rnc,_contacto,_correo,_Direccion,_Descripcion);
end //
delimiter ; 
-- Proceso Solicitar Protesis------------------------------------------------
delimiter //
drop procedure if exists SolicitarProtesis //
create procedure SolicitarProtesis(_Paciente int,_Laboratorio int)
Begin
insert into orden_Protesis(Fecha,idPaciente,idLaboratorio) values (curdate(),_Paciente,_Laboratorio);
end //
delimiter ;
-- Proceso insertar Detalle_Proteis----------------------
delimiter //
drop procedure if exists insertDetalle_Protesis //
create procedure insertDetalle_Protesis(_Solicitud int,_Servicio int,_Cantidad int,_Descripcion text)
begin
insert into Detalle_Protesis(idSolicitud,idServicio,Cantidad,Descripcion) values(_Solicitud,_Servicio,_Cantidad,_Descripcion);
end //
delimiter ;
-- Proceso insertar Cuenta por Pagar--------------------------------------------------------
delimiter // 
drop procedure if exists insertCXP //
create procedure insertCXP(_Solicitud int,_Monto double,_Estado varchar(10),_Pagado double)
begin
insert into CuentaPorPagar(idSolicitud,Monto,Estado,Pagado) values(_Solicitud,_Monto,_Estado,_Pagado);
end //
delimiter ;
-- Proceso Realizar Factura----------------------------------
delimiter //
drop procedure if exists RealizarFactura //
create procedure RealizarFactura(_Paciente int,_Usuario int,_descuento int)
begin
insert into Factura(idPaciente,Fecha,idUsuario,descuento) values(_Paciente,curdate(),_Usuario,_descuento);
end //
delimiter ;
-- Proceso insertarDetalle de Factura---------------------------------------
delimiter //
drop procedure if exists insertDetalle_Factura //
create procedure insertDetalle_Factura(_Factura int,_Servicio int,_Cantidad int,_Descripcion text)
begin
insert into Detalle_Factura(idFactura,idServicio,Cantidad,Descripcion) values(_Factura,_Servicio,_Cantidad,_Descripcion);
end //
delimiter ;
-- Proceso RealizarPago --------------------------
delimiter //
drop procedure if exists RealizarPago //
create procedure RealizarPago(_Factura int)
begin
insert into Pago_Factura(idFactura,Fecha) values(_Factura,curdate());
end //
delimiter ;
-- Proceso insertar Detalles Pago --------------------------
delimiter //
drop procedure if exists insertDetalle_Pagos //
create procedure insertDetalle_Pagos(_Pago int,_Tipo int,_Descripcion text,_Monto double)
begin

insert into Detalle_Pagos(idPago,idTipo,Descripcion,Monto) values(_Pago,_Tipo,_Descripcion,_Monto);
end //
delimiter ;
-- Proceso insertar Cuenta por Cobrar--------------------------------------------------------
delimiter // 
drop procedure if exists insertCXC //
create procedure insertCXC(_Factura int,_Monto double,_Estado varchar(10),_Pagado double)
begin
insert into CuentaPorCobrar(idFactura,Monto,Estado,Pagado) values(_Factura,_Monto,_Estado,_Pagado);
end //
delimiter ; 
-- Proceso para insertar Dentagrama-----------------------------------
delimiter //
drop procedure if exists insertDentagrama //
create procedure insertDentagrama(_idPaciente int,_idDiagnostico int,_iu8 bool,_iu7 bool,_iu6 bool,_iu5 bool,_iu4 bool,_iu3 bool,_iu2 bool,_iu1 bool,
_id8 bool,_id7 bool,_id6 bool,_id5 bool,_id4 bool,_id3 bool,_id2 bool,_id1 bool,
_du8 bool,_du7 bool,_du6 bool,_du5 bool,_du4 bool,_du3 bool,_du2 bool,_du1 bool,
_dd8 bool,_dd7 bool,_dd6 bool,_dd5 bool,_dd4 bool,_dd3 bool,_dd2 bool,_dd1 bool)
begin
insert into Dentagrama(idPaciente,idDiagnostico,iu8,iu7,iu6,iu5,iu4,iu3,iu2,iu1,id8,id7,id6,id5,id4,id3,id2,id1,du8,du7,du6,du5,du4,du3,du2,du1,
dd8,dd7,dd6,dd5,dd4,dd3,dd2,dd1) values(_idPaciente,_idDiagnostico,_iu8,_iu7,_iu6,_iu5,_iu4,_iu3,_iu2,_iu1,_id8,_id7,_id6,_id5,_id4,_id3,_id2,_id1,
_du8,_du7,_du6,_du5,_du4,_du3,_du2,_du1,_dd8,_dd7,_dd6,_dd5,_dd4,_dd3,_dd2,_dd1);
end //
delimiter ;
/******************************* PROCEDIMIENTOS PARA ACTUALIZAR TABLAS ****************************************************/
-- Procedimiento para Actualizar Historial Clinico---------------------------------------------
delimiter //
drop procedure if exists updateHistorial //
create procedure updateHistorial(_idPaciente boolean,_diabetes boolean,_lesionCongenita boolean,_hipertension boolean,_cefalea boolean,
_mareos boolean,_hemofilia boolean,_endocarditis boolean,_hepatitis boolean,_asma boolean,_fiebre boolean,_artritis boolean,
_cancer boolean,_nervios boolean,_hemorragia boolean,_alergiaAnestesia boolean,_alergiaAlimentos boolean,_alergiaAntibiotico boolean,
_venerea boolean,_varices boolean,_rx boolean,_tx boolean)
Begin
update historialClinico set diabetes = _diabetes,lesionCongenita = _lesionCongenita,hipertension = _hipertension,cefalea = _cefalea,
mareos = _mareos,hemofilia = _hemofilia,endocarditis = _endocarditis,hepatitis = _hepatitis,asma = _asma,fiebre = _fiebre,
artritis = _artritis,cancer = _cancer,nervios = _nervios,hemorragia = _hemorragia,alergiaAnestesia = _alergiaAnestesia,
alergiaAlimentos = _alergiaAlimentos,alergiaAntibiotico = _alergiaAntibiotico,venerea = _venerea,varices = _varices,rx = _rx, tx = _tx where 
idPaciente = _idPaciente;
end //
delimiter ; 
-- Procesor para eliminar datos con parametro----------------------------------------------------------------
delimiter //
drop procedure if exists DeleteFromTable //
create procedure DeleteFromTable(_tabla varchar(20),_idData int)
begin
Update Factura set inactive = 1 where idFactura = _idData;
end //
delimiter ;
call insertEstadoCita('Pendiente');
call insertEstadoCita('Confirmado');
call insertRol('Vista','vista solamente');
call insertEstado('Soltero');
call insertEstado('Casado');
call insertEstado('Divorciado');
call insertSexo('M','Masculino');
call insertSexo('F','Feminino');
call insertCargo('Doctor','');
call insertCargo('Asistente','');
call insertServicio(1,'Profilaxis','Limpieza Dental',750);
call insertPaciente('Freddy',1,'24/05/00','24/05/99',1,'Obrero','809-532-5058');
call insertEmpleado('Esmirna','Acosta',2,'24/05/05','402-0041396-7',1,'Calle Cefiro #3','809-532-5058','freddy.sotof19@gmail.com',45000);
call insertUsuario('lmedina',1,1,'123');
call AgendaCita('30/03/19','08:30 AM',1,1,1);
call RealizarDiagnostico(1,1);
call insertDetalle_Diagnostico(1,1,5,'nada');
call insertLaboratorio('DentalDom','809-544-5648','1-0182297-1','Fulano de Tal','fulano@hormail.com','Alcarrizos','General');
call SolicitarProtesis(1,1);
call insertDetalle_Protesis(1,1,5,'nada');
call insertCXP(1,1500,'Pendiente',1000);
call RealizarFactura(1,1,15);
call insertDetalle_Factura(1,1,5,'nada');

call insertCXC(1,500,'Pendiente',120);
call insertDentagrama(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1);


-- Funcion Total Factura-----------------------
delimiter //
drop function if exists totalFactura //
create function totalFactura(_idFactura int) returns double
begin
declare _total double;
select (round(sum(s.precio * df.cantidad) - sum((s.precio * df.cantidad) *(f.descuento / 100)))) into _total
from Factura f inner join Detalle_Factura df on f.idFactura = df.idFactura 
inner join Servicio s on df.idServicio = s.idServicio
where f.inactive = false and f.idFactura = _idFactura;
RETURN _total;
end //
delimiter ;
-- Funcion Total PAgo-----------------------
delimiter //
drop function if exists totalPago //
create function totalPago(_idFactura int) returns double
begin
declare _total double;
select sum(dp.monto) into _total from Pago_Factura p inner join 
Detalle_Pagos dp on p.idPago = dp.idPago inner join Factura f on 
p.idFactura = f.idFactura where p.inactive = false and f.idFactura = _idFactura;
if _total is null then
return 0;
else  
RETURN _total;
end if;
end //
delimiter ;
-- Funcion SaldoPendiente-----------------------
delimiter //
drop function if exists saldoPendiente //
create function saldoPendiente(_idFactura int) returns double
begin
declare _factura double;
declare _pago double;
declare _total double;
select totalFactura(_idFactura) into _factura;
select totalPago(_idFactura) into _pago;
return _factura - _pago;
end //
delimiter ;
-- Balance Paciente --------------------------------------------
delimiter //
drop function if exists balancePaciente //
create function balancePaciente(_idPaciente int) returns double
begin
declare _total double;
select (round(sum(s.precio * df.cantidad) - sum((s.precio * df.cantidad) *(f.descuento / 100)) - totalPago(f.idFactura))) into _total
from Factura f inner join Detalle_Factura df on f.idFactura = df.idFactura 
inner join Servicio s on df.idServicio = s.idServicio
where f.inactive = false and f.idPaciente = _idPaciente;
RETURN _total;
end //
delimiter ;

-- Funcion Estado Factura-----------------------
delimiter //
drop function if exists estadoFactura //
create function estadoFactura(_estado boolean) returns varchar(20)
begin
declare estado varchar(20);
if _estado = true then
set estado = 'Pago';
elseif _estado = false then
set estado = 'Pendiente';
end if;
RETURN estado;
end //
delimiter ;
-- Funcion Estado Solicitud-----------------------
delimiter //
drop function if exists estadoSolicitud //
create function estadoSolicitud(_estado boolean) returns varchar(20)
begin
declare estado varchar(20);
if _estado = true then
set estado = 'Pago';
elseif _estado = false then
set estado = 'Pendiente';
end if;
RETURN estado;
end //
delimiter ;





-- Select Tipo dePAgo---------------
delimiter //
drop procedure if exists selectTipoPago //
create procedure selectTipoPago()
begin
select * from tipo_pago;
end //
delimiter ;
-- Select Estado Cita---------------
delimiter //
drop procedure if exists selectEstadoCita //
create procedure selectEstadoCita()
begin
select * from EstadoCita;
end //
delimiter ;
-- Select ROLES---------------
delimiter //
drop procedure if exists selectRol //
create procedure selectRol()
begin
select * from Rol;
end //
delimiter ;
-- Select Tipo de Servicio---------------------
delimiter //
drop procedure if exists selectTipoServicio //
create procedure selectTipoServicio()
begin
select * from TipoServicio;
end //
delimiter ;
-- Select Sexo--------------------------
delimiter //
drop procedure if exists selectSexo //
create procedure selectSexo()
begin
select * from Sexo;
end //
delimiter ;
-- Select Cargo-----------------------------------------------
delimiter // 
drop procedure if exists selectCargo //
create procedure selectCargo()
begin
select * from cargo;
end //
delimiter ;
-- Select Estado ----------------------------
delimiter //
drop procedure if exists selectEstado //
create procedure selectEstado()
begin
select * from EstadoCivil;
end //
delimiter ;
-- Select Historial ----------------------------
delimiter //
drop procedure if exists selectHistorial //
create procedure selectHistorial(_idPaciente int)
begin
select p.nombre,hc.* from historialClinico hc inner join Paciente p on hc.idPaciente = p.idPaciente where p.idPaciente = _idPaciente;
end //
delimiter ;
-- Select todos los usuarios----------------------------------------
delimiter //
drop procedure if exists selectUsuario //
create procedure selectUsuario()
begin
select u.idUsuario,u.nombre, e.idEmpleado,concat(e.Nombre , ' ' , e.apellido),r.idRol,r.nombre,u.password from Usuario u inner join Empleado e on u.idEmpleado = e.idEmpleado
inner join Rol r on u.idRol = r.idRol where u.inactive = false;
end //
delimiter ;
-- Select todos los Servicios----------------------------------------
delimiter //
drop procedure if exists selectServicio //
create procedure selectServicio()
begin
select s.idServicio,s.nombre,s.descripcion,s.precio,ts.idTipo,ts.Nombre from servicio s inner join TipoServicio ts on s.idTipo = ts.idTipo
where s.inactive = false;
end //
delimiter ;
-- Select Detalle Diagnostico por id------------------------------
delimiter //
drop procedure if exists selectDetalleDiagnosticoServicio //
create procedure selectDetalleDiagnosticoServicio(_idDiagnostico int)
begin
select dd.idDiagnostico, s.idServicio,s.Nombre,dd.Descripcion,s.precio,ts.idTipo,ts.nombre,dd.cantidad from Detalle_Diagnostico dd
inner join Servicio s on dd.idServicio = s.idServicio inner join TipoServicio ts on s.idTipo = ts.idTipo where
dd.idDiagnostico = _idDiagnostico and dd.inactive = false;
end //
delimiter ;

-- Select Detalle Factura por id---------------------------------
 delimiter //
drop procedure if exists selectDetalleFacturaServicio //
create procedure selectDetalleFacturaServicio(_idFactura int)
begin
select df.idFactura, s.idServicio,s.Nombre,df.Descripcion,ts.idTipo,ts.Nombre,s.Precio,df.cantidad,sum(df.cantidad * s.precio) from Detalle_Factura df
inner join Servicio s on df.idServicio = s.idServicio  inner join TipoServicio ts on s.idTipo = ts.idTipo where
df.idFactura = _idFactura and df.inactive = false;
end //
delimiter ;
-- Select Detalle Protesis por id---------------------------------
 delimiter //
drop procedure if exists selectDetalleProtesisServicio //
create procedure selectDetalleProtesisServicio(_idSolicitud int)
begin
select dp.idSolicitud, s.idServicio,ts.idTipo,ts.nombre,s.Nombre,dp.Descripcion,s.Precio,dp.cantidad from Detalle_Protesis dp
inner join Servicio s on dp.idServicio = s.idServicio  inner join TipoServicio ts on s.idTipo = ts.idTipo where
dp.idSolicitud = _idSolicitud and dp.inactive = false;
end //
delimiter ;
-- Select all Citas---------------------------------
 delimiter //
drop procedure if exists selectAllCitas //
create procedure selectAllCitas()
begin
select c.idCita,date_format(c.FechaCita, '%d/%m/%Y'),c.idPaciente,p.nombre 'Nombre Paciente',date_format(c.FechaProgramada, '%d/%m/%Y'),
date_format(c.HoraCita,'%h:%i %p'),ec.idEstado,ec.nombre,c.idEmpleado,e.nombre 'Nombre Doctor' from agendarcita c 
inner join Paciente p on c.idPaciente = p.idPaciente inner join Empleado e on c.idEmpleado = e.idEmpleado inner join EstadoCita ec on
c.idEstadoCita = ec.idEstado
where c.inactive = false;
end //
delimiter ;
-- Select All Pacientes
delimiter // 
drop procedure if exists selectAllPaciente //
create procedure selectAllPaciente()
begin
select p.idPaciente,p.Nombre,s.idSexo,s.Genero,date_format(p.fechanacimiento,'%d/%m/%Y'), date_format(p.fechaIngreso,'%d/%m/%Y'),e.idEstado,e.Nombre,
p.ocupacion,p.telefono,balancePaciente(p.idPaciente) from paciente p inner join Sexo s on p.idSexo = s.idSexo inner join EstadoCivil e on p.idEstado = e.idEstado
where p.inactive = false;
end //
delimiter ;
-- Select All Empleado --------------------
delimiter // 
drop procedure if exists selectAllEmpleado //
create procedure selectAllEmpleado()
begin
select e.idEmpleado,e.nombre , e.apellido,s.idSexo,s.Letra,date_format(e.fechaIngreso,'%d/%m/%Y'),e.Cedula,c.idCargo,c.nombre,e.Direccion,e.telefono,e.correo,e.sueldo from Empleado e
inner join Sexo s on e.idSexo = s.idSexo inner join Cargo c on e.idCargo = c.idCargo where e.inactive = false;
end //
delimiter ;
-- Select All Diagnostico--------------
delimiter //
drop procedure if exists selectAllDiagnostico //
create procedure selectAllDiagnostico()
begin
select d.idDiagnostico,d.idPaciente, p.nombre 'Nombre Paciente',u.idUsuario,u.nombre,e.idEmpleado,e.nombre,date_format(d.FechaDiagnostico,'%d/%m/%Y') from Diagnostico d inner join Paciente p on 
d.idPaciente = p.idPaciente inner join Usuario u on d.idUsuario = u.idUsuario inner join Empleado e on u.idEmpleado = e.idEmpleado 
where p.inactive = false;
end //
delimiter ; 
-- Select All Laboratorio --------------------
delimiter // 
drop procedure if exists selectAllLaboratorio //
create procedure selectAllLaboratorio()
begin
select * from Laboratorio where inactive = false;
end //
delimiter ;
-- Select Facturas PEndeites-------------
delimiter // 
drop procedure if exists selectFacturaPendiente //
create procedure selectFacturaPendiente ()
begin
select f.idFactura,f.idPaciente,p.Nombre,date_format(f.Fecha,'%d/%m/%Y'),u.idUsuario,u.Nombre,f.estado,f.descuento,totalFactura(f.idFactura)
from Factura f inner join Paciente p on f.idPaciente = p.idPaciente inner join Usuario u on f.idUsuario = u.idUsuario
inner join Detalle_Factura df on f.idFactura = df.idFactura inner join Servicio s on df.idServicio = s.idServicio
where f.inactive = false and f.estado = false;
end //
delimiter ;
-- Select All facturas-------------
delimiter // 
drop procedure if exists selectAllFactura //
create procedure selectAllFactura ()
begin
select f.idFactura,f.idPaciente,p.Nombre,date_format(f.Fecha,'%d/%m/%Y'),u.idUsuario,u.Nombre,f.estado,f.descuento,totalFactura(f.idFactura)
from Factura f inner join Paciente p on f.idPaciente = p.idPaciente inner join Usuario u on f.idUsuario = u.idUsuario
inner join Detalle_Factura df on f.idFactura = df.idFactura inner join Servicio s on df.idServicio = s.idServicio
where f.inactive = false;
end //
delimiter ;
-- Select All cuentas PorPagar
delimiter //
drop procedure if exists selectAllCXP //
create procedure selectAllCXP ()
begin
select c.idCuenta, s.idSolicitud,s.idLaboratorio,l.Nombre 'Nombre Laboratorio',s.idPaciente,p.Nombre 'Nombre Paciente',c.monto,c.Estado,c.Pagado from CuentaPorPagar c
inner join orden_protesis s on c.idSolicitud = s.idSolicitud inner join Laboratorio l on s.idLaboratorio = l.idLaboratorio 
inner join Paciente p on s.idPaciente = p.idPaciente where c.inactive = false;
end //
delimiter ;
-- Select aLl Orden Protesis
delimiter //
drop procedure if exists selectAllSolicitud //
create procedure selectAllSolicitud ()
begin
select s.idSolicitud,date_format(s.Fecha,'%d/%m/%Y'),s.idPaciente,p.nombre 'Nombre de Paciente',s.idLaboratorio,l.Nombre 'Nombre Laboratorio',s.Estado from orden_protesis s inner join
Paciente p on s.idPaciente = p.idPaciente inner join laboratorio l on s.idLaboratorio = l.idLaboratorio where s.inactive = false;
end //
delimiter ;
-- Select all CXP--------------
delimiter // 
drop procedure if exists selectAllCXC //
create procedure selectAllCXC()
begin
select c.idCuenta,f.idFactura,f.idPaciente,p.Nombre 'Nombre Paciente',c.Monto,c.Estado,c.Pagado from cuentaporcobrar c inner join Factura f
on c.idFactura = f.idFactura inner join Paciente p on f.idPaciente = p.idPaciente where c.inactive = false;
end //
delimiter ;
call selectAllCXC();
-- Select All PAgos--------------------
delimiter // 
drop procedure if exists selectAllPagos //
create procedure selectAllPagos()
begin
select p.idPago,f.idFactura,f.idPaciente,pp.Nombre 'Nombre Paciente',date_format(p.Fecha,'%d/%m/%Y'),sum(dp.monto),totalFactura(f.idFactura),
saldoPendiente(f.idFactura) from Pago_Factura p inner join Factura f
on p.idFactura = f.idFactura inner join Paciente pp on f.idPaciente = pp.idPaciente inner join
Detalle_Pagos dp on p.idPago = dp.idPago where p.inactive = false group by f.idFactura,p.idPago;
end //
delimiter ;
-- Select All Detalle Pagos------------------------------------
delimiter //
drop procedure if exists selectAllDetallePagos //
create procedure selectAllDetallePagos(_idPago int)
begin
select dp.idPago,t.idTipo,t.tipo,dp.Descripcion,dp.monto from Detalle_Pagos dp inner join
Tipo_Pago t on dp.idTipo = t.idTipo where dp.idPago = _idPago and dp.inactive = false;
end //
delimiter ;
-- Select Dentagrama--------------------------------------------------------------
delimiter //
drop procedure if exists selectDentagrama //
create procedure selectDentagrama(_idPaciente int,_idDiagnostico int)
begin
select * from Dentagrama where idPaciente = _idPaciente and idDiagnostico = _idDiagnostico;
end //
delimiter ;
call selectDentagrama(1,2);
call selectHistorial(1);
select totalPago(1);
select saldoPendiente(1);
call selectTipoPago();
call selectFacturaPendiente();
call selectALLCXP();
call selectAllDetallePagos(2);
call SelectAllPagos();
call selectAllFactura();
call selectAllCXC();
call selectAllSolicitud();
call selectAllDiagnostico();
call selectAllEmpleado;
call selectAllLaboratorio();
call selectAllPaciente;
call selectServicio();
call selectTipoServicio();
call selectAllCitas;
call selectDetalleDiagnosticoServicio(1);
call selectDetalleFacturaServicio(1);
call selectDetalleProtesisServicio(1);
call selectSexo();
call selectCargo();
call selectEstado();
call selectUsuario();
-- Select Paciente por ID---------------------------------------
delimiter // 
drop procedure if exists selectidPaciente //
create procedure selectidPaciente(_idPaciente int)
begin
select p.idPaciente,p.Nombre,s.idSexo,s.Genero,date_format(p.fechaIngreso,'%d/%m/%Y'), date_format(p.fechanacimiento,'%d/%m/%Y'),e.idEstado,e.Nombre,
p.ocupacion,p.telefono,balancePaciente(p.idPaciente) from paciente p inner join Sexo s on p.idSexo = s.idSexo inner join EstadoCivil e on p.idEstado = e.idEstado 
where idPaciente = _idPaciente and p.inactive = false;

end //
delimiter ;
-- Select Empleado por ID---------------------------------------
delimiter // 
drop procedure if exists selectidEmpleado //
create procedure selectidEmpleado(_idEmpleado int)
begin
select e.idEmpleado,e.Nombre,e.apellido,s.idSexo,s.Genero,date_format(e.fechaIngreso,'%d/%m/%Y'),c.idCargo,c.nombre, e.direccion,e.telefono,
e.correo,e.sueldo from empleado e inner join Sexo s on e.idSexo = s.idSexo inner join Cargo c on e.idCargo = c.idCargo 
where idEmpleado = _idEmpleado and e.inactive = false;

end //
delimiter ;
-- Select Laboratorio por ID---------------------------------------
delimiter // 
drop procedure if exists selectidLaboratorio //
create procedure selectidLaboratorio(_idLaboratorio int)
begin
select l.idLaboratorio,l.Nombre,l.telefono,l.rnc,l.contacto,l.correo,l.direccion,l.descripcion from laboratorio l
where l.idLaboratorio = _idLaboratorio and l.inactive = false;

end //
delimiter ;
-- Select Diagnostico por ID------------------------------------------
delimiter //
drop procedure if exists selectidDiagnostico //
create procedure selectidDiagnostico(_idDiagnostico int)
begin
select d.idDiagnostico,d.idPaciente, p.nombre 'Nombre Paciente',u.idUsuario,u.nombre,e.idEmpleado,e.nombre,
date_format(d.FechaDiagnostico,'%d/%m/%y') from Diagnostico d inner join Paciente p on 
d.idPaciente = p.idPaciente  inner join Usuario u on d.idUsuario = u.idUsuario inner join Empleado e on u.idEmpleado = e.idEmpleado 
where p.inactive = false and d.idDiagnostico = _idDiagnostico;
end //
delimiter ; 
-- Select Factura por ID------------------------------------------
delimiter //
drop procedure if exists selectidFactura //
create procedure selectidFactura(_idFactura int)
begin
select f.idFactura,f.idPaciente,p.Nombre,date_format(f.Fecha,'%d/%m/%Y'),u.idUsuario,u.Nombre,f.estado,f.descuento,
totalFactura(_idFactura),totalPago(_idFactura),saldoPendiente(f.idFactura)
from Factura f inner join Paciente p on f.idPaciente = p.idPaciente inner join Usuario u on f.idUsuario = u.idUsuario
inner join Detalle_Factura df on f.idFactura = df.idFactura inner join Servicio s on df.idServicio = s.idServicio left join
Pago_Factura pf on f.idFactura = pf.idFactura
where f.inactive = false and f.idFactura = _idFactura;
end //
delimiter ; 
-- Select Usuario por IDs----------------------------------------
delimiter //
drop procedure if exists selectidUsuario //
create procedure selectidUsuario(_Nombre varchar(20))
begin
select u.idUsuario,u.nombre, e.idEmpleado,concat(e.Nombre , ' ' , e.apellido),r.idRol,r.nombre,u.password from Usuario u inner join Empleado e on u.idEmpleado = e.idEmpleado
inner join Rol r on u.idRol = r.idRol where u.inactive = false and u.nombre = _Nombre;
end //
delimiter ;

call selectidUsuario('lmedina');
call selectidFactura(8);
call selectidDiagnostico(1);
call selectidPaciente(1);
call selectidLaboratorio(1);
-- Llenar ComboBox Empleado ---------------------
delimiter // 
drop procedure if exists cbbEmpleado //
create procedure cbbEmpleado()
begin
select e.idEmpleado,e.Nombre from empleado e inner join Cargo c on e.idCargo = c.idCargo
 where c.nombre = 'Doctor' and e.inactive = false;
end //
delimiter ;


-- Funcion Top ID Diagnostico---------------------
delimiter //
drop function if exists topIDDiagnostico //
create function topIDDiagnostico() returns int
begin
declare _id int;
select max(idDiagnostico) into _id from diagnostico;
RETURN _id;
end //
delimiter ;
-- Funcion Top ID Factura---------------------
delimiter //
drop function if exists topIDFactura //
create function topIDFactura() returns int
begin
declare _id int;
select max(idFactura) into _id from Factura;
RETURN _id;
end //
delimiter ;
-- Funcion Top ID Citas-----------------------------
delimiter //
drop function if exists topIDAgendarCita //
create function topIDAgendarCita() returns int
begin
declare _id int;
select max(idCita) into _id from AgendarCita;
RETURN _id;
end //
delimiter ;
-- Funcion Top ID Solicitud---------------------
delimiter //
drop function if exists topIDSolicitud //
create function topIDSolicitud() returns int
begin
declare _id int;
select max(idSolicitud) into _id from orden_protesis;
RETURN _id;
end //
delimiter ;
-- Funcion Top ID Pago---------------------
delimiter //
drop function if exists topIDPago //
create function topIDPago() returns int
begin
declare _id int;
select max(idPago) into _id from Pago_Factura;
RETURN _id;
end //
delimiter ;

-- Consulta Filtro -----------------------------------
delimiter // 
drop function if exists filterTable //
create function filterTable(_table varchar(25)) 
returns text
begin
declare _consulta text;
case 
when _table = 'AgendarCita' then
set _consulta ="select c.idCita,date_format(c.FechaCita, '%d/%m/%y'),c.idPaciente,p.nombre ,date_format(c.FechaProgramada, '%d/%m/%y'),
date_format(c.HoraCita,'%h:%i %p'),c.EstadoCita,c.idEmpleado,e.nombre from agendarcita c 
inner join Paciente p on c.idPaciente = p.idPaciente inner join Empleado e on c.idEmpleado = e.idEmpleado
where c.inactive = false and";
when _table = 'Usuario' then
set _consulta ="select u.idUsuario,u.nombre, e.idEmpleado,concat(e.Nombre , ' ' , e.apellido),r.idRol,r.nombre,u.password from Usuario u inner join Empleado e on u.idEmpleado = e.idEmpleado
inner join Rol r on u.idRol = r.idRol where u.inactive = false and";
when _table = 'Servicio' then
set _consulta ="select s.idServicio,s.nombre,s.descripcion,s.precio,ts.idTipo,ts.Nombre from servicio s inner join TipoServicio ts on s.idTipo = ts.idTipo
where s.inactive = false and";
when _table = 'Detalle_Diagnostico' then
set _consulta ="select dd.idDiagnostico, s.idServicio,s.Nombre,s.Descripcion,ts.idTipo,ts.nombre,dd.cantidad from Detalle_Diagnostico dd
inner join Servicio s on dd.idServicio = s.idServicio inner join TipoServicio ts on s.idTipo = ts.idTipo where
dd.idDiagnostico = _idDiagnostico and dd.inactive = false and";
when _table = 'Detalle_Factura' then
set _consulta ="select df.idFactura, s.idServicio,s.Nombre,s.Descripcion,ts.idTipo,ts.Nombre,s.Precio,df.cantidad from Detalle_Factura df
inner join Servicio s on df.idServicio = s.idServicio  inner join TipoServicio ts on s.idTipo = ts.idTipo where
df.idFactura = _idFactura and df.inactive = false and";
when _table = 'Detalle_Protesis' then 
set _consulta ="select dp.idSolicitud, s.idServicio,ts.idTipo,ts.nombre,s.Nombre,s.Descripcion,s.Precio,dp.cantidad from Detalle_Protesis dp
inner join Servicio s on dp.idServicio = s.idServicio  inner join TipoServicio ts on s.idTipo = ts.idTipo where
dp.idSolicitud = _idSolicitud and dp.inactive = false and";
when _table = 'Paciente' then
set _consulta ="select p.idPaciente,p.Nombre,s.idSexo,s.Genero,date_format(p.fechaIngreso,'%d/%m/%y'), date_format(p.fechaIngreso,'%d/%m/%y'),e.idEstado,e.Nombre,
p.ocupacion,p.telefono from paciente p inner join Sexo s on p.idSexo = s.idSexo inner join EstadoCivil e on p.idEstado = e.idEstado
where p.inactive = false and";
when _table = 'Empleado' then
set _consulta ="select e.idEmpleado,e.nombre , e.apellido,s.idSexo,s.Genero,date_format(e.fechaIngreso,'%d/%m/%y'),e.Cedula,c.idCargo,c.nombre,e.Direccion,
e.telefono,e.correo,e.sueldo from Empleado e inner join Sexo s on e.idSexo = s.idSexo inner join Cargo c on e.idCargo = c.idCargo 
where e.inactive = false and";
when _table = 'Diagnostico' then 
set _consulta ="select d.idDiagnostico,d.idPaciente, p.nombre 'Nombre Paciente',date_format(d.FechaDiagnostico,'%d/%m/%y') from Diagnostico d inner join Paciente p on 
d.idPaciente = p.idPaciente where p.inactive = false and";
when _table = 'Laboratorio' then
set _consulta ="select * from Laboratorio where inactive = false and _campo = _param;
when _table = 'Factura' then
select f.idFactura,f.idPaciente,p.Nombre 'Nombre Paciente',f.Fecha,f.idEmpleado,e.Nombre 'Nombre Empleado',f.estado from Factura f inner join Paciente p on f.idPaciente = p.idPaciente
inner join Empleado e on f.idEmpleado = f.idEmpleado where f.inactive = false and";
when _table = 'CXP' then
set _consulta ="select c.idCuenta, s.idSolicitud,s.idLaboratorio,l.Nombre 'Nombre Laboratorio',s.idPaciente,p.Nombre 'Nombre Paciente',c.monto,c.Estado,c.Pagado from CuentaPorPagar c
inner join orden_protesis s on c.idSolicitud = s.idSolicitud inner join Laboratorio l on s.idLaboratorio = l.idLaboratorio 
inner join Paciente p on s.idPaciente = p.idPaciente where c.inactive = false and";
when _table = 'orden_protesis' then
set _consulta ="select s.idSolicitud,s.fecha,s.idPaciente,p.nombre 'Nombre de Paciente',s.idLaboratorio,l.Nombre 'Nombre Laboratorio',s.Estado from orden_protesis s inner join
Paciente p on s.idPaciente = p.idPaciente inner join laboratorio l on s.idLaboratorio = l.idLaboratorio where s.inactive = false and";
when _table = 'CXC' then
set _consulta ="select c.idCuenta,f.idFactura,f.idPaciente,p.Nombre 'Nombre Paciente',c.Monto,c.Estado,c.Pagado from cuentaporcobrar c inner join Factura f
on c.idFactura = f.idFactura inner join Paciente p on f.idPaciente = p.idPaciente where c.inactive = false and";
when _table = 'Pagos' then
set _consulta ="select p.idPago,f.idFactura,f.idPaciente,pp.Nombre 'Nombre Paciente',p.Fecha from Pago_Factura p inner join Factura f
on p.idFactura = f.idFactura inner join Paciente pp on f.idPaciente = pp.idPaciente where p.inactive = false and";
when _table = 'Detalle_Pago' then
set _consulta ="select dp.idDetalle,dp.idPago,t.tipo,dp.Banco,dp.numeroCK,dp.monto from Detalle_Pagos dp inner join
Tipo_Pago t on dp.idTipo = t.idTipo where dp.idPago = _idPago and dp.inactive = false and ";
end case;
return _consulta;
end //
delimiter ;
select filterTable('AgendarCita');

call cbbEmpleado();
select LPAD(topIDAgendarCita(),7,'0');
select LPAD(topIDDiagnostico(),7,'0');
select LPAD(topIDSolicitud(),7,'0');
select topIDFactura();
select topIDPago();
SET GLOBAL log_bin_trust_function_creators = 1;
select * from sexo;
select * from factura;
select * from detalle_Factura;
select topIDFactura();
