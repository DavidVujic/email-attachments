(ns email-attachments.email-test
  (:require [clojure.java.io :as io]
            [email-attachments.email :as email]))

(comment
  (->> "emails/excel.eml"
       io/input-stream
       email/content-types))

(comment
  (->> "emails/excel.eml"
       io/input-stream
       email/content-types
       first
       email/content-stream))
