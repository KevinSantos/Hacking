# Salt and Hash
* In cryptography, a salt is random data that is used as an additional input to a one-way function that "hashes" a password or passphrase. Salts are closely related to the concept of nonce. The primary function of salts is to defend against dictionary attacks or against its hashed equivalent, a pre-computed rainbow table attack.
* A new salt is randomly generated for each password.

### Data from client:
Username |	Password
--- | ---
user1	| password123
user2	| password123

### Data Stored (except _"String to be hashed"_ ofc)
Username |	Salt value	| String to be hashed	| Hashed value = SHA256 (Password + Salt value)
--- | --- | --- | ---
user1	| E1F53135E559C253 | password123+E1F53135E559C253 |	72AE25495A7981C40622D49F9A52E4F1565C90F048F59027BD9C8C8900D5C3D8
user2 |	84B03D034B409D4E |	password123+84B03D034B409D4E |	B4B6603ABC670967E99C7E7F1389E40CD16E78AD38EB1468EC2AA1E62B8BED3A

# [Code to Hash in PHP, Java, C#, Ruby](https://github.com/KevinSantos/password-hashing)
