# cljsjs/dropzone

[](dependency)
```clojure
[cljsjs/dropzone "5.5.0-1"] ;; latest release
```
[](/dependency)

This jar comes with `deps.cljs` as used by the [Foreign Libs][flibs] feature
of the ClojureScript compiler. After adding the above dependency to your project
you can require the packaged library like so:

```clojure
(ns application.core
  (:require cljsjs.dropzone))
```

[flibs]: https://clojurescript.org/reference/packaging-foreign-deps
