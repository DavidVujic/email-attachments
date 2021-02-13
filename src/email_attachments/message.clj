(ns email-attachments.message
  (:require [clojure.string :as str])
  (:import [javax.mail.internet MimeMessage]))

(defn message-content
  "Returns the content of a MimeMessage or a MimeBodyPart"
  [message]
  (.getContent message))

(defn- message-content-type [message]
  (.getContentType message))

(defn- message-content-count [content]
  (.getCount content))

(defn- body-part [content index]
  (.getBodyPart content index))

(defn- multipart? [message]
  (str/starts-with? (message-content-type message) "multipart/"))

(defn- multipart->parts [message]
  (let [content       (message-content message)
        content-range (-> content message-content-count range)]
    (map #(body-part content %) content-range)))

(defn- message-parts [message]
  (if (multipart? message)
    (map message-parts (multipart->parts message))
    (conj () message)))

(defn- message->map [message]
  {:content-type (message-content-type message)
   :data         message})

(defn body [message]
  (->> message
       message-parts
       flatten
       (map message->map)))

(defn stream->mime-message [stream]
  (MimeMessage. nil stream))
