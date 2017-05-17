(require '[cljs.build.api :as cljs])

;;java -cp ../cljs.jar:src clojure.main build.clj

(cljs/build
  "src"
  {:output-dir "out"
   :optimizations :none
   :verbose true
   :static-fns true})

