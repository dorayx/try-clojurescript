(require 'cljs.build.api)

(cljs.build.api/build "src"
	{:main 'sample-project.core
	 :output-to "out/main.js"})