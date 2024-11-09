
  Feature: US1001

    Scenario: TC Kullanıcı Get Request sonucundaki
              Response degerleri beklenen degerlerle karşılaştırır

      Given kullanici "jPHBaseUrl" kullanir
      Then  kullanici path parametreleri olarak "posts/11" ekler
      And   kullanici GET request gonderip donen actual response i kaydeder
      Then  kullanici actual response status kodunun 200 test eder
      And   kullanici content type degerinin "application/json; charset=utf-8" eder
      Then  kullanici response degerlerini jSonPath olarak kaydeder
      Then  kullanici response boyddeki id degerinin 11 oldugunu test eder
      Then  kullanici response bodydeki "title" degerinin String "et ea vero quia laudantium autem" test eder

