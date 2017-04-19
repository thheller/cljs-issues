Ticket at: http://dev.clojure.org/jira/browse/CLJS-2011

## Problem

`:modules` currently relies on code motion and the user specifying every namespace that should go into a module.

This is unlikely to produce good results and several things are not correctly identified as `pure` by Closure and therefore are not moved.

Namespaces that are not referenced by any of the configured `:entries` are still included in the `cljs_base.js` module. If these files contain any kind of side-effects they are not removed as well.

Problematic issues include `defmethod`, anything with `^:export`, construction of hash maps and more.

The `:optimize-constants` implicit option of `:advanced` is also not optimal and every keyword ends up in the `cljs_base.js` module.

## Steps to reproduce

Run: (use cljs.jar included in repo or download)
```
curl -L https://github.com/clojure/clojurescript/releases/download/r1.9.512/cljs.jar
java -cp cljs.jar:src clojure.main build.clj

open modules.html
```
    
## Issues

```
Building module :cljs-base
Building module :main
  adding entry (foo.main)
  module :main depends on :cljs-base
Adding remaining namespaces to :cljs-base
  adding entry (goog)
  adding entry [goog.string goog.string.Unicode]
  adding entry [goog.object]
  adding entry [goog.math.Integer]
  adding entry [goog.string.StringBuffer]
  adding entry [goog.debug.Error]
  adding entry [goog.dom.NodeType]
  adding entry [goog.asserts goog.asserts.AssertionError]
  adding entry [goog.array]
  adding entry [goog.reflect]
  adding entry [goog.math.Long]
  adding entry (cljs.core)
  adding entry (cljs.core.constants)
  adding entry (foo.b)
  adding entry (foo.a)
```

- `foo.b` was never referenced and should not have been included in the build.
- `foo.a` was only used by `foo.main` but ended up in the `cljs_base.js` module since it is a transitive dependency the user did not specify. It should have been in the `:main` module before going into Closure.
- `main.js` only contains something like `console.log("main loaded",Fe);` where `Fe` is the keyword which was constructed in the `cljs_base.js` although it was only used in this module.
