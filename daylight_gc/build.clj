(require 'cljs.build.api)

(cljs.build.api/build "src"
	{:main 'daylight-gc.core
	 :output-to "out/main.js"})