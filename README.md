Сделал сервис, который счикает кол-во заходов на страницу. Сонхронизация реализована с помощью пессимистичной блокировки или блокировки со стороны JVM в виде Lock.
Не сделел проверку на отсутствие записи в бд. Не стал это делать программно, т.к для этого есть собственные инструменты, которые в тз не были указаны.