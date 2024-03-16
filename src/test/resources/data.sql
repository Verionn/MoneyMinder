insert into user_entity (name, password, email, role, verified)
values (
   'verion',
   '$2a$10$VfbQPT7JpzA2AzuUkN98Aer0Kos4OCbrh0En/.cADXab82JykvWbS', --12345,
   'verion@gmail.com',
   'USER',
    TRUE
);
insert into user_entity (name, password, email, role, verified)
values (
   'bajcik',
   '$2a$10$VfbQPT7JpzA2AzuUkN98Aer0Kos4OCbrh0En/.cADXab82JykvWbS', --12345,
   'bajcik@gmail.com',
   'ADMIN',
    TRUE
);
insert into reset_password_token_entity(token, email, name, expiration_date)
values(
       'expiredl-ORKa-4930-9831-eec546b91830',
       'verion@gmail.com',
       'klocuszek',
       '2024-03-06 18:16:00'
      );
insert into reset_password_token_entity(token, email, name, expiration_date)
values(
      'validsdl-ORKa-4930-9831-eec546b91830',
      'verion@gmail.com',
      'klocuszek',
      '2324-03-06 18:16:00'
      );

