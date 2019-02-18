(set-env!
  :resource-paths #{"resources"}
  :dependencies '[[cljsjs/boot-cljsjs "0.10.3" :scope "test"]
                  [cljsjs/vega "4.4.0-0"]
                  [cljsjs/vega-lite "3.0.0-rc10-0"]])

(require '[cljsjs.boot-cljsjs.packaging :refer :all])

  (def +lib-version+ "3.26.0")
(def +version+ (str +lib-version+ "-0"))

(task-options!
  pom {:project     'cljsjs/vega-embed
       :version     +version+
       :description "Publish Vega visualizations as embedded web components with interactive parameters."
       :url         "https://vega.github.io/vega-embed"
       :scm         {:url "https://github.com/cljsjs/packages"}})

(deftask package []
  (task-options! push {:ensure-branch nil})
  (comp
    (download
     :url (format "https://unpkg.com/vega-embed@%s/build/vega-embed.js" +lib-version+)
     :checksum "F19B68E44197E68EFF6F3CA9853E22F4")
    (download
     :url (format "https://unpkg.com/vega-embed@%s/build/vega-embed.min.js" +lib-version+)
     :checksum "F5A79DB2A11969A205EC0222E28D9EFB")
    (download
     :url (format "https://unpkg.com/vega-embed@%s/vega-embed.css" +lib-version+)
     :checksum "CFA09422ACF13696B84282A5FCE9267A")
    (sift :move {#".*vega-embed\.js$"      "cljsjs/development/vega-embed.inc.js"})
    (sift :move {#".*vega-embed\.min\.js$" "cljsjs/production/vega-embed.min.inc.js"})
    (sift :move {#".*vega-embed\.css$"     "cljsjs/vega-embed/common/vega-embed.css"})
    (sift :include #{#"^cljsjs"})
    (deps-cljs :name "cljsjs.vega-embed" :requires ["cljsjs.vega" "cljsjs.vega-lite"])
    (pom)
    (jar)))
