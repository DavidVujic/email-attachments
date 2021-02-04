### email-attachments

[![Clojars Project](https://img.shields.io/clojars/v/email-attachments.svg)](https://clojars.org/email-attachments)

A Clojure library that makes extracting attachments from email simple.

#### Usage

Example - extract a CSV file from an email:

``` clojure
(->> my-email-stream
     email/content-types
     (filter email/csv?)
     first
     email/content-stream)
```

The `email/content-stream` will return the attached file as an `input-stream`.

The library includes some helpers for determining file types:

``` clojure
email/csv?
email/excel?
email/ms-word?
```

Example output from the `email/content-types` function:

``` clojure
({:content-type "text/plain; charset=\"utf-8\"",
  :data #object[ ... MimeBodyPart ... ]}

 {:content-type "text/html; charset=\"utf-8\"",
  :data #object[ ... MimeBodyPart ... ]}

  {:content-type "text/csv;\r\n\tx-unix-mode=0644;\r\n\tname=\"my-csv-file.csv\"",
  :data #object[ ... MimeBodyPart ... ]})
```

#### References
A lot of inspiration and ideas comes from these repositories:

* [clojure-mail](https://github.com/owainlewis/clojure-mail)
* [simple-email](https://github.com/kisom/simple-email)

Useful Java docs:
* https://docs.oracle.com/javaee/6/api/javax/mail/internet/MimeMessage.html
* https://commons.apache.org/proper/commons-email/apidocs/org/apache/commons/mail/util/MimeMessageParser.html
