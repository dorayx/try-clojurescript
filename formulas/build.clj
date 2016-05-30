(require 'cljs.build.api)

(cljs.build.api/build "src"
	{:main 'formulas.core
	 :output-to "out/main.js"})