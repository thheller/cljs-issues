(require '[cljs.build.api :as cljs])

;;java -cp ../cljs.jar:src clojure.main build.clj

(cljs/build
  "src"
  {:modules
   {:main
    {:entries ['foo.main]
     :output-to "out/main.js"}}
   :output-dir "out"
   :verbose true
   :optimizations :advanced})

