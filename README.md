Bash String Escape
================================

提供用`Bash`字符串转义的工具方法。

在`Bash`中使用提供字符串会需要转义，不能直接把字符串放参数位置上。

如要输出字符串：   
`2 space  a double quotation mark " a single quotation mark ' end!`

需要执行的命令如下：

```bash
echo '2 space  a double quotation mark " a single quotation mark '\'' end!'
```

即在命令行上，要提供字符串是：  
`'2 space  a double quotation mark " a single quotation mark '\'' end!'`

这个库提供方法来从源字符串来生成`Bash`要使用的字符串。
