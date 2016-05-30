(require 'cljs.build.api)

(cljs.build.api/watch "src"
	{:main 'daylight-js.core
	 :output-to "out/main.js"})