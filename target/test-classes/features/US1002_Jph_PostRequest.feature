
  Feature: US1002 Kullanici JsonPlaceHolder server'a Post request gonderir

    Scenario: TC02 Post request yollandiginda donen actual response'daki
              attribute degerleri beklenen degerlerle ayni olmali


      Given kullanici "jPHBaseUrl" kullanir
      Then kullanici path parametreleri olarak "posts/70" ekler
      And Put request icin "Ahmet","Merhaba",10 70 bilgileri ile request body olusturur
      And kullanici Put request gonderip donen actual response i kaydeder
      Then kullanici actual response status kodunun 200 test eder
      And kullanici content type degerinin "application/json; charset=utf-8" eder
      And actual response daki "Connection" header degerinin "keep-alive"
      Then actual response attribute degerlerinin "Ahmet","Merhaba",10 70 oldugunu test eder
