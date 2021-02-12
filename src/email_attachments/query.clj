(ns email-attachments.query)

(defn filename [{:keys [data]}]
  (.getFileName data))

(defn filenames [content-types]
  (map filename content-types))

(defn- match? [name m]
  (= name (filename m)))

(defn find-in [content-types filename]
  (-> (filter #(match? filename %) content-types)
      seq))
