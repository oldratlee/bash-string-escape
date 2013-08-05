Bash String Escape
================================

提供用Bash字符串转义的工具方法。

在Bash中使用提供字符串会需要转义，往往不能直接把字符串放参数位置上。

如要输出字符串：   
`Follow e space   . Follow a double quotation marks ". Follow a single quotation marks '. End!`

需要执行的命令如下：

```bash
echo 'Follow 3 space   . Follow a double quotation marks ". Follow a single quotation marks '\''. End!'
```

在命令行上，要提供字符串是  
`'Follow 3 space   . Follow a double quotation marks ". Follow a single quotation marks '\''. End!'`。

这个库提供的方法来从源字符串来生成Bash要使用的字符串。
