(require 'cljs.build.api)

(cljs.build.api/watch "src"
	{:main 'formulas.core
	 :output-to "out/main.js"})