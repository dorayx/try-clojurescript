(require 'cljs.build.api)

(cljs.build.api/build "src"
	{:main 'move-zeros.core
	 :output-to "out/main.js"})