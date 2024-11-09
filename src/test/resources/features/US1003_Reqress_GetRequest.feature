Feature: US1003 Kullanici Reqress server'a get request yollar

  Scenario: TC03 donen response bilgileri expected degerler ile ayni olmali

    Given kullanici "reqresBaseUrl" kullanir
    Then kullanici path parametreleri olarak "users/1" ekler
    And kullanici Reqress GET request gonderip donen actual response i kaydeder
    Then kullanici Reqress actual response status kodunun 200 test eder
    And kullanici Reqress actual response "Server" header degerinin "cloudflare" oldugunu test eder
    Then kullanici Reqress response degerlerini jSonPath olarak kaydeder
    Then "data" altindaki "first_name" degerinin "George" oldugunu test eder
    And "data" altindaki "email" degerinin "george.bluth@reqres.in" oldugunu test eder
    And "data" altindaki "last_name" degerinin "Bluth" oldugunu test eder
    And "data" altindaki "avatar" degerinin "https://reqres.in/img/faces/1-image.jpg" oldugunu test eder
    And "support" altindaki "url" degerinin "https://reqres.in/#support-heading" oldugunu test eder
    And "support" altindaki "text" degerinin "To keep ReqRes free, contributions towards server costs are appreciated!" oldugunu test eder

