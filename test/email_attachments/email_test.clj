(ns email-attachments.email-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest testing is]]
            [clojure.java.io :as io]
            [email-attachments.email :as email]))

(defn- test-email->stream []
  (-> "test/data/fake.eml" io/input-stream))

(defn- test-email-content-types []
  (email/content-types (test-email->stream)))

(defn- test-email-content-type []
  (-> (test-email-content-types) first))

(deftest content-types
  (testing "one item in the content-types seq"
    (is (= 1 (count (test-email-content-types)))))

  (testing "returns correct content-type"
    (is (str/starts-with? (-> (test-email-content-type) :content-type) "text/csv")))

  (testing "returns data as a MimeBodyPart"
    (is (= javax.mail.internet.MimeBodyPart (-> (test-email-content-type) :data type)))))

(deftest content-stream
  (testing "returns attachment data as a stream"
    (is (= javax.mail.util.SharedByteArrayInputStream (-> (test-email-content-type) email/content-stream type))))

  (testing "returns correct headers from csv attachment"
    (is (str/starts-with? (-> (test-email-content-type) email/content-stream slurp) "Date,Name,Value"))))

(deftest filenames
  (is (= "my-example-file.csv" (-> (test-email-content-types) email/filenames first))))

(deftest filename
  (is (= "my-example-file.csv" (-> (test-email-content-type) email/filename))))

(deftest find-in
  (testing "returns one item matching a file name"
    (is (= 1 (-> (test-email-content-types) (email/find-in "my-example-file.csv") count))))

  (testing "returns a message-map"
    (let [m (-> (test-email-content-types)
                (email/find-in "my-example-file.csv")
                first)]
      (is (and (contains? m :content-type)
               (contains? m :data))))))

(comment
  (->> "emails/example4.eml"
       io/input-stream
       email/content-types))

(comment
  (->> "emails/example4.eml"
       io/input-stream
       email/content-types
       first
       email/content-stream
       slurp))

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
  (def the-content-types (->> "emails/example4.eml" io/input-stream email/content-types))
  (def the-filename "email-exempel.csv")
  (email/find-in the-content-types the-filename))
