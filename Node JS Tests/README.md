This project shows the various options available to test NodeJS MicroServices.  

A MicroService for Restaurant reservation that uses SQL Lite and JOI (for schema validation) is tested.  The following testing approaches have been shown


Testing Promises via Mocha

Test Double via Proxyquire and Sinon including

	Spies, which offer information about function calls, without affecting their behavior

	Stubs, which are like spies, but completely replace the function. This makes it possible to make a stubbed function do whatever you like — throw an exception, return a specific value, etc

	Mocks, which make replacing whole objects easier by combining both spies and stubs
	 