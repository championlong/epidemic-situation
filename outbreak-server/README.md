
## 存在的问题
* SSLHandshakeException: No appropriate protocol (protocol is disabled or cipher suites are inappropri
> 去掉SSLv3, TLSv1, TLSv1.1后，再启动项目，具体操作如下： 

1. cd /Library/Java/JavaVirtualMachines/jdk1.8.0_341.jdk/Contents/Home/jre/lib/security 
2. vim java.security 
3. jdk.tls.disabledAlgorithms=SSLv3, TLSv1, TLSv1.1, RC4, DES, MD5withRSA, \
4. 修改为： jdk.tls.disabledAlgorithms=RC4, DES, MD5withRSA, \
