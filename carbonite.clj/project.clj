(defproject carbonite "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [openjdk/tools "7"]]
  :java-source-paths ["src-java"]

  :aot :all
  :manifest {"Agent-Class" "carbonite.BytecodeVault"
             "Can-Retransform-Classes" "true"}
  )
