### email-attachments

[![Clojars Project](https://img.shields.io/clojars/v/email-attachments.svg)](https://clojars.org/email-attachments)

A Clojure library that makes extracting attachments from email simple.

#### Usage

Example, extract an attached CSV file from an email file as an input stream
and extract the file content as a stream:

``` clojure
(->> my-email-stream
     email/content-types
     (filter email/csv?)
     first
     email/content-stream)
```

#### References
A lot of inspiration and ideas comes from these repositories:

* [clojure-mail](https://github.com/owainlewis/clojure-mail)
* [simple-email](https://github.com/kisom/simple-email)

Useful Java docs:
* https://docs.oracle.com/javaee/6/api/javax/mail/internet/MimeMessage.html
* https://commons.apache.org/proper/commons-email/apidocs/org/apache/commons/mail/util/MimeMessageParser.html
