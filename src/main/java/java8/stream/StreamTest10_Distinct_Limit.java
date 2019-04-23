package java8.stream;

import java.util.stream.IntStream;


/***
 * stream
 *  和迭代器不同的是，Stream可以并行化操作，迭代器只能命令地，串行化的操作
 *  当使用串行的方式去遍历时，每个Item读完后再读下一个item
 *  使用并行去遍历时，数据会被分成多个段，其中每一个不同的线程处理，然后将结果一起输出
 *  Stream的并行操作依赖于Java7中引入的Fork/Join框架
 *
 *  提交测试
 *
 *
 *     描述性的语言
 *
 *
 *     select name from student where age > 20 and address = 'beijing' order by age desc ;
 *
 *
 *
 *     students.stream.filter(student->student.getAge() > 20).filter(student.getAddress().eques('beijing')).sorted(...).forEach
 *     [student->System.out.println(student.getName())];
 *
 *
 *
 *
 *  内部迭代
 *
 *
 *  外部迭代
 *  List<Student> list = new ArrayList<>();
 *
 *  for(int i = 0 ;i < student.size(); ++i){
 *      Student student = student.get(i);
 *      if(student.getAge() > 20 && student.getAddress().equals("beijing")){
 *          list.add(student);
 *      }
 *  }
 *
 *
 *  Collections.sort(list,Comparator()...);
 *  for(Student : list){
 *      System.out.println(student.getName());
 *  }
 *
 *
 *
 *  集合关注的是数据与数据的存储本身
 *
 *  流关注的是数据的计算
 *
 *  流与迭代器类似的一点是 ：流是无法重复使用或消费的
 *
 *  inputstream ,outputstream
 *
 *  每一次中间操作都会返回一个Stream对象，比如说返回Stream<Student>,Stream<String>
 *      如果遇到一个mapToInt 那么返回map一个映射类型，可能不返回值，也可能返回其他类型的单个值
 *
 *  void main(){
 *
 *  }
 *
 *
 *
 *
 *
 *
 */
public class StreamTest10_Distinct_Limit {

    public static void main(String[] args) {
        IntStream.iterate(0, i -> (i + 1) % 2).limit(6).distinct().forEach(System.out::println);
        //如果返回0和1，表示创建一个无限流,而limit6的话，就一直在等侍
        //IntStream.iterate(0,i -> (i + 1 ) % 2 ).distinct().limit(6).forEach(System.out::println);
        System.out.println("========================");


    }


}
