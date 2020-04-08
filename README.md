# GemfireMBeanRetrieveApplication
Retrieve Gemfire MBean metrics via Spring Boot for Apache Geode &amp; Pivotal GemFire

# How to run this application
1.Create a Gemfire cluster with Cluster Configuration Service enabled.
For example:
```
start locator --port=7900
configure pdx --read-serialized=true --auto-serializable-classes=".*"
start server --server-port=7901 --locators=localhost[7900] --include-system-classpath=true --dir=cacheserver1
create region --name=exampleRegion --type=PARTITION
```

2.Download this project by git command:
```
git clone https://github.com/GSSJacky/GemfireMBeanRetrieveApplication
```

3.Change the properties such as locators in application.properties accordingly
( [⁨MBeanRetrieve_RootPath]/⁨src/⁨main/⁨resources⁩/application.properties )
```
spring.data.gemfire.cache.name=MBeanRetrieveApplication
spring.data.gemfire.cache.log-level=config
spring.data.gemfire.locators="localhost[7900]"
```

4.Install package and run this project by mvn command such as "mvn install".You can find the below Gemfire jmxManager MBean metrics from console output if you run the project correctly:
```
2020-04-08 01:25:18.231  INFO 23883 --- [           main] o.s.data.gemfire.CacheFactoryBean        : Created new Pivotal GemFire version [9.8.7] Cache [MBeanRetrieveApplication]

---------Print jvmMetrics start----------
{JVMMetrics : gcCount = 5 gcTimeMillis = 7 initMemory = 268435456 committedMemory = 314048512 usedMemory = 73057808 maxMemory = 3817865216 totalThreads = 45}
---------Print jvmMetrics end----------

---------Print OSMetrics start----------
{OSMetrics : maxFileDescriptorCount = 10240 openFileDescriptorCount = 147 processCpuTime = 9839853000 committedVirtualMemorySize = 10431377408 totalPhysicalMemorySize = -1 freePhysicalMemorySize = -1 totalSwapSpaceSize = -1 freeSwapSpaceSize = -1 name = Mac OS X version = 10.14.5 arch = x86_64 availableProcessors = 4 systemLoadAverage = 3.3759765625}
---------Print OSMetrics end----------
```
