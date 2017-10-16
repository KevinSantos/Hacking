* To edit cookies use Cookie Manager+ in Firefox
* Create fake html page with values defined by you to be opened by the victim.
* Use javascript code to submit your form automatically
  ```html
  <script>document.getElementById('<formName>').submit();</script>
  ```
### Mitigation
* [Example](https://github.com/KevinSantos/Hacking/blob/master/Mitigation/csrfMitigation.php)
* Make sure the user is submittig data through a page in your web application - **Tokens**
  * Tokens must be a large value, random, unique, use encrypt algo (eg. MD5)
  * Token structure: **[_userID_ _userRequest_ _keyFromServer_]**
  * Tokens are generated client side and embeded in the HTML in a hidden form
  * Then they are sent to the server
  * Server has the capability to generate the same token
  * Compare received and generated token: if they differ **WARNING**
