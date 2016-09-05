(ns example-rest.util
  (:import [java.util Date]))

(defn in?
  "This function returns true if the provided seqenuce contains the given
  elment."
  [sequence elm]
  (some #(= elm %) sequence))

(defn now []
  (Date.))

(defmulti strftime
  "Format time t according to format string fmt.

  Note that this was copied from Miki Tebeka's work here:
    https://github.com/tebeka/clj-strftime/blob/master/src/strftime.clj"
  (fn [fmt t] (class t)))

(defmethod strftime Date
  [fmt t]
  ; Convert strftime to String.format format (e.g. %m -> %1$tm)
  (let [fmt (.replaceAll fmt "%([a-zA-Z])" "%1\\$t$1")]
    (format fmt t)))

(defn get-local-ip
  "Get the IP address of the local machine."
  []
  (.getHostAddress (java.net.InetAddress/getLocalHost)))

(defn add-shutdown-handler [func]
  (.addShutdownHook (Runtime/getRuntime)
                    (Thread. func)))
