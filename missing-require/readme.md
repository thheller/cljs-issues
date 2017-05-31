Ticket at: http://dev.clojure.org/jira/browse/CLJS-2064

```
java -cp ../cljs.jar:src:src-closure clojure.main build.clj
```

## Problem

`foo.main` uses `clojure.set/union` without a `:require` for `clojure.set`. No warning is produced since `foo.bar` used `clojure.set`.

Since it is not guaranteed that `foo.bar` is compiled before `foo.main` this may not work in some situations and will probably break `:parallel-build`.

```
Copying jar:file:/Users/zilence/code/oss/cljs-issues/cljs.jar!/cljs/core.cljs to out/cljs/core.cljs
Reading analysis cache for jar:file:/Users/zilence/code/oss/cljs-issues/cljs.jar!/cljs/core.cljs
Compiling out/cljs/core.cljs
Using cached cljs.core out/cljs/core.cljs
Copying jar:file:/Users/zilence/code/oss/cljs-issues/cljs.jar!/clojure/set.cljs to out/clojure/set.cljs
Compiling out/clojure/set.cljs
Compiling src/foo/bar.cljs
Compiling src/foo/main.cljs
Copying jar:file:/Users/zilence/code/oss/cljs-issues/cljs.jar!/goog/base.js to out/goog/base.js
Copying jar:file:/Users/zilence/code/oss/cljs-issues/cljs.jar!/goog/string/string.js to out/goog/string/string.js
Copying jar:file:/Users/zilence/code/oss/cljs-issues/cljs.jar!/goog/object/object.js to out/goog/object/object.js
Copying jar:file:/Users/zilence/code/oss/cljs-issues/cljs.jar!/goog/math/integer.js to out/goog/math/integer.js
Copying jar:file:/Users/zilence/code/oss/cljs-issues/cljs.jar!/goog/string/stringbuffer.js to out/goog/string/stringbuffer.js
Copying jar:file:/Users/zilence/code/oss/cljs-issues/cljs.jar!/goog/debug/error.js to out/goog/debug/error.js
Copying jar:file:/Users/zilence/code/oss/cljs-issues/cljs.jar!/goog/dom/nodetype.js to out/goog/dom/nodetype.js
Copying jar:file:/Users/zilence/code/oss/cljs-issues/cljs.jar!/goog/asserts/asserts.js to out/goog/asserts/asserts.js
Copying jar:file:/Users/zilence/code/oss/cljs-issues/cljs.jar!/goog/array/array.js to out/goog/array/array.js
Copying jar:file:/Users/zilence/code/oss/cljs-issues/cljs.jar!/goog/reflect/reflect.js to out/goog/reflect/reflect.js
Copying jar:file:/Users/zilence/code/oss/cljs-issues/cljs.jar!/goog/math/long.js to out/goog/math/long.js
Copying file:/Users/zilence/code/oss/cljs-issues/missing-require/src/foo/main.cljs to out/foo/main.cljs
Copying file:/Users/zilence/code/oss/cljs-issues/missing-require/src/foo/bar.cljs to out/foo/bar.cljs
```




