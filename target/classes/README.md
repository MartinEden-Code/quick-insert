1. 把库表sql给建立一下
   1. 程序里面给出了批量插入的接口，可以直接使用该接口，不过注意，此时数据是跑到user_memory这个表，需要在数据库中再执行sql *insert into user select * from user_memory*
   2. 也可以直接使用文章给出的参考连接，构造测试数据
2. 根据实际情况，修改数据库密码
3. 启动测试，可以开始愉快的测试啦！但是要注意/sync/limit接口可能会导致电脑卡死，请谨慎使用:warning::warning::warning:
