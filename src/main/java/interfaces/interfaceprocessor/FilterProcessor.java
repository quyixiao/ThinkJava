//: interfaces/interfaceprocessor/FilterProcessor.java
package interfaces.interfaceprocessor;

import interfaces.filters.*;


/****
 * 178页
 *      这种使用适配器的方式中，FilterAdapter的构造器的的接受你所拥有的接口Filter，然后生成
 * 具有有你所需要的Processor接口的对象，你可能还注意到了，在FilterAdapter类中用到了代理
 *
 */
class FilterAdapter implements Processor {
    Filter filter;

    public FilterAdapter(Filter filter) {
        this.filter = filter;
    }

    public String name() {
        return filter.name();
    }

    public Waveform process(Object input) {
        return filter.process((Waveform) input);
    }
}

public class FilterProcessor {
    public static void main(String[] args) {
        Waveform w = new Waveform();
        Apply.process(new FilterAdapter(new LowPass(1.0)), w);
        Apply.process(new FilterAdapter(new HighPass(2.0)), w);
        Apply.process(new FilterAdapter(new BandPass(3.0, 4.0)), w);
    }
}


/* Output:
Using Processor LowPass
Waveform 0
Using Processor HighPass
Waveform 0
Using Processor BandPass
Waveform 0
*///:~
