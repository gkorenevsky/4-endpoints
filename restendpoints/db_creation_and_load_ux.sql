create table test.USER_INFO (
FIRST_NAME VARCHAR(120) NOT NULL
,LAST_NAME VARCHAR(120) NOT NULL
,GENDER CHAR(1) NOT NULL
,ADDRESS_LINE_1 VARCHAR(120) NOT NULL
,ADDRESS_LINE_2 VARCHAR(120)
,CITY VARCHAR(120) NOT NULL
,STATE_OR_PROVINCE VARCHAR(120) NOT NULL
,COUNTRY VARCHAR(120) NOT NULL
,POSTAL_CODE VARCHAR(120) NOT NULL
,USER_ID VARCHAR(120) NOT NULL
,ENCRYPTED_PASSWORD VARCHAR(120) NOT NULL
,PRIMARY KEY (USER_ID)
,CONSTRAINT UNIQ_USER_ID UNIQUE (USER_ID)
);

CREATE UNIQUE INDEX USER_INFO_UNIQUE_PRIMARY_IX ON test.USER_INFO (USER_ID);

INSERT INTO test.USER_INFO (
FIRST_NAME
,LAST_NAME
,GENDER
,ADDRESS_LINE_1
,ADDRESS_LINE_2
,CITY
,STATE_OR_PROVINCE
,COUNTRY
,POSTAL_CODE
,USER_ID
,ENCRYPTED_PASSWORD
)
VALUES ( 'Janelle', 'Holden', 'F', '451 Manhattan Beach Blvd', 'Suite d226', 'Manhattan Beach', 'CA', 'USA', '90266', 'jholden', '123abc' )
;

INSERT INTO test.USER_INFO (
FIRST_NAME
,LAST_NAME
,GENDER
,ADDRESS_LINE_1
,ADDRESS_LINE_2
,CITY
,STATE_OR_PROVINCE
,COUNTRY
,POSTAL_CODE
,USER_ID
,ENCRYPTED_PASSWORD
)
VALUES ( 'Randall', 'Harwood', 'M', '16817 Prairie Ave', NULL, 'Lawndale', 'CA', 'USA', '90260', 'rharwood', '456efg' )
;

INSERT INTO test.USER_INFO (
FIRST_NAME
,LAST_NAME
,GENDER
,ADDRESS_LINE_1
,ADDRESS_LINE_2
,CITY
,STATE_OR_PROVINCE
,COUNTRY
,POSTAL_CODE
,USER_ID
,ENCRYPTED_PASSWORD
)
VALUES ( 'Robert', 'Kapust', 'M', '703 Pier Ave', NULL, 'Hermosa Beach', 'CA', 'USA', '90254', 'kapustr', '99Acf11' )
;

INSERT INTO test.USER_INFO (
FIRST_NAME
,LAST_NAME
,GENDER
,ADDRESS_LINE_1
,ADDRESS_LINE_2
,CITY
,STATE_OR_PROVINCE
,COUNTRY
,POSTAL_CODE
,USER_ID
,ENCRYPTED_PASSWORD
)
VALUES ( 'Steve', 'Lazar', 'M', '7425 W Azure Dr', '#200', 'Las Vegas', 'NV', 'USA', '89130', 'laserman', 'kahqo762e' )
;

INSERT INTO test.USER_INFO (
FIRST_NAME
,LAST_NAME
,GENDER
,ADDRESS_LINE_1
,ADDRESS_LINE_2
,CITY
,STATE_OR_PROVINCE
,COUNTRY
,POSTAL_CODE
,USER_ID
,ENCRYPTED_PASSWORD
)
VALUES ( 'Patricia', 'Maser', 'F', '751 Teaneck Rd', NULL, 'Teaneck', 'NJ', 'USA', '07666', 'pat_maser', 'KD9-23H805R' )
;
