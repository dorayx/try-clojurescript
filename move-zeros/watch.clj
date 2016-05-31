(require 'cljs.build.api)

(cljs.build.api/watch "src"
	{:main 'move-zeros.core
	 :output-to "out/main.js"})