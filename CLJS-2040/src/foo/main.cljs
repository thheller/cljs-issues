(ns foo.main
  (:require [dummy.foo :as x]
            [goog.string :as gstr]))

;; good
(gstr/urlEncode "foo")

;; bad
(x/demo 1 2)
