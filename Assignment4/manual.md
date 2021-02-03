# SDPEncryptor Android Application
This application performs message encryption using the [Affine Cipher](https://en.wikipedia.org/wiki/Affine_cipher)
# The Algorithm
The algorithm works in the following way:
* Every character gets converted into a number between 0 and 25
* The following equation is applied: E(x) = (αx + β) % 26
* Finally, the output of the previous step is converted into a character
* Note that only characters are encrypted. (A-Z and a-z). The rest of the characters are added to the result as they appear in the original message.
# Instructions on how to use the app
* Enter the message to be encrypted
* Enter your alpha value
* Enter your beta value
* Hit Encrypt
* The results of the operation will be displayed below the "Encrypted Message:" label
# Things to keep in mind
* The app only encrypts letters. Any other characters will not be encrypted.
* Valid alpha values are coprimes between 1 and 25 (coprimes to 26)
* Valid beta values are integers between 1 and 25