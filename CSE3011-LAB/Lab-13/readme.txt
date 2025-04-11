Generate the Server Keystore and Certificate
----------------------------------------------
keytool -genkeypair -alias serverkey -keyalg RSA -keysize 2048 -keystore serverkeystore.jks -validity 365 -storepass password -keypass password -dname "CN=ServerName, OU=OrgUnit, O=Organization, L=City, S=State, C=Country"

Export the Server Certificate
-----------------------------
keytool -export -alias serverkey -keystore serverkeystore.jks -file server.cer -storepass password

Generate the Client Keystore and Certificate
--------------------------------------------
keytool -genkeypair -alias clientkey -keyalg RSA -keysize 2048 -keystore clientkeystore.jks -validity 365 -storepass password -keypass password -dname "CN=ClientName, OU=OrgUnit, O=Organization, L=City, S=State, C=Country"

Export the Client Certificate
-----------------------------
keytool -export -alias clientkey -keystore clientkeystore.jks -file client.cer -storepass password

Import the Server Certificate into the Client Truststore
--------------------------------------------------------
keytool -import -alias servercert -file server.cer -keystore clienttruststore.jks -storepass password

Import the Client Certificate into the Server Truststore
--------------------------------------------------------
keytool -import -alias clientcert -file client.cer -keystore servertruststore.jks -storepass password

