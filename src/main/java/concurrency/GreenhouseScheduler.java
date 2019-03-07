package concurrency;//: concurrency/GreenhouseScheduler.java
// Rewriting innerclasses/GreenhouseController.java
// to use a ScheduledThreadPoolExecutor.
// {Args: 5000}

import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/*****
 *  730页
 *  使用ScheduledExecutor的温室控制器
 *      在第10章介绍过可以应用于假想的温室的控制系统的示例，它可以控制各种设施的开关。
 *  或者是对它们进行调节，这可以被看作是一种并发问题，每个期望的温室事件都是一个预定
 *  时间运行的任务，ScheduledThreadPoolExecutor提供了解决该问题的服务，通过使用schedule()
 *  运行一次任务，或者，schueduleAtFixedRate() 每隔规则 的时间重复执行任务，你可以将
 *  Runnable对象设置为在将来的某个时刻执行，将下现的程序与在第10章中使用的方式相比，就
 *  会注意到，当你使用像ScheduledThreadPoolExecutor这样的预定义工具时，要简单许多。
 *
 *
 *  这是一个很基础的优先级队列，它具有可阻塞的读取操作，下面是一个示例，其中的优先
 *  级队列中的对象是按照优先级有顺序从队列中出现的任务，PrioritizedTask被赋予了一个优先级
 *  数字，以此来提供这种顺序
 *
 *
 *
 *
 *
 *
 *  这是一个问题的，因为我觉得还是这样的一个有知道的这是一个这样的人，因为这是一测试这是一个很不好用的
 *  因为这是这个，这是一个有觉得还是好的，因为这是一个他这个的，测试我们这是一个他个人的行为，这是一个
 *  他们的这是一个他一个他们的这个人的行为这是样的，因为我们觉得还是这样的一个他们他们的这是一个他们的，
 *  觉得还是好的，因为我们觉得还是这样的一个，人的觉得，哈哈中，我觉得还是一样的好的，因为我觉得还是一个
 *   有道字典的，因为我同学是这样的一个，我觉得还是这样的
 *
 *   我觉得还是比较好的，
 *
 *   因为，我觉得还是一个比较好的

 *
 *
 * 1
 *
 *
 *
 */
public class GreenhouseScheduler {
    private volatile boolean light = false;
    private volatile boolean water = false;
    private String thermostat = "Day";

    public synchronized String getThermostat() {
        return thermostat;
    }

    public synchronized void setThermostat(String value) {
        thermostat = value;
    }

    ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(10);

    public void schedule(Runnable event, long delay) {
        scheduler.schedule(event, delay, TimeUnit.MILLISECONDS);
    }

    public void repeat(Runnable event, long initialDelay, long period) {
        scheduler.scheduleAtFixedRate(event, initialDelay, period, TimeUnit.MILLISECONDS);
    }

    class LightOn implements Runnable {
        public void run() {
            // Put hardware control code here to
            // physically turn on the light.
            System.out.println("Turning on lights");
            light = true;
        }
    }

    class LightOff implements Runnable {
        public void run() {
            // Put hardware control code here to
            // physically turn off the light.
            System.out.println("Turning off lights");
            light = false;
        }
    }

    class WaterOn implements Runnable {
        public void run() {
            // Put hardware control code here.
            System.out.println("Turning greenhouse water on");
            water = true;
        }
    }

    class WaterOff implements Runnable {
        public void run() {
            // Put hardware control code here.
            System.out.println("Turning greenhouse water off");
            water = false;
        }
    }

    class ThermostatNight implements Runnable {
        public void run() {
            // Put hardware control code here.
            System.out.println("Thermostat to night setting");
            setThermostat("Night");
        }
    }

    class ThermostatDay implements Runnable {
        public void run() {
            // Put hardware control code here.
            System.out.println("Thermostat to day setting");
            setThermostat("Day");
        }
    }

    class Bell implements Runnable {
        public void run() {
            System.out.println("Bing!");
        }
    }

    class Terminate implements Runnable {
        public void run() {
            System.out.println("Terminating");
            scheduler.shutdownNow();
            // Must start a separate task to do this job,
            // since the scheduler has been shut down:
            new Thread() {
                public void run() {
                    for (DataPoint d : data)
                        System.out.println(d);
                }
            }.start();
        }
    }

    // New feature: data collection
    static class DataPoint {
        final Calendar time;
        final float temperature;
        final float humidity;

        public DataPoint(Calendar d, float temp, float hum) {
            time = d;
            temperature = temp;
            humidity = hum;
        }

        public String toString() {
            return time.getTime() +
                    String.format(
                            " temperature: %1$.1f humidity: %2$.2f",
                            temperature, humidity);
        }
    }

    private Calendar lastTime = Calendar.getInstance();

    { // Adjust date to the half hour
        lastTime.set(Calendar.MINUTE, 30);
        lastTime.set(Calendar.SECOND, 00);
    }

    private float lastTemp = 65.0f;
    private int tempDirection = +1;
    private float lastHumidity = 50.0f;
    private int humidityDirection = +1;
    private Random rand = new Random(47);
    List<DataPoint> data = Collections.synchronizedList(
            new ArrayList<DataPoint>());

    class CollectData implements Runnable {
        public void run() {
            System.out.println("Collecting data");
            synchronized (GreenhouseScheduler.this) {
                // Pretend the interval is longer than it is:
                lastTime.set(Calendar.MINUTE,
                        lastTime.get(Calendar.MINUTE) + 30);
                // One in 5 chances of reversing the direction:
                if (rand.nextInt(5) == 4)
                    tempDirection = -tempDirection;
                // Store previous value:
                lastTemp = lastTemp +
                        tempDirection * (1.0f + rand.nextFloat());
                if (rand.nextInt(5) == 4)
                    humidityDirection = -humidityDirection;
                lastHumidity = lastHumidity +
                        humidityDirection * rand.nextFloat();
                // Calendar must be cloned, otherwise all
                // DataPoints hold references to the same lastTime.
                // For a basic object like Calendar, clone() is OK.
                data.add(new DataPoint((Calendar) lastTime.clone(),
                        lastTemp, lastHumidity));
            }
        }
    }

    public static void main(String[] args) {
        GreenhouseScheduler gh = new GreenhouseScheduler();
        gh.schedule(gh.new Terminate(), 5000);
        // Former "Restart" class not necessary:
        gh.repeat(gh.new Bell(), 0, 1000);
        gh.repeat(gh.new ThermostatNight(), 0, 2000);
        gh.repeat(gh.new LightOn(), 0, 200);
        gh.repeat(gh.new LightOff(), 0, 400);
        gh.repeat(gh.new WaterOn(), 0, 600);
        gh.repeat(gh.new WaterOff(), 0, 800);
        gh.repeat(gh.new ThermostatDay(), 0, 1400);
        gh.repeat(gh.new CollectData(), 500, 500);
    }
} /* (Execute to see output) *///:~
