(defproject carbonite "0.1.0-SNAPSHOT"
  :description "Serialize the unserializable"
  :url "https://github.com/farrellm/carbonite"
  :license {:name "Apache License"
            :url "http://www.apache.org/licenses/"}
  :dependencies [[openjdk/tools "7"]]
  :java-source-paths ["src-java"]

  :manifest {"Agent-Class" "carbonite.SeerAgent"
             "Can-Retransform-Classes" "true"})
