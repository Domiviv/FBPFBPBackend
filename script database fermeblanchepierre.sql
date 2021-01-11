# CREATE DATABASE
drop database if exists fermeblanchepierre;
create database fermeblanchepierre;
use fermeblanchepierre;


# CREATE TABLES
drop table if exists Roles;
create table Roles(
	idRole int auto_increment,
	label varchar(50),
    constraint PK_ROLES primary key(idRole)
)DEFAULT CHARSET=utf8;

drop table if exists Allergens;
create table Allergens(
	idAllergen int auto_increment,
    label varchar(100),
    constraint PK_ALLERGENS primary key(idAllergen)
)DEFAULT CHARSET=utf8;

drop table if exists OrderStatus;
create table OrderStatus(
	idStatus int auto_increment,
    label varchar(50),
    constraint PK_ORDERSTATUS primary key(idStatus)
)DEFAULT CHARSET=utf8;

drop table if exists Categories;
create table Categories(
	idCategory int auto_increment,
    label varchar(100),
    constraint PK_CATEGORIES primary key(idCategory)
)DEFAULT CHARSET=utf8;

drop table if exists Measures;
create table Measures(
idMeasure int auto_increment,
label varchar(100),
unit varchar(10), 
constraint PK_MEASURES primary key(idMeasure)
)DEFAULT CHARSET=utf8;

drop table if exists Items;
create table Items(
	idItem int auto_increment,
    label varchar(100),
    price double(10,2),
    descr text,
    qt double(10,3),
    idMeasure int,
    constraint PK_ITEMS primary key(idItem),
    constraint FK_MEASURES_ITEMS foreign key(idMeasure) references Measures(idMeasure)
)DEFAULT CHARSET=utf8;

drop table if exists Users;
create table Users(
	idUser int auto_increment,
    email varchar(200),
    pwd text,
    lastName varchar(200),
    firstName varchar(200),
    address text,
    address2 text,
    verified boolean default true,
    idRole int,
    constraint PK_USERS primary key(idUser),
    constraint FK_ROLES_USERS foreign key(idRole) references Roles(idRole)
)DEFAULT CHARSET=utf8;

drop table if exists Orders;
create table Orders(
	idOrder int auto_increment,
	idUser int,
    idStatus int,
    orderDt datetime,
    pickupDt datetime,
    constraint PK_ORDERS primary key(idOrder), 
    constraint FK_USERS_ORDERS foreign key(idUser) references Users(idUser),
    constraint FK_ORDERSTATUS_ORDERS foreign key(idStatus) references OrderStatus(idStatus)
)DEFAULT CHARSET=utf8;

drop table if exists Stocks;
create table Stocks(
	idStock int auto_increment,
    idItem int,
    constraint PK_STOCKS primary key(idStock), 
    constraint FK_ITEMS_STOCKS foreign key(idItem) references Items(idItem)
)DEFAULT CHARSET=utf8;

drop table if exists SoldItems;
create table SoldItems(
	idSoldItem int auto_increment,
    idItem int,
    idOrder int,
    constraint PK_SOLDITEMS primary key(idSoldItem), 
    constraint FK_ITEMS_SOLDITEMS foreign key(idItem) references Items(idItem),
    constraint FK_ORDERS_SOLDITEMS foreign key(idOrder) references Orders(idOrder)
)DEFAULT CHARSET=utf8;

drop table if exists Regroups;
create table Regroups(
	idCategory int,
    idItem int,
    constraint PK_REGROUPS primary key(idCategory, idItem), 
    constraint FK_ITEMS_REGROUPS foreign key(idItem) references Items(idItem),
    constraint FK_CATEGORIES_REGROUPS foreign key(idCategory) references Categories(idCategory)
)DEFAULT CHARSET=utf8;

drop table if exists ItemContains;
create table ItemContains(
	idAllergen int,
    idItem int,
    constraint PK_ITEMCONTAINS primary key(idAllergen, idItem), 
    constraint FK_ITEMS_ITEMCONTAINS foreign key(idItem) references Items(idItem),
    constraint FK_ALLERGENS_ITEMCONTAINS foreign key(idAllergen) references Allergens(idAllergen)
)DEFAULT CHARSET=utf8;



# INSERTING VALUES

INSERT INTO Roles (idRole, label)
VALUES
(1, 'administrateur'),
(2, 'client');

INSERT INTO Allergens(idAllergen, label)
VALUES
(1,'gluten'),
(2,'oeufs'),
(3,'arachides'),
(4,'lait')
;

INSERT INTO OrderStatus(idStatus, label)
VALUES
(1,'en attente de paiement'),
(2,'en attente de d\'enlèvement (paiement sur place)'),
(3,'en attente de d\'enlèvement (payée en ligne)'),
(4,'payée'),
(5,'enlevée'),
(6,'annulée')
;

INSERT INTO Categories (idCategory, label)
VALUES 
(1,'produit laitier'),
(2,'céréales'),
(3,'oeuf'),
(4,'fromage'),
(5,'viande'),
(6,'pâtisserie')
;

INSERT INTO Measures (idMeasure, label, unit)
VALUES 
(1,'grammes','g'),
(2,'kilogramme','kg'),
(3,'Litre','L'),
(4,'pièce','pc')
;

INSERT INTO Items (label, price, descr, qt, idMeasure)
VALUES 
('fromage blanc maigre',1.10,'descr fromage',250,1),
('fromage blanc gras',1.30,'descr fromage',250,1),
('fromage blanc aux fines herbes',1.50,'descr fromage',250,1),
('fromage blanc au lait battu',1.50,'descr fromage',250,1),
('boule de Blanche Pierre nature',10.00,'descr fromage',1,2),
('boule de Blanche Pierre aux orties',12.00,'descr fromage',1,2),
('boule de Blanche Pierre : mélange italien',13.00,'descr fromage',1,2),
('petit Mesvinois aux fines herbes',2.50,'descr fromage',1,4),
('petit Mesvinois aux échalottes',2.50,'descr fromage',1,4),
('petit Mesvinois au poivre',2.50,'descr fromage',1,4),
('petit Mesvinois au thym & miel',2.50,'descr fromage',1,4),
('beurre',7.20,'descr beurre',1,2),
('crème fraîche',8.00,'descr crème',1,3),
('lait entier',0.80,'descr lait',1,3),
('lait battu',0.60,'descr lait',1,3),
('oeuf',0.25,'descr oeuf',1,4),
('maton',6.00,'descr pâtisserie',1,4),
('tarte au riz',8.00,'descr pâtisserie',1,4),
('pommes de terre',null,null,null,null),
('poulet',7.00,'descr viande',1,2),
('lapin',8.00,'descr viande',1,2),
('foin',5.00,null,null,null),
('paille',3.00,null,null,null),
('yaourt',0.50,'descr yaourt',1,4),
('canard',9.00,'descr animal',1,2),
('brie',10.00,'descr fromage',1,2),
('maroilles',12.00,'descr fromage',1,2)
;

INSERT INTO Users (idUser, email, pwd, lastName, firstName, address, address2, verified, idRole)
VALUES 
(1,'a@gmail.com','$2a$12$ku6KVHw7qDQxvFeDcF037.hmzR9NLGZXMzZrXZKPWZNkMI/FrOibW','admin','admin','rue du test','7060 Soignies',true, 1),
(2,'c@gmail.com','$2a$12$ku6KVHw7qDQxvFeDcF037.hmzR9NLGZXMzZrXZKPWZNkMI/FrOibW','client','client','rue du test','7060 Soignies',true, 2);

INSERT INTO Stocks (idItem)
VALUES 
(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),
(2),(2),(2),(2),(2),(2),(2),(2),(2),(2),
(3),(3),(3),(3),(3),(3),(3),(3),(3),(3),
(4),(4),(4),(4),(4),(4),(4),(4),(4),(4),
(5),(5),(5),(5),(5),(5),(5),(5),(5),(5),
(5),(6),(6),(6),(6),(7),(7),(7),(7),(7),
(7),(8),(8),(8),(8),(8),(9),(9),(9),(9),
(9),(9),(10),(10),(10),(10),(10),(10),(10),(10),
(10),(10),(10),(10),(10),(10),(10),(11),(11),(11),
(11),(11),(11),(11),(11),(11),(11),(11),(14),(14),
(14),(14),(14),(14),(14),(14),(14),(14),(14),(14),
(14),(14),(14),(14),(14),(14),(14),(14),(14),(14),
(14),(14),(14),(14),(14),(14),(14),(14),(14),(14),
(14),(14),(14),(14),(14),(14),(14),(14),(14),(14)
;

INSERT INTO ItemContains (idAllergen, idItem) VALUES 
('4', '12'),('4','7'),('4','6'),('4','5'),
('4','26'),('4','13'),('4','4'),('4','3'),
('4','2'),('4','1'),('4','15'),('4','14'),
('4','27'),('2','16'),('2','17'),('4','17'),
('4','10'),('4','11'),('4','8'),('4','9'),
('4','18'),('2','18'),('4','24');