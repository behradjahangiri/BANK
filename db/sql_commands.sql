create table customer(
    id number primary key ,
    username nvarchar2(20),
    password nvarchar2(15),
    firstname nvarchar2(20),
    lastname nvarchar2(20),
    nationllid nvarchar2(10),
    phone nvarchar2(10),
    email nvarchar2(50),
    address nvarchar2(100),
    dateofbirth date,
    registrationdate date
);

CREATE table account(
    accountnumber nvarchar2(16) primary key ,
    balance number,
    opendate date,
    status nvarchar2(10),
    customerid number,
    foreign key (customerid) references customer(id)
);

create table transaction(
    transactionid number primary key ,
    transactiontype nvarchar2(10),
    amount float,
    transactiondate date,
    sourceaccount nvarchar2(16),
    targetaccount nvarchar2(16),
    foreign key (sourceaccount) references account(accountnumber),
    foreign key (targetaccount) references account(accountnumber)
)