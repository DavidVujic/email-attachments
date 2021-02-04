(defproject email-attachments "1.0.0"
  :description "makes extracting email attachments simple"
  :url "https://github.com/DavidVujic/email-attachments"
  :license {:name "The MIT License (MIT)"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.apache.commons/commons-email "1.5"]]
  :aot [email-attachments.email])
