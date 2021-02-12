(ns email-attachments.email-test
  (:require [clojure.java.io :as io]
            [email-attachments.email :as email]))

(comment
  (->> "emails/example2.eml"
       io/input-stream
       email/content-types))

(comment
  (->> "emails/example4.eml"
       io/input-stream
       email/content-types
       first
       email/content-stream))

(comment
  (->> "emails/excel.eml"
       io/input-stream
       email/content-types
       first
       email/filename))

(comment
  (->> "emails/example2.eml"
       io/input-stream
       email/content-types
       email/filenames))

(comment
  (def content-types (->> "emails/example4.eml"
                          io/input-stream
                          email/content-types))

  (def filename "email-exempel.csv")
  (email/find-in content-types filename))
