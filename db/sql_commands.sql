-- create table customer(
--     id number primary key ,
--     username nvarchar2(20),
--     password nvarchar2(15),
--     firstname nvarchar2(20),
--     lastname nvarchar2(20),
--     nationllid nvarchar2(10),
--     phone nvarchar2(10),
--     email nvarchar2(50),
--     address nvarchar2(100),
--     dateofbirth date,
--     registrationdate date
-- );

-- create sequence customer_seq start with 1 increment by 1;

-- CREATE table account(
--     accountnumber nvarchar2(16) primary key ,
--     balance number,
--     opendate date,
--     status nvarchar2(10),
--     customerid number,
--     foreign key (customerid) references customer(id)
-- );

-- create sequence account_seq start with 1 increment by 1;

-- create table transaction(
--     transactionid number primary key ,
--     transactiontype nvarchar2(10),
--     amount float,
--     transactiondate date,
--     sourceaccount nvarchar2(16),
--     targetaccount nvarchar2(16),
--     foreign key (sourceaccount) references account(accountnumber),
--     foreign key (targetaccount) references account(accountnumber)
-- );

-- create sequence transaction_seq start with 1 increment by 1;

-- create view transaction_report as
-- select TRANSACTIONID as TRANSACTION_ID,
--        TRANSACTIONTYPE as TRANSACTION_TYPE,
--        AMOUNT as AMOUNT,
--        SOURCEACCOUNT as SOURCEACCOUNT,
--        TARGETACCOUNT as TARGETACCOUNT,
--        CUSTOMER.FIRSTNAME as FIRST_NAME,
--        CUSTOMER.LASTNAME as LAST_NAME
--
-- from TRANSACTION
--     inner join
--     ACCOUNT  on TRANSACTION.SOURCEACCOUNT = ACCOUNT.ACCOUNTNUMBER
--     inner join
--     ACCOUNT on TRANSACTION.TARGETACCOUNT = ACCOUNT.ACCOUNTNUMBER
--     inner join
--     CUSTOMER on

-- CREATE VIEW transaction_report AS
-- SELECT
--     transaction_table.transactionid AS transaction_id,               -- شناسه تراکنش
--     transaction_table.transactiontype AS transaction_type,           -- نوع تراکنش
--     transaction_table.amount AS amount,                              -- مبلغ تراکنش
--     transaction_table.transactiondate AS transaction_date,           -- تاریخ تراکنش
--
--     -- جزئیات حساب منبع (source)
--     source_account_table.accountnumber AS source_account_number,     -- شماره حساب منبع
--     source_account_table.balance AS source_balance,                  -- موجودی حساب منبع
--     source_account_table.opendate AS source_open_date,               -- تاریخ افتتاح حساب منبع
--     source_account_table.status AS source_status,                    -- وضعیت حساب منبع
--
--     -- جزئیات مشتری منبع (source)
--     source_customer_table.firstname || ' ' || source_customer_table.lastname AS source_customer_full_name,  -- نام کامل مشتری منبع
--     source_customer_table.phone AS source_phone,                     -- شماره تلفن مشتری منبع
--     source_customer_table.email AS source_email,                     -- ایمیل مشتری منبع
--
--     -- جزئیات حساب مقصد (target)
--     target_account_table.accountnumber AS target_account_number,     -- شماره حساب مقصد
--     target_account_table.balance AS target_balance,                  -- موجودی حساب مقصد
--     target_account_table.opendate AS target_open_date,               -- تاریخ افتتاح حساب مقصد
--     target_account_table.status AS target_status,                    -- وضعیت حساب مقصد
--
--     -- جزئیات مشتری مقصد (target)
--     target_customer_table.firstname || ' ' || target_customer_table.lastname AS target_customer_full_name,  -- نام کامل مشتری مقصد
--     target_customer_table.phone AS target_phone,                     -- شماره تلفن مشتری مقصد
--     target_customer_table.email AS target_email                      -- ایمیل مشتری مقصد
-- FROM
--     transaction transaction_table                                    -- جدول اصلی: تراکنش‌ها
--         INNER JOIN
--     account source_account_table                                     -- جوین اول: حساب منبع
--     ON transaction_table.sourceaccount = source_account_table.accountnumber
--         INNER JOIN
--     customer source_customer_table                                   -- جوین دوم: مشتری منبع (از طریق حساب منبع)
--     ON source_account_table.customerid = source_customer_table.id
--         INNER JOIN
--     account target_account_table                                     -- جوین سوم: حساب مقصد
--     ON transaction_table.targetaccount = target_account_table.accountnumber
--         INNER JOIN
--     customer target_customer_table                                   -- جوین چهارم: مشتری مقصد (از طریق حساب مقصد)
--     ON target_account_table.customerid = target_customer_table.id;
--

CREATE VIEW transaction_report AS
SELECT
    -- فیلدهای جدول TRANSACTION
    transaction_table.transactionid          AS transaction_id,
    transaction_table.transactiontype        AS transaction_type,
    transaction_table.amount                 AS amount,
    transaction_table.transactiondate        AS transaction_date,

    -- تمام فیلدهای ACCOUNT منبع (source)
    source_account.accountnumber             AS source_account_number,
    source_account.balance                   AS source_balance,
    source_account.opendate                  AS source_open_date,
    source_account.status                    AS source_status,
    source_account.customerid                AS source_account_customerid,

    -- تمام فیلدهای CUSTOMER منبع (source)
    source_customer.id                       AS source_customer_id,
    source_customer.username                 AS source_customer_username,
    source_customer.password                 AS source_customer_password,
    source_customer.firstname                AS source_customer_firstname,
    source_customer.lastname                 AS source_customer_lastname,
    source_customer.nationllid               AS source_customer_nationallid,
    source_customer.phone                    AS source_customer_phone,
    source_customer.email                    AS source_customer_email,
    source_customer.address                  AS source_customer_address,
    source_customer.dateofbirth              AS source_customer_dateofbirth,
    source_customer.registrationdate         AS source_customer_registrationdate,

    -- تمام فیلدهای ACCOUNT مقصد (target)
    target_account.accountnumber             AS target_account_number,
    target_account.balance                   AS target_balance,
    target_account.opendate                  AS target_open_date,
    target_account.status                    AS target_status,
    target_account.customerid                AS target_account_customerid,

    -- تمام فیلدهای CUSTOMER مقصد (target)
    target_customer.id                       AS target_customer_id,
    target_customer.username                 AS target_customer_username,
    target_customer.password                 AS target_customer_password,
    target_customer.firstname                AS target_customer_firstname,
    target_customer.lastname                 AS target_customer_lastname,
    target_customer.nationllid               AS target_customer_nationallid,
    target_customer.phone                    AS target_customer_phone,
    target_customer.email                    AS target_customer_email,
    target_customer.address                  AS target_customer_address,
    target_customer.dateofbirth              AS target_customer_dateofbirth,
    target_customer.registrationdate         AS target_customer_registrationdate

FROM
    TRANSACTION  transaction_table

INNER JOIN
    ACCOUNT  source_account
    ON transaction_table.sourceaccount = source_account.accountnumber

INNER JOIN
    CUSTOMER  source_customer
    ON source_account.customerid = source_customer.id

INNER JOIN
    ACCOUNT  target_account
    ON transaction_table.targetaccount = target_account.accountnumber

INNER JOIN
    CUSTOMER  target_customer
    ON target_account.customerid = target_customer.id;
