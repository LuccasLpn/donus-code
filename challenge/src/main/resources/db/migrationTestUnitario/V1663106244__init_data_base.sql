CREATE TABLE IF NOT EXISTS person
(
    id          BIGINT NOT NULL,
    full_name   VARCHAR(255),
    cpf         VARCHAR(255),
    authorities VARCHAR(255),
    CONSTRAINT pk_person PRIMARY KEY (id)
);
ALTER TABLE person
    ADD CONSTRAINT uc_person_cpf UNIQUE (cpf);


CREATE TABLE IF NOT EXISTS bank_account
(
    id        BIGINT NOT NULL,
    agency    VARCHAR(255),
    account   VARCHAR(255),
    balance   BIGINT,
    person_id BIGINT NOT NULL,
    CONSTRAINT pk_bank_account PRIMARY KEY (id)
);

ALTER TABLE bank_account
    ADD CONSTRAINT FK_BANK_ACCOUNT_ON_PERSON FOREIGN KEY (person_id) REFERENCES person (id);