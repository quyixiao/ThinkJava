package strings;//: strings/WhitherStringBuilder.java


/***
 * 285页
 *
 * 1
 *
 *      可以看到，不仅循环的代码更简短，更简单，而且它只生成了一个StringBuilder对象，显式的
 * 创建了StringBuilder还允许你预先为其指定大小，如果你己经知道最终的字符串大概有多长的话，
 * 那预先指定StringBuilder的大小可以避免多次重新分配缓冲
 *
 *
 *
 */
public class WhitherStringBuilder {
    public String implicit(String[] fields) {
        String result = "";
        for (int i = 0; i < fields.length; i++)
            result += fields[i];
        return result;
    }

    public String explicit(String[] fields) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < fields.length; i++)
            result.append(fields[i]);
        return result.toString();
    }
} ///:~
