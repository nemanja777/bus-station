

INSERT INTO prevoznik ( naziv, adresa, pIB) VALUES ('PeraTrans', 'Somborska 15', '123');
INSERT INTO prevoznik ( naziv, adresa, pib) VALUES ( 'NisExpres', 'Bulevar' , '34344');
INSERT INTO prevoznik ( naziv, adresa, pib) VALUES ( 'SomborTrans', 'Slavujeva 14a' , '544');
INSERT INTO prevoznik ( naziv, adresa, pIB) VALUES ('JocaExpress', 'Bulevar Cara Lazara 25a', '11556');

INSERT INTO linija (broj_Mesta, cena_karte, vreme_polaska, destinacija, prevoznik_id) VALUES ( 60, 1000.00, '16:00:00', 'Novi Sad - Linz', 1); 
INSERT INTO linija (broj_mesta, cena_karte, vreme_polaska, destinacija, prevoznik_id) VALUES ( 15, 2500.00, '00:30', 'Milano - Novi Sad', 2);
INSERT INTO linija (broj_mesta, cena_karte, vreme_polaska, destinacija, prevoznik_id) VALUES ( 90, 760.00, '08:30', 'Novi Sad - Beograd', 1);
INSERT INTO linija (broj_mesta, cena_karte, vreme_polaska, destinacija, prevoznik_id) VALUES ( 40, 1990.00, '17:15', 'Novi Sad - Kursumlija', 3);

INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');


INSERT INTO rezervacija ( broj_putnika, linija_id, korisnik_id) VALUES ( 4, 1, 1 );
INSERT INTO rezervacija ( broj_putnika, linija_id, korisnik_id) VALUES ( 15, 1, 2);
INSERT INTO rezervacija ( broj_putnika, linija_id, korisnik_id) VALUES ( 10, 2, 3);
INSERT INTO rezervacija ( broj_putnika, linija_id, korisnik_id) VALUES ( 2, 4, 3);
INSERT INTO rezervacija ( broj_putnika, linija_id, korisnik_id) VALUES ( 16, 3, 1);





 




