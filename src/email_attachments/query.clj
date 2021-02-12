(ns email-attachments.query)

(defn filename [{:keys [data]}]
  (.getFileName data))

(defn filenames [message-maps]
  (map filename message-maps))

(defn- match? [name m]
  (= name (filename m)))

(defn find-in [message-maps filename]
  (-> (filter #(match? filename %) message-maps)
      seq))
