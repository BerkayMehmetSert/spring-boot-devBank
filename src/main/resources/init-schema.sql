CREATE TABLE accounts
(
    id                       INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    account_number           VARCHAR(255),
    balance                  DOUBLE PRECISION,
    created_at               TIMESTAMP WITHOUT TIME ZONE,
    updated_at               TIMESTAMP WITHOUT TIME ZONE,
    deleted_at               TIMESTAMP WITHOUT TIME ZONE,
    is_active                BOOLEAN,
    customer_id              INTEGER,
    account_type             VARCHAR(255),
    account_status           VARCHAR(255),
    account_limit            DOUBLE PRECISION,
    account_limit_type       VARCHAR(255),
    account_limit_status     VARCHAR(255),
    account_limit_amount     DOUBLE PRECISION,
    account_limit_start_date date,
    account_limit_end_date   date,
    account_limit_created_at TIMESTAMP WITHOUT TIME ZONE,
    account_limit_updated_at TIMESTAMP WITHOUT TIME ZONE,
    account_limit_deleted_at TIMESTAMP WITHOUT TIME ZONE,
    account_limit_is_active  BOOLEAN,
    CONSTRAINT pk_accounts PRIMARY KEY (id)
);

CREATE TABLE addresses
(
    id           INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    address_line VARCHAR(255),
    city         VARCHAR(255),
    state        VARCHAR(255),
    country      VARCHAR(255),
    zip_code     VARCHAR(255),
    created_at   TIMESTAMP WITHOUT TIME ZONE,
    updated_at   TIMESTAMP WITHOUT TIME ZONE,
    deleted_at   TIMESTAMP WITHOUT TIME ZONE,
    is_active    BOOLEAN,
    CONSTRAINT pk_addresses PRIMARY KEY (id)
);

CREATE TABLE customers
(
    id           INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    first_name   VARCHAR(255),
    last_name    VARCHAR(255),
    national_id  VARCHAR(11)                              NOT NULL,
    email        VARCHAR(255)                             NOT NULL,
    password     VARCHAR(255),
    phone_number VARCHAR(255),
    created_at   TIMESTAMP WITHOUT TIME ZONE,
    updated_at   TIMESTAMP WITHOUT TIME ZONE,
    deleted_at   TIMESTAMP WITHOUT TIME ZONE,
    is_active    BOOLEAN,
    address_id   INTEGER,
    CONSTRAINT pk_customers PRIMARY KEY (id)
);

ALTER TABLE accounts
    ADD CONSTRAINT uc_accounts_account_number UNIQUE (account_number);

ALTER TABLE accounts
    ADD CONSTRAINT FK_ACCOUNTS_ON_CUSTOMER_ID FOREIGN KEY (customer_id) REFERENCES customers (id);

ALTER TABLE customers
    ADD CONSTRAINT FK_CUSTOMERS_ON_ADDRESS_ID FOREIGN KEY (address_id) REFERENCES addresses (id);