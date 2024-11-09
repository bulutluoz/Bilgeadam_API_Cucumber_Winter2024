Feature: US1003 Kullanici Reqress server'a get request yollar

  Scenario: TC03 donen response bilgileri expected degerler ile ayni olmali

    Then kullanici Reqress path parametreleri olarak "users/1" ekler
    And kullanici Reqress GET request gonderip donen actual response i kaydeder
    Then kullanici Reqress actual response status kodunun 200 test eder
    And kullanici Reqress actual response "Server" header degerinin "cloudflare" oldugunu test eder
    Then kullanici Reqress response i jSonPath olarak kaydeder
    Then "data" altindaki "first_name" degerinin "George" oldugunu test eder
    And "data" altindaki "email" degerinin "george.bluth@reqres.in" oldugunu test eder
    And "data" altindaki "last_name" degerinin "Bluth" oldugunu test eder
    And "data" altindaki "avatar" degerinin "https://reqres.in/img/faces/1-image.jpg" oldugunu test eder
    And "support" altindaki "url" degerinin "https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral" oldugunu test eder
    And "support" altindaki "text" degerinin "Tired of writing endless social media content? Let Content Caddy generate it for you." oldugunu test eder

