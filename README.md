### email-attachments

[![Clojars Project](https://img.shields.io/clojars/v/email-attachments.svg)](https://clojars.org/email-attachments)

A Clojure library that makes extracting attachments from email simple.

#### Usage

Example - extract a CSV file from an email:

``` clojure
;; example: a raw .eml file as an input-stream

(->> my-email-stream
     email/content-types
     (filter email/csv?)
     first
     email/content-stream)
```

The `email/content-stream` will return the attached file as a `stream`.

The library includes some helpers for determining file types:

``` clojure
email/csv?
email/excel?
email/ms-word?
email/pdf?
email/xml?
```

There are also querying helpers, that can be used to extract file names or find an attachment by name:

``` clojure
(email/filenames message-maps) ;; pass in the result from the email/content-types function
(email/filename message-map)   ;; pass in one item from the seq of the email/content-types result

(email/find-in message-maps "my-filename.csv")
```

Example output from the `email/content-types` function, a seq of `message-map`:

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
