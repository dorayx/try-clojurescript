(require 'cljs.build.api)

(cljs.build.api/watch "src"
	{:main 'daylight-gc.core
	 :output-to "out/main.js"})