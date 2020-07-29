# How Generate Cert for Client to connect Postgresql

Please Execute these Commands from the files folder under postgres to use the root.crt.

```sh
$ openssl genrsa -des3 -out /tmp/postgresql.key 1024
$ openssl rsa -in /tmp/postgresql.key -out /tmp/postgresql.key
$ openssl req -new -key /tmp/postgresql.key -out /tmp/postgresql.csr -subj '/C=CA/ST=British Columbia/L=Comox/O=localhost/CN=ascentuser'
$ openssl x509 -req -in /tmp/postgresql.csr -CA root.crt -CAkey server.key -out /tmp/postgresql.crt -CAcreateserial
```