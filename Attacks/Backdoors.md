### [Veil](https://github.com/Veil-Framework/Veil.git)
  * Generates backdoor file to be uploaded
  * **$ ./veil.py**
  * **$ list** - List available tools
  * **$ 1** - Evasion
  * **$ list** - List payloads
  * **$ use _n_** - Use payload number _n_
  * **$ options** - Check options
  * **$ set _option_ _value_** - Customize payload
  * **$ generate** - Generate backdoor
  * Check if file generated is detected by AVs - [No Distribute](https://nodistribute.com)
  
### [Metasploit]()
  * Creates session and listens to the backdoor created previously
  * **$ msfconsole**
  * **$ use _moduleName_** - eg. exploit/multi/handler
  * **$ show options/payloads**
  * **$ set _option_ _value_**
  * **$ exploit** - Listen to connections
  
