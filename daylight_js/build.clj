(require 'cljs.build.api)

(cljs.build.api/build "src"
	{:main 'daylight-js.core
	 :output-to "out/main.js"})