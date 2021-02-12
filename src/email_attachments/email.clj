(ns email-attachments.email
  (:require [clojure.string :as str]
            [email-attachments.message :as message]
            [email-attachments.query :as query]))

(defn- content-type? [message-map type]
  (-> message-map
      :content-type
      (str/includes? type)))

(defn csv? [message-map]
  (content-type? message-map "text/csv"))

(defn excel? [message-map]
  (or (content-type? message-map "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
      (content-type? message-map "application/vnd.ms-excel")))

(defn ms-word? [message-map]
  (or (content-type? message-map "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
      (content-type? message-map "application/msword")))

(defn pdf? [message-map]
  (content-type? message-map "application/pdf"))

(defn xml? [message-map]
  (or (content-type? message-map "application/xml")
      (content-type? message-map "text/xml")))

(defn content-stream
  "Extract data as an input stream from a single message-map"
  [message-map]
  (let [data (:data message-map)]
    (when data (-> data message/message-content))))

(defn content-types
  "Takes an email as an input-stream,
   extracts the content types into a seq of message-maps."
  [email-input-stream]
  (->> email-input-stream
       message/stream->mime-message
       message/body))

(defn filename
  "Extracts the filename of an attachment in a message-map"
  [message-map]
  (query/filename message-map))

(defn filenames
  "Extracts the attachment filenames from a seq of message-maps"
  [message-maps]
  (query/filenames message-maps))

(defn find-in
  [message-maps filename]
  (query/find-in message-maps filename))
