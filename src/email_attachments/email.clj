(ns email-attachments.email
  (:require [clojure.string :as str]
            [email-attachments.message :as message]))

(defn- content-type? [m type]
  (-> m
      :content-type
      (str/includes? type)))

(defn csv? [m]
  (content-type? m "text/csv"))

(defn excel? [m]
  (or (content-type? m "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
      (content-type? m "application/vnd.ms-excel")))

(defn ms-word? [m]
  (or (content-type? m "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
      (content-type? m "application/msword")))

(defn content-stream
  "Extract data as an input stream from a single message-map"
  [message-map]
  (let [data (:data message-map)]
    (when data (-> data message-content))))

(defn content-types
  "Takes an email as an input-stream,
   extracts the content types into a seq of message-maps."
  [email-input-stream]
  (->> email-input-stream
       message/stream->mime-message
       message/body))