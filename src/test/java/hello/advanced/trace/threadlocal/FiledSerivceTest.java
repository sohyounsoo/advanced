package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FiledService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FiledSerivceTest {

    private FiledService filedService = new FiledService();

    @Test
    void filed() {
        log.info("main start");

//        Runnable userA = new Runnable() {
//            @Override
//            public void run() {
//                filedService.logic("userA");
//            }
//        }

        Runnable userA = () -> {
            filedService.logic("userA");
        };
        Runnable userB = () -> {
            filedService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadA.setName("thread-B");

        threadA.start();
        sleep(2000); //동시성 문제 방지
        threadB.start();

        sleep(3000);//  메인쓰레드 종료대기
        log.info("main exit");
    }

    private void sleep(int m) {
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
