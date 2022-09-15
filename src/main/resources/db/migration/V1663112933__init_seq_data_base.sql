CREATE SEQUENCE person_seq
    AS bigint
    INCREMENT 1
    MINVALUE 1
    NO MAXVALUE
    NO CYCLE
    START WITH 1
    OWNED BY person.id;

ALTER TABLE public.person
    ALTER COLUMN id SET DEFAULT nextval('person_seq'::regclass);


CREATE SEQUENCE bank_account_seq
    AS bigint
    INCREMENT 1
    MINVALUE 1
    NO MAXVALUE
    NO CYCLE
    START WITH 1
    OWNED BY bank_account.id;

ALTER TABLE public.bank_account
    ALTER COLUMN id SET DEFAULT nextval('bank_account_seq'::regclass);