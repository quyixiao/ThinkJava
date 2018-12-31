package io;//: io/PreferencesDemo.java

import java.util.prefs.Preferences;

import static net.mindview.util.Print.print;


/***
 *
 *  588 页
 *  Preferences API 对象序列化相比，前者与对象持久性更加的密切，因为它们可以自动的存储和读取
 *  信息，不过，它只能用于小的，受限的数据集合，我们只能存储基本的数据类型和字符串，并且每一个字符
 *  串的存储长度不能超过8K,不是很小，但是我们不能用它来创建任何东西，顾名思义，PerfereceAPi 用于
 *  存储和读取用户的偏好，以及程序配置项的设置。
 *
 *  Perferces 是一个键值集合，类似的映射，存储一个节点层次结构中，尽管节点层次结构可用来创建更为复杂的
 *  结构，但是通常是创建以你的类名为单一的结点，然后将信息存储于其中，下面是一个简单的例子
 *
 *
 *
 *
 *  这里用的是 userNodeForPackage() ,但是我们也可以选择用SystemNodePackage() ，虽然可能任意的选择
 *  ，但是最好的"user" 用于个别的偏好，将"system" 用于能常的非静态的内部方法内部，我们通常使用getClass()
 *  ，尽管我们不一定非要所它当成的类作为节点，但是这仍然不失为一种很有用的方法，
 *  一旦我们创建了节点，就可以用它来加载读取数据了，在这个例子中，向节点载入不同的类型的数据项，然后获取其keys()
 *  ，它们是以String[]的形式返回的，如果你习惯于keys()属于集合类库，那么这个返回结果可能并不是你所期望的，注意
 *  get() 的第二个参数，如果某个关键字没有任何的条目，那么这个参数就是产生的默认的值，当在一个关键字集合内迭代时
 *  我们总要确定信条目是存在的，因此用null作为默认值是安全的，但是通常我们会获得一个具名的关键字，就像下面的这条
 *  语句
 *
 *
 *
 *  prefs.getInt("Companions",0)
 *
 *  在通常情况下，我们希望提供一个合理的默认值，实际上，典型的习惯用法可见下面这几行
 *
 *  int usageCount = prefs.getInt("UsageCount",0)
 *  usageCount ++;
 *  prefs.putInt("UsageCount",usageCount)
 *
 *  这样，在我们第一次运行的程序的时，UsageCount 的值是0，但在随后的引用中，它将是非零值
 *  但是数据存储到哪了呢，在程序第一次运行的之后，并没有任何的本地的文件，preferences API
 *  利用合适的系统资源完成这个任务，并且这些资源会随操作系统不同而不同，例如在windows 里，就使用
 *  注册表，但是最重要的一点是它以经神奇的般的为我们实现存储信息，
 *
 *
 *
 *
 *
 *
 */
public class PreferencesDemo {
    public static void main(String[] args) throws Exception {
        Preferences prefs = Preferences.userNodeForPackage(PreferencesDemo.class);
        prefs.put("Location", "Oz");
        prefs.put("Footwear", "Ruby Slippers");
        prefs.putInt("Companions", 4);
        prefs.putBoolean("Are there witches?", true);
        int usageCount = prefs.getInt("UsageCount", 0);
        usageCount++;
        prefs.putInt("UsageCount", usageCount);
        for (String key : prefs.keys()) {
            print(key + ": " + prefs.get(key, null));
        }
        // You must always provide a default value:
        print("How many companions does Dorothy have? " + prefs.getInt("Companions", 0));
    }
}




/* Output: (Sample)
Location: Oz
Footwear: Ruby Slippers
Companions: 4
Are there witches?: true
UsageCount: 53
How many companions does Dorothy have? 4
*///:~
