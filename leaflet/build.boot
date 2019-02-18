(set-env!
  :resource-paths #{"resources"}
  :dependencies '[[cljsjs/boot-cljsjs "0.10.3" :scope "test"]])

(require '[cljsjs.boot-cljsjs.packaging :refer :all])

<<<<<<< HEAD
(def +lib-version+ "1.3.0")
=======
(def +lib-version+ "1.4.0")
>>>>>>> c25e199a0d697f6700a85756e91b8e63ab92080c
(def +version+ (str +lib-version+ "-0"))

(task-options!
 pom  {:project     'cljsjs/leaflet
       :version     +version+
       :description "JavaScript Library for Mobile-Friendly Interactive Maps"
       :url         "http://leafletjs.com/"
       :scm         {:url "https://github.com/cljsjs/packages"}
       :license     {"MIT" "http://opensource.org/licenses/MIT"}})

(require '[clojure.java.io :as io])

(deftask package []
  (comp
   (download :url      (str "https://github.com/Leaflet/Leaflet/archive/v" +lib-version+ ".zip")
             :unzip    true)
   (sift :move {#"^Leaflet-(.*)/dist/leaflet-src.js"    "cljsjs/leaflet/development/leaflet.inc.js"
                #"^Leaflet-(.*)/dist/leaflet.js"        "cljsjs/leaflet/production/leaflet.min.inc.js"
                #"^Leaflet-(.*)/dist/leaflet.css"       "cljsjs/leaflet/common/leaflet.inc.css"
                #"^Leaflet-(.*)/dist/images/(.*\.png)$" "cljsjs/leaflet/common/images/$2"})
   (sift :include #{#"^cljsjs"})
   (deps-cljs :provides ["cljsjs.leaflet" "leaflet"]
              :global-exports '{leaflet L})
   (pom)
   (jar)
<<<<<<< HEAD
   (validate-checksums)))
=======
   (validate)))
>>>>>>> c25e199a0d697f6700a85756e91b8e63ab92080c
