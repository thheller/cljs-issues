Ticket at: http://dev.clojure.org/jira/browse/CLJS-2040

## Problem

The CLJS compiler that all Closure compatible JS will be in the `goog.*` namespace.

This assumption is bad and produces non-optimal code if there is any other Closure JS in a build.

```
java -cp ../cljs.jar:src:src-closure clojure.main build.clj
```

Will produce the files in the `out` directory.

The problematic invoke is in `out/foo/main.js`


```
// Compiled by ClojureScript 1.9.521 {:static-fns true}
goog.provide('foo.main');
goog.require('cljs.core');
goog.require('dummy.foo');
goog.require('goog.string');
goog.string.urlEncode("foo");
(dummy.foo.demo.cljs$core$IFn$_invoke$arity$2 ? dummy.foo.demo.cljs$core$IFn$_invoke$arity$2((1),(2)) : dummy.foo.demo.call(null,(1),(2)));

//# sourceMappingURL=main.js.map
```

The `gstr/urlEncode` uses the good form but the `x/demo` invoke uses the CLJS style invoke and will always end up in `.call`.

The code still works but should be optimized.

```
dummy.foo.demo((1),(2));
```