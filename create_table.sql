CREATE TABLE IF NOT EXISTS TRANSACTION_LOG (
   ID serial not null unique ,
   REQUEST_BODY varchar,
   RESPONSE_BODY varchar,
   CREATION_DATE timestamp
);