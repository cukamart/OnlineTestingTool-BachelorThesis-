1) Stiahnut PgAdmin v4<br/>
2) Stiahnut PostgreSQL server https://www.postgresql.org/download/windows/<br/>
3) Do tamcatu do priecinku lib skopirovat jdbc driver pre postgreSQL https://jdbc.postgresql.org/download.html<br/>
4) V eclipse do server/context.xml skopirovat<br/>
<Resource name="jdbc/postgres" auth="Container"
          type="javax.sql.DataSource" driverClassName="org.postgresql.Driver"
          url="jdbc:postgresql://127.0.0.1:5432/postgres"
          username="cuka1" password="1111" maxActive="20" maxIdle="10" maxWait="-1"/>
5) vytvorit v pgadmine usera cuka1<br/>
6) povolit autentifikaciu  (metoda trust)<br/>
7) vytvorit databazu pomocou skriptu<br/>
8) pridat do testov stlpec te_test<br/>
9) insertnut do databazy potrebne data<br/>
