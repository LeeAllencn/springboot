List<?>:意思是元素类型未知的List。(?)问号称为通配符，它的元素类型可以匹配任何类型。集合总的元素类型是Object

```markdown
/**
 * 泛型方法定义
 * <T> 声明此方法为泛型方法
 * List<T> 方法返回值为List<T>
 * Class<T> 指明泛型T的具体类型
 */
public static <T> List<T> parseArray(String text, Class<T> clazz);
```

# OkHttp3

> [OKHttp官网](http://square.github.io/okhttp/)

> [OkHttp3使用指南](http://www.qingpingshan.com/rjbc/az/110232.html)

> [Bearer Token](http://www.haomou.net/2014/08/13/2014_bare_token/)

> [OkHttp使用完全教程 - 简书](http://www.jianshu.com/p/ca8a982a116b)



# 算法

## 滑动窗口

适合题型：数组的子数组、字符串的子串

解题思路：

```markdown
// 左右指针
int left = 0;
int right = 0;
// 外层循环扩展右边界
while (right < length) {
    // TODO 当前考虑的元素
    // 内层循环扩展左边界
    while (left <= right && check()) {
        // TODO 区间[left,right]不符合题意
        left++;
    }
    // TODO 区间[left,right]符合题意，统计相关信息
    right++;
}
```