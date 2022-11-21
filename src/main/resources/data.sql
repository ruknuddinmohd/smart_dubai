INSERT INTO BOOK (id,name,description,author,type,price,isbn) VALUES
  (50,'Fiction Book','Fiction Book description','Author123','FICTION',50,'ISBN123'),
  (51,'COMICS Book','Comic Book description','Author456','COMIC',60,'ISBN4567');

INSERT INTO PROMO (id,code,fiction_Percentage,comic_Percentage) VALUES
  (50,'PROMO CODE',10,0);

  commit;
