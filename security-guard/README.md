# Security Guard
Security Guard is turning a service into a Resource service ([OAuth2](https://docs.spring.io/spring-security-oauth2-boot/docs/2.0.x/reference/html/boot-features-security-oauth2-resource-server.html)).
## Documents
For more information, see "[Security Guard docs](https://97lynk.github.io/base-modules/security-guard)".
## How to use?
Add dependency in `pom.xml` file follow [Release package](https://github.com/97lynk/base-modules/packages/472267)

Security Guard using asymmetric encryption so must to config java key store:
```yml
security:
    oauth2:
        resource:
            jwt:
                key-store: <location of java key store, e.g., classpath:key-file.jks>
                key-password: <password of key store file>
                key-alias: <alias name>
                key-store-password: <password of alias>
```
or public key url:
```yml
security:
    oauth2:
        resource:
            jwt:
                key-uri: <key uri published auth server, e.g., http://localhost:7070/oauth/token_key>
```

