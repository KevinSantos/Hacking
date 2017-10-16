### Brute Force
* Cover all possible combinations
* Hydra
  * **$ hydra _IP_ -L _usernameListFile_ -P _passwordListFile_ http-post-form "path to form?page=login.php:username=^USER^_&password=^PASS^&login-php-submit-button=Login:F=Not Logged In"**

### Dictionary
* Use a wordlist and try every password in the list
* Crunch - generate dictionaries
  * **$ crunch _minChars_ _maxChars_ _chars_ -t _pattern_ -o _filename_**
  * pattern - a@@@b
